package assign07;

import java.util.*;

/**
 * Represents a sparse, unweighted, directed graph (a set of vertices and a set of edges).
 * The graph is not generic and assumes that a string name is stored at each vertex.
 *
 * @author Eric Heisler & Benjamin Faerber & David Chen
 * @version 2025-6-17
 */
public class Graph<T> {

	// the graph -- a set of vertices (String name mapped to Vertex instance)
	private HashMap<T, Vertex<T>> vertices;

	/**
	 * Constructs an empty graph.
	 */
	public Graph() {
		vertices = new HashMap<T, Vertex<T>>();
	}

	/**
	 * Adds to the graph a directed edge from the vertex with name "name1" 
	 * to the vertex with name "name2".  (If either vertex does not already 
	 * exist in the graph, it is added.)
	 * 
	 * @param name1 - string name for source vertex
	 * @param name2 - string name for destination vertex
	 */
	public void addEdge(T name1, T name2) {
		if (name1 == null || name2 == null) throw new IllegalArgumentException("Vertex names cannot be null");

		Vertex<T> vertex1;
		// if vertex already exists in graph, get its object
		if (vertices.containsKey(name1)) {
			vertex1 = vertices.get(name1);
		}
		else {
			vertex1 = new Vertex<T>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex<T> vertex2;
		// do the same for vertex2
		if (vertices.containsKey(name2)) {
			vertex2 = vertices.get(name2);
		}
		else {
			vertex2 = new Vertex<>(name2);
			vertices.put(name2, vertex2);
		}

		vertex1.addEdge(vertex2);
	}


	
	/**
	 * Generates the DOT encoding of this graph as string, which can be 
	 * pasted into http://www.webgraphviz.com to produce a visualization.
	 */
	public String generateDot() {
		StringBuilder dot = new StringBuilder("digraph d {\n");
		
		// for every vertex 
		for (T str : vertices.keySet()) {
			// for every edge
			Iterator<Vertex<T>> edges = vertices.get(str).edges();
			while (edges.hasNext()) {
				dot.append("\t\"").append(vertices.get(str).getName())
						.append("\" -> \"")
						.append(edges.next().getName())
						.append("\"\n");
			}
		}
		
		return dot.toString() + "}";
	}

	/**
	 * Generates a simple textual representation of this graph.
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		for (T str : vertices.keySet()) {
			result.append(vertices.get(str)).append("\n");
		}
		
		return result.toString();
	}

	/**
	 * The auxillary method powering depth-first search
	 * @param current vertex to start at
	 * @param dest the value we are looking for
	 * @param visited a list of already visited
	 * @return true if item is found
	 */
	private boolean dfsRec(Vertex<T> current, T dest, List<Vertex<T>> visited) {
		for (Iterator<Vertex<T>> it = current.edges(); it.hasNext(); ) {
			Vertex<T> vertex = it.next();

			if (vertex.getValue().equals(dest)) {
				return true;
			}

			if (! visited.contains(current)) {
				visited.add(current);

				return dfsRec(vertex, dest, visited);
			}
		}
		return false;
	}

	/**
	 * Use the depth-first search algorithm to find the destination
	 * @param source The value where to start
	 * @param destination The destination to end up at
	 * @return true if a path from source to destination is found
	 */
	public boolean depthFirstSearch(T source, T destination) {
		List<Vertex<T>> visited = new ArrayList<>();
		Vertex<T> current = vertices.get(source);
		if (current == null || vertices.get(destination) == null) {
			throw new IllegalArgumentException("Vertex names cannot be null");
		}

		return dfsRec(current, destination, visited);
	}

	/**
	 * Use the breadth-first search algorithm to find a path
	 * @param source The value of where to start
	 * @param destination The value of where to end
	 * @return A list of the path values
	 */
	public List<T> breadthFirstSearch(T source, T destination) {
		Vertex<T> firstVert = vertices.get(source);
		Vertex<T> lastVert = vertices.get(destination);
		Queue<Vertex<T>> exploreQue = new LinkedList<>();
		List<T> foundPath = new ArrayList<>();

		if (firstVert == null || lastVert == null) {
			throw new IllegalArgumentException("Vertex names cannot be null");
		}

		exploreQue.add(firstVert);
		firstVert.setVisited(true);

		while (!exploreQue.isEmpty()) {
			Vertex<T> current = exploreQue.poll();
			// Reverse adding kinda
			if (current.equals(lastVert)) {
				Vertex<T> next = lastVert;
				while (next != null) {
					foundPath.add(0, next.getValue());
					next = next.getParent();
				}
				return foundPath;
			}

			for (Vertex<T> neighbor : current.getNeighbors()) {
				if (!neighbor.isVisited()) {
					exploreQue.add(neighbor);
					neighbor.setVisited(true);
					neighbor.setParent(current);
				}
			}
		}
		return null;
	}

	/**
	 * Perform a topological sort of graph
	 * @return The nodes in topological sort order
	 */
	public List<T> topoSort() {
		List<T> result = new ArrayList<>();
		Queue<Vertex<T>> queue = new LinkedList<>();

		for (Vertex<T> vertex : vertices.values()) {
			if (vertex.getIndegree() == 0) {
				queue.add(vertex);
			}
		}
		int visitCount = 0;

		while (!queue.isEmpty()) {
			Vertex<T> current = queue.poll();
			result.add(current.getValue());
			visitCount++;

			for (Vertex<T> neighbor : current.getNeighbors()) {
				neighbor.addIndegree(-1);
				if (neighbor.getIndegree() == 0) {
					queue.add(neighbor);
				}
			}
		}

		if (visitCount != vertices.size()) {
			throw new IllegalArgumentException("Graph contains cycle");
		}

		return result;
	}
}
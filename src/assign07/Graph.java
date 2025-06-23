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
		Vertex vertex1;
		// if vertex already exists in graph, get its object
		if (vertices.containsKey(name1)) {
			vertex1 = vertices.get(name1);
		}
		// else, create a new object and add to graph
		else {
			vertex1 = new Vertex<T>(name1);
			vertices.put(name1, vertex1);
		}

		Vertex vertex2;
		// do the same for vertex2
		if (vertices.containsKey(name2)) {
			vertex2 = vertices.get(name2);
		}
		else {
			vertex2 = new Vertex(name2);
			vertices.put(name2, vertex2);
		}

		// add new directed edge from vertex1 to vertex2
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
				dot.append("\t\"" + vertices.get(str).getName() + "\" -> \"" + edges.next().getName() + "\"\n");
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
			result.append(vertices.get(str) + "\n");	
		}
		
		return result.toString();
	}

	private void dfsRecursive() {

	}

	private boolean edgeVisitor(Vertex<T> current, T dest, List<Vertex<T>> visited) {
		for (Iterator<Vertex<T>> it = current.edges(); it.hasNext(); ) {
			Vertex<T> vertex = it.next();

			if (vertex.getValue().equals(dest)) {
				return true;
			}

			if (! visited.contains(current)) {
				visited.add(current);

				return edgeVisitor(vertex, dest, visited);
			}
		}
		return false;
	}

	public boolean depthFirstSearch(T source, T destination) {
		List<Vertex<T>> visited = new ArrayList<Vertex<T>>();
		Vertex<T> current = vertices.get(source);

		// Go to all connected
		return edgeVisitor(current, destination, visited);
	}

	public List<T> breadthFirstSearch(T source, T destination){
		Queue<Vertex<T>> queue = new LinkedList<Vertex<T>>();
		queue.add(vertices.get(source));
		List<T> pathFound = new ArrayList<>();
		List<T> visited = new ArrayList<>();

		Vertex<T> current = vertices.get(source);

		while (!queue.isEmpty()) {
			current = queue.poll();

			if (current.getValue().equals(destination)) {
				return pathFound;
			}

			if (! visited.contains(current.getValue())) {
				visited.add(current.getValue());
				for (Iterator<Vertex<T>> it = current.edges(); it.hasNext(); ) {
					Vertex<T> vertex = it.next();

					if (! visited.contains(vertex.getValue())) {
						queue.add(vertex);
					}
					pathFound.add(vertex.getValue());
				}

			}
		}

		return new ArrayList<>();
	}
}
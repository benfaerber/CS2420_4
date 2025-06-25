package assign07;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

/**
 * This class represents a vertex in a directed graph. The vertex is
 * not generic, assumes that a string name is stored there.
 * 
 * @author Eric Heisler
 * @version 2025-6-17
 */
public class Vertex<T> {

	private T value;
	private Vertex<T> parent;  // Added for BFS parent tracking
	private boolean visited;
	private int indegree;

	private List<Vertex<T>> adjacent;

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param value - string used to identify this Vertex
	 */
	public Vertex(T value) {
		this.value = value;
		this.adjacent = new LinkedList<Vertex<T>>();
		this.parent = null;
		this.visited = false;
		this.indegree = 0;
	}

	/**
	 * @return the string used to identify this Vertex
	 */
	public T getName() {
		return value;
	}


	public T getValue() {
		return value;
	}


	/**
	 * Adds a directed edge from this Vertex to another.
	 * 
	 * @param otherVertex - the Vertex object that is the destination of the edge
	 */
	public void addEdge(Vertex<T> otherVertex) {
		adjacent.add(otherVertex);
		otherVertex.addIndegree(1);
	}

	/**
	 * @return an iterator for accessing the edges for which this Vertex is the source
	 */
	public Iterator<Vertex<T>> edges() {
		return adjacent.iterator();
	}

	/**
	 * Generates and returns a textual representation of this Vertex.
	 */
	public String toString() {
		String s = value + " is adjacent to ";
		Iterator<Vertex<T>> itr = edges();
		while (itr.hasNext()) {
			s += itr.next().value + "  ";
		}
		return s;
	}

	/**
	 * @return a list of vertices that are directly reachable from this vertex
	 */
	public List<Vertex<T>> getNeighbors() {
		return new LinkedList<>(adjacent);
	}

	/**
	 * Get the parent of the vertex
	 * @return the parent vertex
	 */
	public Vertex<T> getParent() {
		return parent;
	}

	/**
	 * Set the varent of the vertex
	 * @param parent the parent to set
	 */
	public void setParent(Vertex<T> parent) {
		this.parent = parent;
	}

	/**
	 * Check if this node is visited
	 * @return true if visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * Set if item is visited
	 * @param visited is visited?
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * Set the indegree
	 * @param indegree
	 */
	public void setIndegree(int indegree) {
		this.indegree = indegree;
	}

	/**
	 * Add to the indegree
	 * @param indegree to add
	 */
	public void addIndegree(int indegree) {
		this.indegree += indegree;
	}

	/**
	 * Get indegree
	 * @return indegree
	 */
	public int getIndegree() {
		return indegree;
	}

}
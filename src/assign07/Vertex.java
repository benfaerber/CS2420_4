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

	// data held in the Vertex
	private T value;

	// adjacency list
	// We have a choice
	private List<Vertex<T>> adjacent;
	// or
	//private List<Edge> adjacent; // <- Good for weighted graphs

	/**
	 * Creates a new Vertex object, using the given name.
	 * 
	 * @param value - string used to identify this Vertex
	 */
	public Vertex(T value) {
		this.value = value;
		this.adjacent = new LinkedList<Vertex<T>>();
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
}
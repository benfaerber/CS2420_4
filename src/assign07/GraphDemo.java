package assign07;

/**
 * Demonstrates the Graph class.
 * 
 * @author Eric Heisler
 * @version 2025-6-17
 */
public class GraphDemo {

	public static void main(String[] args) {
		
		// build a sample directed graph
		Graph<String> sample = new Graph<String>();
		sample.addEdge("a", "b");
		sample.addEdge("b", "c");
		sample.addEdge("c", "d");
		sample.addEdge("b", "d");
		sample.addEdge("d", "a");

		// print textual representation of the graph
		System.out.println(sample);
		
		// print DOT representation of the graph (paste into http://www.webgraphviz.com)
		System.out.println(sample.generateDot());
	}
}
package assign07;

import java.util.List;

public class GraphUtility {
    /**
     * A private method to construct a graph from a list of sources and destinations
     * @param sources A list of the sources
     * @param destinations A list of the destinations
     * @return A constructed graph
     * @param <T> The type the list and the graph contains
     */
    private static <T> Graph<T> buildGraphFromLists(List<T> sources, List<T> destinations) {
        Graph<T> graph = new Graph<>();
        if (sources.size() != destinations.size()) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < sources.size(); i++) {
            graph.addEdge(sources.get(i), destinations.get(i));
        }
        return graph;
    }

    /**
     * Determine if 2 points are connected on a graph
     * @param sources A list of sources
     * @param destinations A list of destinations
     * @param srcData Where to start
     * @param dstData Where to end
     * @return If the 2 points are connected
     * @param <T> The type contained
     */
    public static <T> boolean areConnected(List<T> sources, List<T> destinations, T srcData, T dstData) {
        Graph<T> graph = buildGraphFromLists(sources, destinations);
        return graph.depthFirstSearch(srcData, dstData);
    }

    /**
     * Finds the shortest path in a graph or null if not found
     * @param sources A list of sources
     * @param destinations A list of destinations
     * @param srcData Where to start
     * @param dstData Where to end
     * @return A list of the path or null if not found
     * @param <T> The type contained in the Graph
     */
    public static <T> List<T> shortestPath(List<T> sources, List<T> destinations, T srcData, T dstData) {
        Graph<T> graph = buildGraphFromLists(sources, destinations);
        return graph.breadthFirstSearch(srcData, dstData);
    }

    /**
     * Sorts a graph in topological order
     * @param sources A list of sources
     * @param destinations A list of destinations
     * @return The nodes sorted in topological order
     * @param <T> The type the graph contains
     */
    public static <T> List<T> sort(List<T> sources, List<T> destinations) {
        Graph<T> graph = buildGraphFromLists(sources, destinations);
        return graph.topoSort();
    }
}

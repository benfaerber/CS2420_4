package assign07;

import java.util.List;

public class GraphUtility {
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

    public static <T> boolean areConnected(List<T> sources, List<T> destinations, T srcData, T dstData) {
        Graph<T> graph = buildGraphFromLists(sources, destinations);
        return graph.depthFirstSearch(srcData, dstData);
    }

    public static <T> List<T> shortestPath(List<T> sources, List<T> destinations, T srcData, T dstData) {
        Graph<T> graph = buildGraphFromLists(sources, destinations);
        return graph.breadthFirstSearch(srcData, dstData);
    }

    public static <T> List<T> sort(List<T> sources, List<T> destinations) {
        Graph<T> graph = buildGraphFromLists(sources, destinations);
        return graph.topoSort();
    }
}

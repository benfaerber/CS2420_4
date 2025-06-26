package assign07;

import java.util.Random;

import timing.TimingExperiment;

/**
 * Experiment to measure the running time of topological sort for random acyclic
 * sparse graphs with a range of vertex counts.
 *
 * @author CS 2420 course staff
 * @version February 27, 2025
 */
public class DepthFirstSearchSparseTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "vertexCount";
    private static int problemSizeMin = 5000;
    private static int problemSizeCount = 500;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 100;

    private final Random rng = new Random();

    private Graph<Integer> acyclicRandomGraph;

    private Vertex<Integer> randomSource;
    private Vertex<Integer> randomDestination;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new TopoSortSparseTimingExperiment();
        timingExperiment.printResults();
    }

    public DepthFirstSearchSparseTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep,    experimentIterationCount);
    }

    /**
     * Setup experiment for problemSize by generating a sparse acyclic random graph.
     * 
     * @param problemSize - number of vertices
     */
    protected void setupExperiment(int problemSize) {
    	// V = {0, 1, ..., problemSize - 1}
    	acyclicRandomGraph = new Graph<Integer>();
    	   	
    	// |E| = 2 * problemSize
        for(int i = 0; i < 2 * problemSize; i++) {
        	// Add random edge between two random vertices.
        	int sourceVertexData = rng.nextInt(problemSize - 1);
        	int destinationVertexData = rng.nextInt(sourceVertexData + 1, problemSize);
        	acyclicRandomGraph.addEdge(sourceVertexData, destinationVertexData);
        }

        this.randomSource = acyclicRandomGraph.getRandomVertex();
        this.randomDestination = acyclicRandomGraph.getRandomVertex();
    }

    /**
     * Run the topoSort method.
     */
    protected void runComputation() {
        acyclicRandomGraph.depthFirstSearch(randomSource.getValue(), randomDestination.getValue());
    }
}
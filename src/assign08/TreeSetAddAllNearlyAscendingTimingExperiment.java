package assign08;

import java.util.ArrayList;
import java.util.TreeSet;

import timing.ArrayListGenerator;
import timing.TimingExperiment;

/**
 * Experiment to measure the running times of adding a nearly-ascending ordered collection 
 * of elements to a BST via addAll, for various problem sizes.
 *
 * @author CS 2420 course staff
 * @version March 6, 2025
 */
public class TreeSetAddAllNearlyAscendingTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "element count";
    private static int problemSizeMin = 10000;
    private static int problemSizeCount = 10;
    private static int problemSizeStep = 5000;
    // Due to larger running times, this iteration count can be less than for BSTAddAllPermutedTimingExperiment.
    private static int experimentIterationCount = 25; 
    
    protected TreeSet<Integer> sortedSet;
    protected ArrayList<Integer> elements;
        
    public static void main(String[] args) {
        TimingExperiment timingExperiment = new TreeSetAddAllNearlyAscendingTimingExperiment();
        timingExperiment.printResults();
    }

    public TreeSetAddAllNearlyAscendingTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

	/**
	 * Creates an empty BST and generates an ArrayList with problemSize random integers in nearly 
	 * ascending order, with each element unique.
	 * 
	 * @param problemSize - the number of integers to fill the ArrayList
	 */
    protected void setupExperiment(int problemSize) {
    	sortedSet = new TreeSet<>();
    	elements = ArrayListGenerator.generateNearlyAscendingArrayListWithoutDuplicates(problemSize);
    }
    
	/**
	 * Runs the addAll method for the BST.
	 */
    protected void runComputation() {
    	sortedSet.addAll(elements);
    }
}
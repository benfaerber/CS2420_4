package assign04;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Timing experiment for determining add to a set
 *
 * @author Benjamin Faerber
 * @version 2025-05-22
 */
public class LargestAnagramGroupTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "Word List Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 500;
    private static int problemSizeStep = 1;
    private static int experimentIterationCount = 30;

    private static String[] loadedList;
    private static String[] partialList;

    public static void main(String[] args) {
        loadedList = AnagramChecker.readFileLines("random_anagrams.txt");

        TimingExperiment timingExperiment = new LargestAnagramGroupTimingExperiment();
        timingExperiment.printResults();
    }

    public LargestAnagramGroupTimingExperiment() {
        super(
            problemSizeDescription,
            problemSizeMin,
            problemSizeCount,
            problemSizeStep,
            experimentIterationCount
        );
    }

    /**
     * Populate the set with the given number of elements and define an element to search for.
     * @param problemSize - the size of the set
     */
    @Override
    protected void setupExperiment(int problemSize) {
    	partialList = new String[problemSize];
    	for (int i = 0; i < partialList.length; i++) {
    		partialList[i] = loadedList[i];
    	}
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
    	AnagramChecker.getLargestAnagramGroup(this.partialList);
    }
}

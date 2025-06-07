package assign03;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Timing experiment for determining add to a set
 *
 * @author Benjamin Faerber
 * @version 2025-05-22
 */
public class ArraySetAddTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "addToSet";
    private static int problemSizeMin = 0;
    private static int problemSizeCount = 500;
    private static int problemSizeStep = 10;
    private static int experimentIterationCount = 30;

    private ArraySet<Integer> set = new ArraySet<Integer>();
    private Integer value = 1;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArraySetAddTimingExperiment();
        timingExperiment.printResults();
    }

    public ArraySetAddTimingExperiment() {
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
        this.set.clear();
        this.value = problemSize;
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        for (int i = 0; i < this.value; i++) {
        	this.set.add(i);
        }
    }
}

package assign10;

import timing.TimingExperiment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Timing experiment for FindKSortTimingExperiment
 *
 * @author Benjamin Faerber
 * @version 2025-07-17
 */
public class FindKSort10TimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "List Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 100;
    private static int problemSizeStep = 1000;
    private static int experimentIterationCount = 30;

    private ArrayList<Integer> toAdd = new ArrayList<>();
    private Integer chosenK = 0;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new FindKSort10TimingExperiment();
        timingExperiment.printResults();
    }

    public FindKSort10TimingExperiment() {
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
        chosenK = 5;
        for (int i = 1; i <= problemSizeCount; i++) {
            toAdd.add(i);
        }
        Collections.shuffle(toAdd);
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        FindKLargest.findKLargestSort(toAdd, chosenK);
    }
}

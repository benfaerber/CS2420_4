package assign05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import timing.TimingExperiment;

/**
 * Timing experiment for ListSorter.mergesort
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class MergeSortTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "ArrayList Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 50_000;
    private static int problemSizeStep = 1;
    private static int experimentIterationCount = 25;

    private List<Integer> toSort = new ArrayList<>();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new MergeSortTimingExperiment();
        timingExperiment.printResults();
    }

    public MergeSortTimingExperiment() {
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
        this.toSort = ListSorter.generatePermuted(problemSize);
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        ListSorter.mergesort(this.toSort, 1);
    }

}

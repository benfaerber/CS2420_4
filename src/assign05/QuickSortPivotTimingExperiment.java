package assign05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import assign05.*;
import timing.TimingExperiment;

/**
 * Timing experiment for QuickSort Pivot
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class QuickSortPivotTimingExperiment extends TimingExperiment {

    private static final String problemSizeDescription = "ArrayList Size";
    private static final int problemSizeMin = 1;
    private static final int problemSizeCount = 20_000;
    private static int problemSizeStep = 1;
    private static int experimentIterationCount = 10;

    private List<Integer> toSort;
    private PivotChooser<Integer> medianChooser;
    private PivotChooser<Integer> firstChooser;
    private PivotChooser<Integer> randomChooser;


    public static void main(String[] args) {
        TimingExperiment timingExperiment = new QuickSortPivotTimingExperiment();
        timingExperiment.printResults();
    }

    public QuickSortPivotTimingExperiment() {
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
        this.medianChooser = new MedianOfThreePivotChooser<>();
        this.firstChooser = new FirstPivotChooser<>();
        this.randomChooser = new RandomPivotChooser<>();

        this.toSort = ListSorter.generatePermuted(problemSize);
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        ListSorter.quicksort(this.toSort, this.randomChooser);
    }
}

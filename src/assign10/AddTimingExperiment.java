package assign10;

import timing.TimingExperiment;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Timing experiment for Add To Binary Max Heap
 *
 * @author Benjamin Faerber
 * @version 2025-07-17
 */
public class AddTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "Heap Size";
    private static int problemSizeMin = 100;
    private static int problemSizeCount = 1000;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 30;

    private BinaryMaxHeap<Integer> heap;
    private ArrayList<Integer> toAdds;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new AddTimingExperiment();
        timingExperiment.printResults();
    }

    public AddTimingExperiment() {
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
        toAdds = new ArrayList<>();
        for (int i = 0; i < problemSizeCount; i++) {
            toAdds.add(i);
        }
        Collections.shuffle(toAdds);
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        heap = new BinaryMaxHeap<>();
        for (Integer i : toAdds) {
            heap.add(i);
        }
    }
}

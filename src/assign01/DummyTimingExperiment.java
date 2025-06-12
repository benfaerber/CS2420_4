package assign01;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class DummyTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "String size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 300;
    private static int problemSizeStep = 1;
    private static int experimentIterationCount = 30;


    public static void main(String[] args) {
        TimingExperiment timingExperiment = new DummyTimingExperiment();
        timingExperiment.printResults();
    }

    public DummyTimingExperiment() {
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

    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {

    }
}

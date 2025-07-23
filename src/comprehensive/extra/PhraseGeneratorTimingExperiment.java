package comprehensive.extra;

import comprehensive.RandomPhraseGenerator;
import timing.TimingExperiment;
import timing.TimingWithTimestampExperiment;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class PhraseGeneratorTimingExperiment extends TimingWithTimestampExperiment {

    private static String problemSizeDescription = "Phrase Count";
    private static int problemSizeMin = 100_000;
    private static int problemSizeCount = 1;
    private static int problemSizeStep = 5_00;
    private static int experimentIterationCount = 2;

    private int currentProblemSize = 0;

    public static void main(String[] args) {
        TimingWithTimestampExperiment timingExperiment = new PhraseGeneratorTimingExperiment();
        timingExperiment.printResults();
    }

    public PhraseGeneratorTimingExperiment() {
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
        this.currentProblemSize = problemSize;
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        try {
            // Just run main to be accurate to gradescope
            RandomPhraseGenerator.main(new String[] {
                    "src/comprehensive/examples/fruit.g",
                    this.currentProblemSize + "",
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

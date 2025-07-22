package comprehensive.extra;

import comprehensive.RandomPhraseGenerator;
import timing.TimingExperiment;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class PhraseGeneratorTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "Phrase Count";
    private static int problemSizeMin = 10;
    private static int problemSizeCount = 300;
    private static int problemSizeStep = 50;
    private static int experimentIterationCount = 2;

    private int currentProblemSize = 0;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new PhraseGeneratorTimingExperiment();
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

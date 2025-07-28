package comprehensive.extra;

import comprehensive.Grammar;
import comprehensive.ValueRandom;
import timing.TimingExperiment;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class IncreasePhraseTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "Phrase Count";
    private static int problemSizeMin = 100;
    private static int problemSizeCount = 50;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 30;

    private int phraseCount;
    private Grammar grammar;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new IncreasePhraseTimingExperiment();
        timingExperiment.printResults();
    }

    public IncreasePhraseTimingExperiment() {
        super(
                problemSizeDescription,
                problemSizeMin,
                problemSizeCount,
                problemSizeStep,
                experimentIterationCount
        );
        this.grammar = Grammar.fromExampleFile("fruit.g", new ValueRandom());
    }

    /**
     * Populate the set with the given number of elements and define an element to search for.
     * @param problemSize - the size of the set
     */
    @Override
    protected void setupExperiment(int problemSize) {
        this.phraseCount = problemSize;
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        for (int i = 0; i < phraseCount; i++) {
            grammar.randomPhrase();
        }
    }
}

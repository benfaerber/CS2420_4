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
public class IncreaseRulesTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "Rule Count";
    private static int problemSizeMin = 100;
    private static int problemSizeCount = 50;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 30;

    private int ruleCount;
    private Grammar grammar;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new IncreaseRulesTimingExperiment();
        timingExperiment.printResults();
    }

    public IncreaseRulesTimingExperiment() {
        super(
                problemSizeDescription,
                problemSizeMin,
                problemSizeCount,
                problemSizeStep,
                experimentIterationCount
        );
        this.grammar = Grammar.fromExampleFile("fruit.g", new ValueRandom());
    }

    private static String generateFakeGrammar(int count) {
        StringBuilder sb = new StringBuilder();

        sb.append("{\n<start>\n");
        for (int i = 0; i < count; i++) {
            sb.append("<").append(varName(i)).append(">");
        }
        sb.append("\n}\n\n");

        for (int i = 0; i < count; i++) {
            String var = varName(i);
            sb.append("{\n<").append(var).append(">\n");
            sb.append(var).append("\n");
            sb.append("<").append(var).append(">").append(var).append("\n");
            sb.append("}\n\n");
        }

        return sb.toString();
    }

    private static String varName(int index) {
        StringBuilder name = new StringBuilder();
        index++;
        while (index > 0) {
            index--;
            name.insert(0, (char) ('a' + (index % 26)));
            index /= 26;
        }
        return name.toString();
    }

    /**
     * Populate the set with the given number of elements and define an element to search for.
     * @param problemSize - the size of the set
     */
    @Override
    protected void setupExperiment(int problemSize) {
        this.ruleCount = problemSize;
        String fakeGrammarText = generateFakeGrammar(ruleCount);
        this.grammar = Grammar.fromText("fake_grammar_" + ruleCount, fakeGrammarText);
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        for (int i = 0; i < 1_000; i++) {
            this.grammar.randomPhrase();
        }
    }
}

package comprehensive;

import java.util.*;

/**
 * A single line in a grammar
 *
 * This is represented as a list of GrammarTokens
 * Example: "The <adjective> <color> fox jumped over the <adjective> <noun>"
 *
 * @author Benjamin Faerber and David Chen
 * @version July 28, 2025
 */
public class GrammarLine {
    private ArrayList<GrammarToken> tokens;
    private boolean hasVars;
    private int sizeGuess;

    /**
     * Create a GrammarLine from a list of GrammarTokens.
     *
     * @param tokens a list of tokens in the line
     */
    public GrammarLine(ArrayList<GrammarToken> tokens) {
        this.tokens = tokens;
        for (GrammarToken token : tokens) {
            if (token.isVariable()) {
                hasVars = true;
            } else {
                sizeGuess += token.getContent().length();
            }
        }
    }

    /**
     * Using a given grammar, evaluate this line.
     *
     * @param grammar the grammar to use for evaluation
     * @return the rendered grammar line
     */
    public String evaluate(Grammar grammar) {
        if (!hasVars) {
            // To try and be speedy, if it doesn't have any vars
            // just do this simple operation
            StringBuilder sb = new StringBuilder(sizeGuess);
            for (GrammarToken token : tokens) {
                sb.append(token.getContent());
            }
            return sb.toString();
        }

        return evaluateWithVariables(grammar);
    }

    /**
     * If a line has variables it must be expanded.
     * This evaluates variables recursively.
     *
     * @param grammar The grammar to use for evaluation
     * @return the rendered grammar line
     */
    private String evaluateWithVariables(Grammar grammar) {
        StringBuilder output = new StringBuilder(sizeGuess * 2); // Estimate 2x for expansions
        evaluateTokensRecursive(grammar, tokens, output);
        return output.toString();
    }

    /**
     * This is a recursive method of evaluating a line.
     * It loops over the tokens and renders them.
     *
     * @param grammar the grammar to use for evaluation
     * @param tokenList a list of tokens to evaluate
     * @param output the StringBuilder to output data to
     */
    private void evaluateTokensRecursive(Grammar grammar, List<GrammarToken> tokenList, StringBuilder output) {
        for (GrammarToken token : tokenList) {
            if (token.isText()) {
                output.append(token.getContent());
                continue;
            }

            if (token.isVariable()) {
                GrammarLine replacement = grammar.randomLineInSection(token.getContent());
                if (replacement != null) {
                    evaluateTokensRecursive(grammar, replacement.tokens, output);
                }
            }
        }
    }

    /**
     * A string representation of the grammar line
     * @return a string representation of the grammar line
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        for (GrammarToken token : tokens) {
            joiner.add(token.toString());
        }
        return "[" + joiner.toString() + "]";
    }
}

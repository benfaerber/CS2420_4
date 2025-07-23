package comprehensive;

import java.util.*;

public class GrammarLine {
    private ArrayList<GrammarToken> tokens;
    private boolean hasVars;
    private int sizeGuess;

    /**
     * I'm guessing the average line will be 200
     */
    private int SIZE_GUESS = 200;

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

    private String evaluateWithVariables(Grammar grammar) {
        StringBuilder output = new StringBuilder(sizeGuess * 2); // Estimate 2x for expansions
        evaluateTokensRecursive(grammar, tokens, output);
        return output.toString();
    }

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
                continue;
            }
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        for (GrammarToken token : tokens) {
            joiner.add(token.toString());
        }
        return "[" + joiner.toString() + "]";
    }
}
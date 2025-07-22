package comprehensive;

import java.util.*;

public class GrammarLine {
    private ArrayList<GrammarToken> tokens;
    public GrammarLine(ArrayList<GrammarToken> tokens) {
        this.tokens = tokens;
    }

    public String evaluate(Grammar grammar) {
        StringBuilder output = new StringBuilder();
        Deque<GrammarToken> stack = new ArrayDeque<>(tokens);
        while (!stack.isEmpty()) {
            GrammarToken token = stack.pollFirst();

            if (token.isText()) {
                output.append(token.getContent());
            } else if (token.isVariable()) {
                GrammarLine replacement = grammar.randomLineInSection(token.getContent());
                List<GrammarToken> replacementTokens = replacement.tokens;
                for (int i = replacementTokens.size() - 1; i >= 0; i--) {
                    stack.addFirst(replacementTokens.get(i));
                }
            }
        }

        return output.toString();
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
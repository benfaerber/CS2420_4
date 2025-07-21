package comprehensive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

public class GrammarLine {
    private ArrayList<GrammarToken> tokens;
    public GrammarLine(ArrayList<GrammarToken> tokens) {
        this.tokens = tokens;
    }

    public String evaluate(Grammar grammar) {
        StringBuilder builder = new StringBuilder();

        for (GrammarToken token : tokens) {
            if (token.isText()) {
                builder.append(token.getContent());
                continue;
            }

            if (token.isVariable()) {
                GrammarLine varLine = grammar.randomLineInSection(token.getContent());
                String varEval = varLine.evaluate(grammar);
                builder.append(varEval);
            }
        }

        return builder.toString();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(" ");
        for (GrammarToken token : tokens) {
            joiner.add(token.toString());
        }
        return joiner.toString();
    }
}
package comprehensive;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.Callable;

class PhraseBatchGenerator implements Callable<String> {
    private final Grammar grammar;
    private final int count;

    public PhraseBatchGenerator(Grammar grammar, int count) {
        this.grammar = grammar;
        this.count = count;
    }

    @Override
    public String call() {
        StringJoiner phrases = new StringJoiner("\n");
        for (int i = 0; i < count; i++) {
            phrases.add(grammar.randomPhrase());
        }
        return phrases.toString();
    }
}

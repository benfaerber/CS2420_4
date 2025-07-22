package comprehensive;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class PhraseBatchGenerator implements Callable<List<String>> {
    private final Grammar grammar;
    private final int count;

    public PhraseBatchGenerator(Grammar grammar, int count) {
        this.grammar = grammar;
        this.count = count;
    }

    @Override
    public List<String> call() {
        List<String> phrases = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            phrases.add(grammar.randomPhrase());
        }
        return phrases;
    }
}

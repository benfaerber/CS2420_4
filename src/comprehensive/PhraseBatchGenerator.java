package comprehensive;

import java.util.StringJoiner;
import java.util.concurrent.Callable;

/**
 * A batch of phrase generation.
 * This is so you can generate phrases in chunks on different threads.
 *
 * @author Benjamin Faerber and David Chen
 * @version July 28, 2025
 */
class PhraseBatchGenerator implements Callable<String> {
    private final Grammar grammar;
    private final int count;

    /**
     * Create a new PhraseBatchGenerator.
     *
     * @param grammar the grammar to use
     * @param count the random phrases in this batch
     */
    public PhraseBatchGenerator(Grammar grammar, int count) {
        this.grammar = grammar;
        this.count = count;
    }

    /**
     * This is what is executed when Future.get is called.
     *
     * @return The chunk output
     */
    @Override
    public String call() {
        StringJoiner phrases = new StringJoiner("\n");
        for (int i = 0; i < count; i++) {
            phrases.add(grammar.randomPhrase());
        }
        return phrases.toString();
    }
}

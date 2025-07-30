package comprehensive;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * This class is used to generate random phrases based on a grammar defined in a text file.
 * The grammar specifies nonterminal symbols and their corresponding production rules.
 * The generator expands these rules recursively to produce complete phrases.
 *
 * @author Benjamin Faerber and David Chen
 * @version July 28, 2025
 **/
public class RandomPhraseGenerator {
    private static int BATCH_SIZE = 100;

    /**
     * The buffered writer is much better performance wise but messes with the order the output is display in
     * (it doesn't lock like println so the order will be weird)
     */
    private static boolean USE_BUFFERED_WRITER = false;

    /**
     * The main method ran when the program is ran from the CLI.
     *
     * @param args the CLI arguments
     * @throws IOException If it failed to read the file
     * @throws ExecutionException If it failed to execute
     * @throws InterruptedException If the threading gets interrupted
     */
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        if (args.length == 0) {
            System.out.println("Usage: java comprehensive.RandomPhraseGenerator <filename.g>");
            System.exit(0);
            return;
        }

        int phraseCount = 1;
        if (args.length == 2) {
            phraseCount = Integer.parseInt(args[1]);
        }

        Path filepath;
        try {
            filepath = Path.of(args[0]);
        } catch (Exception e) {
            throw new RuntimeException("Invalid path: " + args[0]);
        }

        Grammar grammar = Grammar.fromFile(filepath);
        executeBulkPhraseGenerator(grammar, phraseCount);
    }

    /**
     * This chunks up the phrase generation and uses multithreading to quickly
     * generate a bunch of phrases.
     *
     * @param grammar the grammar used to randomly generate
     * @param phraseCount how many phrases to generate
     * @throws ExecutionException If the Future throws an execution
     * @throws InterruptedException If the Future gets interrupted
     */
    private static void executeBulkPhraseGenerator(Grammar grammar, int phraseCount) throws ExecutionException, InterruptedException {
        int batchCount = (int) Math.ceil(phraseCount / (double) BATCH_SIZE);

        int threads = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < batchCount; i++) {
            int count = BATCH_SIZE;
            if (i == batchCount - 1) {
                count = phraseCount - i * BATCH_SIZE;
            }
            futures.add(pool.submit(new PhraseBatchGenerator(grammar, count)));
        }

        if (USE_BUFFERED_WRITER) {
            bufferedWriter(futures);
        } else {
            printlnWriter(futures);
        }
        pool.shutdown();
    }

    /**
     * Print the output using System.out.println.
     *
     * @param futures a list of batch generation futures
     * @throws ExecutionException If the Future throws an execution
     * @throws InterruptedException If the Future gets interrupted
     */
    private static void printlnWriter(List<Future<String>> futures) throws ExecutionException, InterruptedException {
        StringJoiner sb = new StringJoiner("\n");
        for (Future<String> future : futures) {
            sb.add(future.get());
        }
        System.out.println(sb);
    }

    /**
     * Print the output using a buffered writer.
     *
     * @param futures a list of batch generation futures
     */
    private static void bufferedWriter(List<Future<String>> futures) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(System.out), 50_000)) {
            for (Future<String> future : futures) {
                writer.write(future.get());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package comprehensive;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomPhraseGenerator {
    private static int BATCH_SIZE = 10_000;

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

        int batchCount = (int) Math.ceil(phraseCount / (double) BATCH_SIZE);

        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < batchCount; i++) {
            int count = batchCount;
            if (i == batchCount - 1) {
                count = phraseCount - i * BATCH_SIZE;
            }
            futures.add(pool.submit(new PhraseBatchGenerator(grammar, count)));
        }

        for (Future<String> future : futures) {
            String batchPhrases = future.get();
            System.out.println(batchPhrases);
        }
        pool.shutdown();
    }
}

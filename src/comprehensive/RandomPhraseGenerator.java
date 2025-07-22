package comprehensive;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomPhraseGenerator {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Usage: java comprehensive.RandomPhraseGenerator <filename.g>");
            System.exit(0);
            return;
        }

        int phraseCount = 1;
        if (args.length == 2) {
            phraseCount = Integer.parseInt(args[1]);
        }

        boolean quiet = false;
        if (args.length == 3) {
            quiet = args[2].equals("-q") || args[2].equals("--quiet");
        }

        Path filepath;
        try {
            filepath = Path.of(args[0]);
        } catch (Exception e) {
            throw new RuntimeException("Invalid path: " + args[0]);
        }

        String grammarRawContent = Files.readString(filepath);
        String grammarName = filepath.getFileName().toString();
        Grammar grammar = Grammar.fromText(grammarName, grammarRawContent);

        singleThreadGeneration(grammar, phraseCount, quiet);
    }

    private static void multithreadedGeneration(Grammar grammar, int phraseCount, boolean quiet) {
        String output = IntStream.range(0, phraseCount)
                .parallel()
                .mapToObj(i -> {
                    return grammar.randomPhrase();
                })
                .collect(Collectors.joining("\n"));
        if (!quiet) {
            System.out.println(output);
        }
    }

    private static void singleThreadGeneration(Grammar grammar, int phraseCount, boolean quiet) {
        for (int i = 0; i < phraseCount; i++) {
            String phrase = grammar.randomPhrase();
            if (!quiet) {
                System.out.println(phrase);
            }
        }
    }
}

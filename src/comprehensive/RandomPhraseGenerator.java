package comprehensive;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringJoiner;
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

        Path filepath;
        try {
            filepath = Path.of(args[0]);
        } catch (Exception e) {
            throw new RuntimeException("Invalid path: " + args[0]);
        }

        String grammarRawContent = Files.readString(filepath);
        String grammarName = filepath.getFileName().toString();
        Grammar grammar = Grammar.fromText(grammarName, grammarRawContent);

        StringJoiner buffer = new StringJoiner("\n");
        for (int i = 0; i < phraseCount; i++) {
            String phrase = grammar.randomPhrase();
            buffer.add(phrase);

            int BUFFER_SIZE = 1_000;
            if (buffer.length() > BUFFER_SIZE) {
                System.out.println(buffer);
                buffer = new StringJoiner("\n");
            }
        }

        System.out.println(buffer);
    }
}

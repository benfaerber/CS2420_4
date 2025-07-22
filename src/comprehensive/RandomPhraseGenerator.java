package comprehensive;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
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

        List<String> phrases = IntStream.range(0, phraseCount)
                .parallel()
                .mapToObj(i -> grammar.randomPhrase())
                .toList();

        int BUFFER_SIZE = 200;

        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < phrases.size(); i++) {
            buffer.append(phrases.get(i)).append("\n");
            if ((i + 1) % BUFFER_SIZE == 0) {
                System.out.print(buffer);
                buffer.setLength(0);
            }
        }
        if (!buffer.isEmpty()) {
            System.out.print(buffer);
        }
    }
}

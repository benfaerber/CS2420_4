package comprehensive;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class RandomPhraseGenerator {

    public static void main(String[] args) {
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

        for (int i = 0; i < phraseCount; i++) {
            String phrase = grammar.randomPhrase();
            System.out.println(phrase);
        }

    }
}

package comprehensive;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Grammar file (a .g file)
 *
 * This parses grammar files and allows you to generate random phrases.
 * To get started use "Grammar.fromText" or "Grammar.fromFile"
 */
public class Grammar implements Iterable<GrammarSection> {
    private final String name;
    private static String START_SECTION = "start";

    private final HashMap<String, GrammarSection> sections;
    private final RandomProvider random;

    /**
     * Create a named Grammar file from a list of sections
     * @param name the file name (ie grammar.g)
     * @param sectionsList a list of the GrammarSections
     * @param random A RandomProvider. For normal use, use ValueRandom and for unit testing use MockRandom
     */
    public Grammar(String name, ArrayList<GrammarSection> sectionsList, RandomProvider random) {
        this.name = name;

        sections = new HashMap<>();
        for (GrammarSection section : sectionsList) {
            sections.put(section.getName(), section);
        }
        this.random = random;
    }

    /**
     * Create a named grammar from raw text
     * @param name the desired name of the grammar
     * @param text the raw text content of the grammar
     * @return A Grammar based on your input text
     */
    public static Grammar fromText(String name, String text) {
        return GrammarParser.parseGrammarFromText(name, text, new ValueRandom());
    }

    /**
     * Create a grammar from a file (.g file)
     * @param path The filepath to your grammar file
     * @return A newly parsed grammar of your file
     */
    public static Grammar fromFile(Path path) {
        return GrammarParser.parseGrammarFromFile(path, new ValueRandom());
    }

    /**
     * Create a grammar from a file (.g file)
     * This method allows you to add a random provider for testing purposes
     * @param path The filepath to your grammar file
     * @param random A RandomProvider (source of randomness)
     * @return A newly parsed grammar from your file
     */
    public static Grammar fromFile(Path path, RandomProvider random) {
        return GrammarParser.parseGrammarFromFile(path, random);
    }

    /**
     * Load an example file from the "examples" directory. Used for TimingExperiments and testing
     * @param fileName the name of the example file
     * @param random the RandomProvider (either Mock or Value)
     * @return A newly parsed grammar from your file
     */
    public static Grammar fromExampleFile(String fileName, RandomProvider random) {
        return GrammarParser.parseGrammarFromExamples(fileName, random);
    }

    /**
     * Iterate over grammar sections
     * @return An iterator of grammar sections
     */
    public Iterator<GrammarSection> iterator() {
        return sections.values().iterator();
    }

    /**
     * Get a random line in a named section
     * @param name a section name
     * @return a random GrammarLine
     */
    public GrammarLine randomLineInSection(String name) {
        GrammarSection section = sections.get(name);
        return section.randomLine();
    }

    /**
     * Generate a random phrase based on this grammar
     * @return a random phrase
     */
    public String randomPhrase() {
        GrammarSection startSection = sections.get(START_SECTION);
        GrammarLine startLine = startSection.randomLine();

        return startLine.evaluate(this);
    }

    /**
     * Convert this grammar into a string display
     * @return a string representation of the grammar
     */
    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n\n");
        for (GrammarSection section : this) {
            sj.add(section.toString());
        }
        return name + "\n---------" + sj.toString() + "-------";
    }

    /**
     * A Parser class to hide the encapsulate parsing of the file
     */
    private static class GrammarParser {
        private static final Pattern SECTION_REGEX = Pattern.compile(
                "\\{\\n" // Start with opening brackets
                        + "<(.+?)>\\n" // Now a variable surrounded by angle brackets, followed by a newline
                        + "([\\s\\S]*?)" // All the content (the *? means as few as possible, so it doesn't eat all the sections)
                        + "\\}", // And finally close off the bracket!
                Pattern.DOTALL);

        /**
         * Extract a list of sections from raw text
         * @param content The raw content of the grammar
         * @param random The source of randomness
         * @return A list of grammar sections
         */
        private static ArrayList<GrammarSection> parseSections(String content, RandomProvider random) {
            Matcher matcher = SECTION_REGEX.matcher(content);
            ArrayList<GrammarSection> sections = new ArrayList<>();
            while (matcher.find()) {
                GrammarSection section = new GrammarSection(matcher.group(1), matcher.group(2), random);
                sections.add(section);
            }
            return sections;
        }

        /**
         * Read a file from a string and return a runtime error on failure
         * @param filePath The file path to load
         * @return The string contents of the file
         */
        private static String readFile(Path filePath) {
            try {
                return Files.readString(filePath);
            } catch (Exception e) {
                throw new RuntimeException("Failed to read file! Context: " + e);
            }
        }

        /**
         * Parse a grammar from text
         * @param name the name of the grammar
         * @param rawContent the raw content of the grammar
         * @param random the source of randomness
         * @return the parsed grammar
         */
        public static Grammar parseGrammarFromText(String name, String rawContent, RandomProvider random) {
            ArrayList<GrammarSection> sections = parseSections(rawContent, random);
            return new Grammar(name, sections, random);
        }

        /**
         * Parse a grammar from a file
         * @param filePath the file to parse from
         * @param random the source of randomness
         * @return the parsed grammar
         */
        public static Grammar parseGrammarFromFile(Path filePath, RandomProvider random) {
            return parseGrammarFromText(filePath.getFileName().toString(), readFile(filePath), random);
        }

        /**
         * Parse a grammar from the examples folder
         * @param exampleName the name of the example
         * @param random the source of randomness
         * @return the parsed grammar
         */
        public static Grammar parseGrammarFromExamples(String exampleName, RandomProvider random) {
            return parseGrammarFromFile(Path.of("src/comprehensive/examples/" + exampleName), random);
        }
    }

}

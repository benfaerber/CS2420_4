package comprehensive;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A section of a Grammar
 *
 * A grammar section is a list of GrammarLines with a name
 *
 * Example in text form:
 * {
 * <name>
 * contents
 * }
 *
 * @author Benjamin Faerber and David Chen
 * @version July 28, 2025
 */
public class GrammarSection {
    /** Group 1: It is a variable (ensure variable as short as possible */
    private static String VARIABLE_SUBSECTION = "<([a-z0-9_-]+?)>";
    /** Group 2: It is outer text */
    private static String TEXT_SUBSECTION = "([^<>]+)";
    private static Pattern GRAMMAR_STREAM_REGEX = Pattern.compile(VARIABLE_SUBSECTION + "|" +  TEXT_SUBSECTION);

    private String name;
    private ArrayList<GrammarLine> lines;
    private RandomProvider random;

    /**
     * Construct a grammar section from raw content.
     *
     * @param name the name of the section
     * @param rawContent the raw content in the section
     * @param random the source of randomness
     */
    public GrammarSection(String name, String rawContent, RandomProvider random) {
        this.name = name;
        this.lines = parseAllGrammarLines(rawContent);
        this.random = random;
    }

    /**
     * Parse all lines from raw content.
     *
     * @param rawContent the content to parse
     * @return A list of parsed lines
     */
    private ArrayList<GrammarLine> parseAllGrammarLines(String rawContent) {
        String[] lines = rawContent.split("\n");
        ArrayList<GrammarLine> grammarLines = new ArrayList<>();
        for (String line : lines) {
            grammarLines.add(parseSingleGrammarLine(line));
        }
        return grammarLines;
    }

    /**
     * Parse a single line of the Grammar.
     *
     * @param line The contents of a single line
     * @return The parsed GrammarLine
     */
    private GrammarLine parseSingleGrammarLine(String line) {
        Matcher matcher = GRAMMAR_STREAM_REGEX.matcher(line);
        ArrayList<GrammarToken> grammarStream = new ArrayList<>();
        while (matcher.find()) {
            GrammarToken chunk = matcher.group(1) != null
                    ? GrammarToken.ofVariable(matcher.group(1))
                    : GrammarToken.ofText(matcher.group(2));
            grammarStream.add(chunk);
        }
        return new GrammarLine(grammarStream);
    }

    /**
     * A string representation of the grammar.
     *
     * @return A string representation of the grammar
     */
    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (GrammarLine grammarLine : lines) {
            joiner.add(grammarLine.toString());
        }
        return "{\nGroup Name: " + name + "\n" + joiner.toString() + "\n}";
    }

    /**
     * Get the name of the grammar section.
     *
     * @return the name of the grammar section
     */
    public String getName() {
        return name;
    }

    /**
     * Get a random line from this grammar section.
     *
     * @return a random line from this grammar section
     */
    public GrammarLine randomLine() {
        return lines.get(random.nextInt(0, lines.size()));
    }
}

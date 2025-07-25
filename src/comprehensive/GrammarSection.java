package comprehensive;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarSection {
    /** Group 1: It is a variable (ensure variable as short as possible */
    private static String VARIABLE_SUBSECTION = "<([a-z0-9_-]+?)>";
    /** Group 2: It is outer text */
    private static String TEXT_SUBSECTION = "([^<>]+)";
    private static Pattern GRAMMAR_STREAM_REGEX = Pattern.compile(VARIABLE_SUBSECTION + "|" +  TEXT_SUBSECTION);

    private String name;
    private ArrayList<GrammarLine> lines;
    private RandomProvider random;

    public GrammarSection(String name, String rawContent, RandomProvider random) {
        this.name = name;
        this.lines = parseAllGrammarLines(rawContent);
        this.random = random;
    }

    private ArrayList<GrammarLine> parseAllGrammarLines(String rawContent) {
        String[] lines = rawContent.split("\n");
        ArrayList<GrammarLine> grammarLines = new ArrayList<>();
        for (String line : lines) {
            grammarLines.add(parseSingleGrammarLine(line));
        }
        return grammarLines;
    }

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

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("\n");
        for (GrammarLine grammarLine : lines) {
            joiner.add(grammarLine.toString());
        }
        return "{\nGroup Name: " + name + "\n" + joiner.toString() + "\n}";
    }

    public String getName() {
        return name;
    }

    public GrammarLine randomLine() {
        return lines.get(random.nextInt(0, lines.size()));
    }

}

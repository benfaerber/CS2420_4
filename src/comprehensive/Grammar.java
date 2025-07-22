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

public class Grammar implements Iterable<GrammarSection> {
    private final String name;
    private static String START_SECTION = "start";

    private final HashMap<String, GrammarSection> sections;
    private final RandomProvider random;

    public Grammar(String name, ArrayList<GrammarSection> sectionsList, RandomProvider random) {
        this.name = name;

        sections = new HashMap<>();
        for (GrammarSection section : sectionsList) {
            sections.put(section.getName(), section);
        }
        this.random = random;
    }


    public static Grammar fromFile(Path path) {
        return GrammarParser.parseGrammar(path, new ValueRandom());
    }

    public static Grammar fromFile(Path path, RandomProvider random) {
        return GrammarParser.parseGrammar(path, random);
    }

    public static Grammar fromExampleFile(String fileName, RandomProvider random) {
        return GrammarParser.parseGrammarFromExamples(fileName, random);
    }

    public Iterator<GrammarSection> iterator() {
        return sections.values().iterator();
    }

    public GrammarLine randomLineInSection(String name) {
        GrammarSection section = sections.get(name);
        return section.randomLine();
    }

    public String randomPhrase() {
        GrammarSection startSection = sections.get(START_SECTION);
        GrammarLine startLine = startSection.randomLine();

        return startLine.evaluate(this);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("\n\n");
        for (GrammarSection section : this) {
            sj.add(section.toString());
        }
        return name + "\n---------" + sj.toString() + "-------";
    }

    private static class GrammarParser {
        private static final Pattern SECTION_REGEX = Pattern.compile(
                "\\{\\n" // Start with opening brackets
                        + "<(.+?)>\\n" // Now a variable surrounded by angle brackets, followed by a newline
                        + "([\\s\\S]*?)" // All the content (the *? means as few as possible, so it doesn't eat all the sections)
                        + "\\}", // And finally close off the bracket!
                Pattern.DOTALL);

        private static ArrayList<GrammarSection> parseSections(String content, RandomProvider random) {
            Matcher matcher = SECTION_REGEX.matcher(content);
            ArrayList<GrammarSection> sections = new ArrayList<>();
            while (matcher.find()) {
                GrammarSection section = new GrammarSection(matcher.group(1), matcher.group(2), random);
                sections.add(section);
            }
            return sections;
        }

        private static String readFile(Path filePath) {
            try {
                return Files.readString(filePath);
            } catch (Exception e) {
                throw new RuntimeException("Failed to read file! Context: " + e);
            }
        }

        public static Grammar parseGrammar(Path filePath, RandomProvider random) {
            String rawContent = readFile(filePath);
            ArrayList<GrammarSection> sections = parseSections(rawContent, random);
            return new Grammar(filePath.getFileName().toString(), sections, random);
        }

        public static Grammar parseGrammarFromExamples(String exampleName, RandomProvider random) {
            return parseGrammar(Path.of("src/comprehensive/examples/" + exampleName), random);
        }
    }

}

package comprehensive;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarParser {
    private static final Pattern SECTION_REGEX = Pattern.compile(
            "\\{\\n<(.+?)>\\n([\\s\\S]*?)\\}", Pattern.DOTALL);

    private ArrayList<GrammarSection> parseSections(String content) {
        Matcher matcher = SECTION_REGEX.matcher(content);
        ArrayList<GrammarSection> sections = new ArrayList<>();
        while (matcher.find()) {
            GrammarSection section = new GrammarSection(matcher.group(1), matcher.group(2));
            sections.add(section);
        }
        return sections;
    }

    private String readFile(String filePath) {
        try {
            return Files.readString(Path.of(filePath));
        } catch (Exception e) {
            throw new RuntimeException("Failed to read file! Context: " + e);
        }
    }

    public Grammar parseGrammar(String filePath) {
        String rawContent = readFile(filePath);
        ArrayList<GrammarSection> sections = parseSections(rawContent);
        return new Grammar(filePath, sections);
    }

    public Grammar parseGrammarFromExamples(String exampleName) {
        return parseGrammar("src/comprehensive/examples/" + exampleName);
    }
}

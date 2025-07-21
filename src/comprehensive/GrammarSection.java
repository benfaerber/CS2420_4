package comprehensive;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarSection {
    /** Group 1: It is a variable */
    private String VARIABLE_SUBSECTION = "<(.+)>";
    /** Group 2: It is outer text */
    private String TEXT_SUBSECTION = "([^<>]+)";
    Pattern GRAMMAR_STREAM_REGEX = Pattern.compile(VARIABLE_SUBSECTION + "|" +  TEXT_SUBSECTION);

    private String groupName;
    private ArrayList<GrammarLine> lines;

    public GrammarSection(String groupName, String rawContent) {
        this.groupName = groupName;
        this.lines = parseAllGrammarLines(rawContent);
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
        ArrayList<GrammarChunk> grammarStream = new ArrayList<>();
        while (matcher.find()) {
            GrammarChunk chunk = matcher.group(1) != null
                    ? GrammarChunk.ofVariable(matcher.group(1))
                    : GrammarChunk.ofText(matcher.group(2));
            grammarStream.add(chunk);
        }

        return new GrammarLine(grammarStream);
    }

    private static class GrammarLine {
        private ArrayList<GrammarChunk> chunks;
        public GrammarLine(ArrayList<GrammarChunk> chunks) {
            this.chunks = chunks;
        }
    }

    private static class GrammarChunk {
        enum GrammarType {
            Variable,
            Text,
        }

        private final String content;
        private final GrammarType type;

        public GrammarChunk(String content, GrammarType type) {
            this.content = content;
            this.type = type;
        }

        public static GrammarChunk ofText(String text) {
            return new GrammarChunk(text, GrammarType.Text);
        }

        public static GrammarChunk ofVariable(String variable) {
            return new GrammarChunk(variable, GrammarType.Variable);
        }

        public boolean isVariable() {
            return type == GrammarType.Variable;
        }
        public boolean isText() {
            return type == GrammarType.Text;
        }

        public String getContent() {
            return content;
        }
    }

}

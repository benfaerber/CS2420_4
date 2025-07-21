package comprehensive;

import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrammarSection {
    /** Group 1: It is a variable (ensure variable as short as possible */
    private String VARIABLE_SUBSECTION = "<(.+?)>";
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
        return "{\nGroup Name: " + groupName + "\n" + joiner.toString() + "\n}";
    }

    private static class GrammarLine {
        private ArrayList<GrammarToken> tokens;
        public GrammarLine(ArrayList<GrammarToken> tokens) {
            this.tokens = tokens;
        }

        @Override
        public String toString() {
            StringJoiner joiner = new StringJoiner(" ");
            for (GrammarToken token : tokens) {
                joiner.add(token.toString());
            }
            return joiner.toString();
        }
    }

    private static class GrammarToken {
        enum GrammarType {
            Variable,
            Text,
        }

        private final String content;
        private final GrammarType type;

        public GrammarToken(String content, GrammarType type) {
            this.content = content;
            this.type = type;
        }

        public static GrammarToken ofText(String text) {
            return new GrammarToken(text, GrammarType.Text);
        }

        public static GrammarToken ofVariable(String variable) {
            return new GrammarToken(variable, GrammarType.Variable);
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

        @Override
        public String toString() {
            return switch (type) {
                case Variable -> "Var(" + content + ")";
                case Text -> "Text(" + content + ")";
            };
        }
    }

}

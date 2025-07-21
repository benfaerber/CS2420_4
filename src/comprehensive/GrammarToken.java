package comprehensive;

class GrammarToken {
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
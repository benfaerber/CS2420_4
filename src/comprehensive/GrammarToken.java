package comprehensive;

/**
 * A single GrammarToken
 * This can either be Variable or Text.
 * A variable can be expanded
 */
class GrammarToken {
    /**
     * The various types of grammar
     * This could be expanded later to add new types of grammars
     */
    enum GrammarType {
        Variable,
        Text,
    }

    private final String content;
    private final GrammarType type;

    /**
     * Construct a GrammarToken
     * @param content the text content of the token
     * @param type the token type
     */
    public GrammarToken(String content, GrammarType type) {
        this.content = content;
        this.type = type;
    }

    /**
     * Create a Text token
     * @param text the text contents of the token
     * @return a new text token
     */
    public static GrammarToken ofText(String text) {
        return new GrammarToken(text, GrammarType.Text);
    }

    /**
     * Create a Variable token
     * @param variable the name of the variable
     * @return a new variable token
     */
    public static GrammarToken ofVariable(String variable) {
        return new GrammarToken(variable, GrammarType.Variable);
    }

    /**
     * Is this a variable token?
     * @return true if the token is a variable
     */
    public boolean isVariable() {
        return type == GrammarType.Variable;
    }

    /**
     * Is this a text token?
     * @return true if the token is text
     */
    public boolean isText() {
        return type == GrammarType.Text;
    }

    /**
     * Get the inner content of the token
     * @return the inner content of the token
     */
    public String getContent() {
        return content;
    }

    /**
     * Get a string representation of the token
     * @return a string representation of the token
     */
    @Override
    public String toString() {
        return switch (type) {
            case Variable -> "Var(" + content + ")";
            case Text -> "Text(" + content + ")";
        };
    }
}
package comprehensive;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringJoiner;

public class Grammar implements Iterable<GrammarSection>, Serializable {
    private String name;
    private String START_SECTION = "start";

    private HashMap<String, GrammarSection> sections;

    public Grammar(String name, ArrayList<GrammarSection> sectionsList) {
        this.name = name;

        sections = new HashMap<>();
        for (GrammarSection section : sectionsList) {
            sections.put(section.getName(), section);
        }
    }

    public static Grammar fromText(String text) {
        return GrammarParser.parseGrammar(text);
    }

    public static Grammar fromFile(String fileName) {
        return GrammarParser.parseGrammar(fileName);
    }

    public static Grammar fromExampleFile(String fileName) {
        return GrammarParser.parseGrammarFromExamples(fileName);
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
}

package comprehensive;

import assign10.BinaryMaxHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GrammarParserTest {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testGrammarParser() {
        GrammarParser parser = new GrammarParser();
        ArrayList<GrammarSection> sections = parser.getSections("src/comprehensive/super_simple.g");
        System.out.println(sections.get(0));
    }
}

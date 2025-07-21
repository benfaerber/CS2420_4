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
        Grammar grammar = parser.parseGrammarFromExamples("super_simple.g");
        System.out.println(grammar);

        String eval = grammar.evaluate();
        System.out.println(eval);
    }
}

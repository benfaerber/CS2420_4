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
        Grammar grammar = Grammar.fromExampleFile("super_simple.g");
        System.out.println(grammar);

        String eval = grammar.randomPhrase();
        System.out.println(eval);
    }
}

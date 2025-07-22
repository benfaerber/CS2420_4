package comprehensive;

import assign10.BinaryMaxHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class GrammarParserTest {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testPhraseGenerator() {
        Grammar grammar0 = Grammar.fromExampleFile("super_simple.g", new MockRandom());
        String expectedPhrase0 = "The cat sat on the cat.";
        assertEquals(grammar0.randomPhrase(), expectedPhrase0);


        Grammar grammar1 = Grammar.fromExampleFile("super_simple.g", new MockRandom(1));
        String expectedPhrase1 = "The dog stood on the dog.";
        assertEquals(grammar1.randomPhrase(), expectedPhrase1);

        Grammar grammar2 = Grammar.fromExampleFile("assignment_extension_request.g", new MockRandom(0));
        String expectedPhrase2 = "I need an extension because my USB flash drive got erased.";
        assertEquals(grammar2.randomPhrase(), expectedPhrase2);

    }
}

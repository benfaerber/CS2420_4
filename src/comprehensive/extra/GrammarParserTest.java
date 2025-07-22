package comprehensive.extra;

import comprehensive.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testPhraseGeneratorWithSequenceRandom() {
        Grammar grammar0 = Grammar.fromExampleFile("super_simple.g", new MockSequenceRandom());
        String expectedPhrase0 = "The cat sat on the dog.";
        assertEquals(grammar0.randomPhrase(), expectedPhrase0);

        Grammar grammar2 = Grammar.fromExampleFile("assignment_extension_request.g", new MockSequenceRandom());
        String expectedPhrase2 = "I need an extension because my USB flash drive got erased.";
        assertEquals(grammar2.randomPhrase(), expectedPhrase2);

        Grammar grammar3 = Grammar.fromExampleFile("fruit.g", new MockSequenceRandom());
        String expectedPhrase3 = "I can't eat fruit today because I got into a fight with a banana.";
        assertEquals(grammar3.randomPhrase(), expectedPhrase3);
    }

    @Test
    public void testValueRandom() {
        // Value random should generate 0-10 exclusive
        RandomProvider random = new ValueRandom();
        Set<Integer> genedNums = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            genedNums.add(random.nextInt(0, 10));
        }

        for (int i = 0; i < 10; i++) {
            assertTrue(genedNums.contains(i));
        }

        assertTrue(genedNums.size() == 10);
    }

    @Test
    public void testMockRandom() {
        RandomProvider mock0 = new MockRandom();
        RandomProvider mock1 = new MockRandom(1);
        RandomProvider mockSmall = new MockRandom(-100);
        RandomProvider mockBig = new MockRandom(100);

        assertEquals(0, mock0.nextInt(0, 10));
        assertEquals(1, mock1.nextInt(0, 10));
        assertEquals(0, mockSmall.nextInt(0, 10));
        assertEquals(9, mockBig.nextInt(0, 10));
    }

    @Test
    public void testPhraseGeneratorWithRandom() {
        // These will be random, so this just ensures they throw no errors

        Grammar grammar0 = Grammar.fromExampleFile("super_simple.g", new ValueRandom());
        System.out.println(grammar0.randomPhrase());

        Grammar grammar1 = Grammar.fromExampleFile("super_simple.g", new ValueRandom());
        System.out.println(grammar1.randomPhrase());

        Grammar grammar2 = Grammar.fromExampleFile("assignment_extension_request.g", new ValueRandom());
        System.out.println(grammar2.randomPhrase());
    }

    @Test
    public void testAllExamplesWithRandom() {
        String[] examples = new String[] {
            "abc.g", "abc_spaces.g", "assignment_extension_request.g", "fruit.g", "hello_world.g",
                "mathematical_expression.g", "poetic_sentence.g", "super_simple.g"
        };
        for (String exampleName : examples) {
            Grammar grammar = Grammar.fromExampleFile(exampleName, new ValueRandom());
            System.out.println(exampleName);
            for (int i = 0; i < 20; i++) {
                System.out.println(grammar.randomPhrase());
            }
            System.out.println("-----");
        }
    }

    @Test
    public void testLargeFruitFile() {
        Grammar fruitGrammar = Grammar.fromExampleFile("fruit.g", new ValueRandom());

        for (int i = 0; i < 100; i++) {
            System.out.println(fruitGrammar.randomPhrase());
        }
    }
}

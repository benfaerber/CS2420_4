package assign06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * This class contains tests for SinglyLinkedList.
 *
 * @author Benjamin Faerber
 * @version 2025-06-14
 */
public class PostfixCalculatorTests {
    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    void testPostFixExpression() {
        // 3 + 4
        double result1 = PostfixCalculator.evaluate("3 4 +");
        assertEquals(7, result1);

        // 2 3 1 * + 9 -
        double result2 = PostfixCalculator.evaluate("2 3 1 * + 9 -");
        assertEquals(-4, result2);

        // 3 4 + 5 6 + *
        double result3 = PostfixCalculator.evaluate("3 4 + 5 6 + *");
        assertEquals(77, result3);

        double result4 = PostfixCalculator.evaluate("2.5 2 *");
        assertEquals(5, result4);

        assertThrows(IllegalArgumentException.class, () -> PostfixCalculator.evaluate("asdf"));
    }

    @Test
    void testValidatesExpression() {
        assertThrows(IllegalArgumentException.class, () -> PostfixCalculator.evaluate("asdf"));
        assertThrows(IllegalArgumentException.class, () -> PostfixCalculator.evaluate("1 +"));
    }

}
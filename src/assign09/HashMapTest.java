package assign09;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


public class HashMapTest {
    private HashTable<String, Integer> simpleMap;

    @BeforeEach
    void setUp() throws Exception {
        simpleMap = new HashTable<>();
    }

    @AfterEach
    void tearDown() throws Exception {}

    @Test
    public void testPut() {
        simpleMap.put("apple", 1);
        simpleMap.put("banana", 5);
        simpleMap.put("pear", 4);

        // Size is 3
        assertEquals(3, simpleMap.size());

        // Apple stores 1
        assertEquals(1, simpleMap.get("apple"));

        System.out.println(simpleMap.toString());
    }
}





package assign09;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;


public class HashMapTest {
    private HashTable<String, Integer> fruits, emptyMap, simpleMap;

    // Done: containsKey, isEmpty, put, size, containsValue, entries, get, remove


    @BeforeEach
    void setUp() throws Exception {
        fruits = new HashTable<>();
        emptyMap = new HashTable<>();
        simpleMap = new HashTable<>();

        fruits.put("apple", 1);
        fruits.put("orange", 2);
        fruits.put("pineapple", 3);
        fruits.put("kiwi", 4);
    }

    @Test
    void testIsEmpty() {
        assertTrue(emptyMap.isEmpty());
        assertFalse(fruits.isEmpty());
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
    }

    @Test
    public void testClear() {
        // Starts at 4
        assertEquals(4, fruits.size());
        fruits.clear();
        // Should be empty
        assertEquals(0, fruits.size());
        assertTrue(fruits.isEmpty());

        // Should be able to readd
        fruits.put("red_apple", 50);
        fruits.put("red_banana", 12);
        fruits.put("red_pear", 13);

        assertEquals(3, fruits.size());
        assertEquals(50, fruits.get("red_apple"));
    }

    @Test
    public void testContainsKey() {
        assertTrue(fruits.containsKey("apple"));
        assertFalse(fruits.containsKey("dog"));
        assertTrue(fruits.containsKey("kiwi"));
    }

    @Test
    public void testContainsValue() {
        // Should have
        assertTrue(fruits.containsValue(1));
        assertTrue(fruits.containsValue(4));

        // Shouldn't have
        assertFalse(fruits.containsValue(5));
        assertFalse(fruits.containsValue(12));
    }

    @Test
    public void testEntries() {
        // Should have all these
        ArrayList<String> keys = new ArrayList<>();
        keys.add("apple");
        keys.add("orange");
        keys.add("pineapple");
        keys.add("kiwi");

        int entries = 0;

        for (MapEntry<String, Integer> entry : fruits.entries()) {
            assertTrue(keys.contains(entry.getKey()));
            entries++;
        }

        assertEquals(entries, keys.size());
    }

    @Test
    public void testGet() {
        assertNull(fruits.get("dog"));
        assertEquals(1, fruits.get("apple"));
        assertEquals(3, fruits.get("pineapple"));
    }

    @Test
    public void testRemove() {
        assertEquals(1, fruits.remove("apple"));

        assertEquals(3, fruits.size());
        assertNull(fruits.get("apple"));

        // Clear by removing
        fruits.remove("kiwi");
        fruits.remove("orange");
        fruits.remove("pineapple");

        assertEquals(0, fruits.size());
    }
}





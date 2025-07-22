package comprehensive;

import java.util.HashMap;

/**
 * A deterministic random mock that counts up.
 * I created this to avoid infinite loops in recursive grammars.
 */
public class MockSequenceRandom implements RandomProvider {
    private final HashMap<String, Integer> values = new HashMap<>();

    public MockSequenceRandom() {
    }

    public MockSequenceRandom withSequence(int min, int max, int value) {
        String key = keyGen(min, max);
        values.put(key, value);
        return this;
    }

    private String keyGen(int min, int max) {
        return min + ":" + max;
    }

    @Override
    public int nextInt(int min, int max) {
        String key = keyGen(min, max);
        Integer value = values.get(key);
        if (value == null) {
            value = 0;
        }

        int prevVal = value + min;
        value++;
        if (value >= (max - min)) {
            value = 0;
        }

        values.put(key, value);
        return prevVal;
    }
}

package comprehensive;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ValueRandom accesses the ThreadLocalRandom and returns a random value
 * This is faster than generating a new Random each time
 */
public class ValueRandom implements RandomProvider {
    /**
     * The next random int
     * @param min the minimum random value
     * @param max the maximum random value (exclusive)
     * @return the random value
     */
    @Override
    public int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}

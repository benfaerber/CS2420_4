package comprehensive;

import java.util.Random;

public class ValueRandom implements RandomProvider {
    /**
     * A random singleton to reuse for the whole app. Speed boost!
     */
    private final static Random random = new Random();

    @Override
    public int nextInt(int min, int max) {
        return random.nextInt(min, max);
    }
}

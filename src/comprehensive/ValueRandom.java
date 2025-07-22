package comprehensive;

import java.util.Random;

public class ValueRandom implements RandomProvider {
    private final Random random = new Random();

    @Override
    public int nextInt(int min, int max) {
        return random.nextInt(min, max);
    }
}

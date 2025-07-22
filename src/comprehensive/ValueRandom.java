package comprehensive;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ValueRandom implements RandomProvider {
    @Override
    public int nextInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}

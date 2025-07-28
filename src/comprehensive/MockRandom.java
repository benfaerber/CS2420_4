package comprehensive;

/**
 * MockRandom is useful for unit testing
 * It allows for deterministic testing.
 * For example you can create a MockRandom that will always return 1
 * this will ALWAYS create the same sentence so you can test properly
 */
public class MockRandom implements RandomProvider {
    private int alwaysReturn;

    /**
     * Create a new MockRandom that always returns 0
     */
    public MockRandom() {
        this.alwaysReturn = 0;
    }

    /**
     * Create a new MockRandom that always returns alwaysReturn
     * @param alwaysReturn the value to always return
     */
    public MockRandom(int alwaysReturn) {
        this.alwaysReturn = alwaysReturn;
    }

    /**
     * Get the alwaysReturn value (compatible with RandomProvider)
     * @param min the minimum value
     * @param max the maximum value
     * @return the alwaysReturn value
     */
    @Override
    public int nextInt(int min, int max) {
        if (alwaysReturn >= max - 1) {
            return max - 1;
        }

        return Math.max(alwaysReturn, min);

    }
}

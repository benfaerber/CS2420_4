package comprehensive;

public class MockRandom implements RandomProvider {
    private int alwaysReturn;

    public MockRandom() {
        this.alwaysReturn = 0;
    }

    public MockRandom(int alwaysReturn) {
        this.alwaysReturn = alwaysReturn;
    }

    @Override
    public int nextInt(int min, int max) {
        if (alwaysReturn >= max - 1) {
            return max - 1;
        }

        return Math.max(alwaysReturn, min);

    }
}

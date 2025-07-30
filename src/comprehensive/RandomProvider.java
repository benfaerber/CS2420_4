package comprehensive;

/**
 * This interface represents the base requirements of randomness.
 * This is used throughout the app so we can use different sources of randomness.
 *
 * @author Benjamin Faerber and David Chen
 * @version July 28, 2025
 */
public interface RandomProvider {
    /**
     * The next random int
     * @param min the minimum random value
     * @param max the maximum random value (exclusive)
     * @return the random value
     */
    public int nextInt(int min, int max);
}

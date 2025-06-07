package lab02;

/**
 * Class with static methods to compute the maximum/minimum element in an integer array.
 *
 * @author CS 2420 Course Staff
 * @version 2025-05-23
 */
public class ArrayUtility {
    /**
     * Compute the maximum integer in an array.
     * @param array - array of integers
     * @return - maximum integer in the array
     * @throws IllegalArgumentException if array is empty.
     */
    public static int computeMaximum(int[] array) throws IllegalArgumentException {
        if (array.length == 0) {
            throw new IllegalArgumentException("Maximum of empty array is not defined");
        }

        int maximum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maximum) {
                maximum = array[i];
            }
        }

        return maximum;
    }

    /**
     * Compute the minimum integer in an array.
     * @param array - array of integers
     * @return - minimum integer in the array
     * @throws IllegalArgumentException if array is empty.
     */
    public static int computeMinimum(int[] array) throws IllegalArgumentException {
        if (array.length == 0) {
            throw new IllegalArgumentException("Minimum of empty array is not defined");
        }

        int minimum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] < minimum) {
                minimum = array[i];
            }
        }

        return minimum;
    }
}

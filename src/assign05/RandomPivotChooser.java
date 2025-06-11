package assign05;

import java.util.List;
import java.util.Random;

/**
 * Select a random element from the list to be the pivot
 * @param <E> the type of item in the list
 */
public class RandomPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    /**
     * Selects an element in the given List to serve as the quicksort pivot.
     *
     * @param list - list containing a portion to be sorted
     * @param leftIndex - position of first item in the sublist to be sorted
     * @param rightIndex - position of the last item in the sublist to be sorted
     * @return index of the list element selected to serve as the pivot
     */
    public int getPivotIndex(List<E> list, int leftIndex, int rightIndex) {
        Random rand = new Random();
        // I think this won't include rightIndex
//        return rand.nextInt(leftIndex, rightIndex);
        return rand.nextInt(rightIndex - leftIndex + 1) + leftIndex;
    }
}
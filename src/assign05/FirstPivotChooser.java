package assign05;
import java.util.List;

/**
 * Select the first element in the list as the pivot.
 *
 * @param <E> The type of items in the list
 */
public class FirstPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    /**
     * Selects an element in the given List to serve as the quicksort pivot.
     *
     * @param list - list containing a portion to be sorted
     * @param leftIndex - position of first item in the sublist to be sorted
     * @param rightIndex - position of the last item in the sublist to be sorted
     * @return index of the list element selected to serve as the pivot
     */
    public int getPivotIndex(List<E> list, int leftIndex, int rightIndex) {
        return leftIndex;
    }
}
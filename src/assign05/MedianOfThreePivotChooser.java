package assign05;
import java.util.ArrayList;
import java.util.List;

/**
 * The median item of the first element, the middle element, and the last element.
 *
 * @param <E> the type of item in the list
 */
public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    /**
     * Selects an element in the given List to serve as the quicksort pivot.
     *
     * @param list - list containing a portion to be sorted
     * @param firstIndex - position of first item in the sublist to be sorted
     * @param lastIndex - position of the last item in the sublist to be sorted
     * @return index of the list element selected to serve as the pivot
     */
    public int getPivotIndex(List<E> list, int firstIndex, int lastIndex) {
        int middleIndex = (firstIndex + lastIndex) / 2;
        E first = list.get(firstIndex);
        E middle = list.get(middleIndex);
        E last = list.get(lastIndex);

        // Change to find the index directly instead
        if ((first.compareTo(middle) <= 0 && middle.compareTo(last) <= 0) ||
                (last.compareTo(middle) <= 0 && middle.compareTo(first) <= 0)) {
            return middleIndex;
        } else if ((middle.compareTo(first) <= 0 && first.compareTo(last) <= 0) ||
                (last.compareTo(first) <= 0 && first.compareTo(middle) <= 0)) {
            return firstIndex;
        } else {
            return lastIndex;
        }
    }
}

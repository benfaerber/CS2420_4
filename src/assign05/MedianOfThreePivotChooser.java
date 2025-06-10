package assign05;

import java.util.ArrayList;
import java.util.List;

/**
 * The median item of the first element, middle element, and last element
 * @param <E> the type of item in the list
 */
public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    /**
     * Selects an element in the given List to serve as the quicksort pivot.
     *
     * @param list - list containing a portion to be sorted
     * @param leftIndex - position of first item in the sublist to be sorted
     * @param rightIndex - position of the last item in the sublist to be sorted
     * @return index of the list element selected to serve as the pivot
     */
    public int getPivotIndex(List<E> list, int leftIndex, int rightIndex) {
        E first = list.get(leftIndex);
        E last = list.get(rightIndex);
        int middleIndex = ((rightIndex - leftIndex) / 2) + leftIndex;
        E middle = list.get(middleIndex);

        ArrayList<E> medianList = new ArrayList<E>();
        medianList.add(first);
        medianList.add(last);
        medianList.add(middle);
        ListSorter.insertionSort(medianList);
        E medianElement = medianList.get(1);

        if (middle == medianElement) {
            return middleIndex;
        } else if (middle == first) {
            return leftIndex;
        } else {
            return rightIndex;
        }
    }
}

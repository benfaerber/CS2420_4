package assign05;

import java.util.ArrayList;
import java.util.List;

public class MedianOfThreePivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
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

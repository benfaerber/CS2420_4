package assign05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListSorter {
    /**
     * Perform insertion sort on the the specified range of list.
     *
     * @param list  - T[] type objects
     * @param start - int index start position of sorting
     * @param end   - int index end position of sorting
     * @param <T>   - Generic type of object
     */
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i; j > start; j--) {
                T currentUnsortStar = list.get(j);
                T currentUnsortEnd = list.get(j - 1);
                if (currentUnsortStar.compareTo(currentUnsortEnd) < 0) {
                    T temp = list.get(j);
                    list.set(j, currentUnsortEnd);
                    list.set(j - 1, temp);
                }
            }
        }
    }

    /**
     * Perform insertion sort on an entire list with insertion sort
     *
     * @param list the full list to be sorted
     * @param <T>  the type the list contains
     */
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list) {
        insertionSort(list, 0, list.size());
    }

    /**
     * @param list
     * @param threshold
     * @param <T>
     */
    public static <T extends Comparable<? super T>> void mergesort(List<T> list, int threshold) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("Threshold must be a positive number");
        }

        boolean shouldInsert = list.size() <= threshold;
        if (shouldInsert) {
            insertionSort(list);
            return;
        }
        mergesortRec(list, 0, list.size() - 1, threshold);
    }

    /**
     * A private recursive implementation of merge sort
     * This is an auxillary method, so this helps the driver method (see: mergesort) do its job
     *
     * @param list      The list to merge sort
     * @param lower     the lower bound to start at
     * @param upper     the upper bound to end at
     * @param threshold when to switch to using insertion sort (quicker for small lists)
     * @param <T>       the type the data contains
     */
    private static <T extends Comparable<? super T>> void mergesortRec(List<T> list, int lower, int upper, int threshold) {
        // Base Cases
        if (lower == upper) {
            return;
        }
        int middle = (lower + upper) / 2;

        // LEFT SIDE
        int leftStartAt = lower;
        int leftEndAt = middle;
        int leftSize = leftEndAt - leftStartAt;

        printListRange("Left Range Before Sort", list, leftStartAt, leftEndAt);
        if (leftSize > threshold) {
            mergesortRec(list, leftStartAt, leftEndAt, threshold);
        } else {
            insertionSort(list, leftStartAt, leftEndAt);
        }

        // Right SIDE
        int rightStartAt = middle + 1;
        int rightEndAt = upper;
        int rightSize = rightEndAt - rightStartAt;

        if (rightSize > threshold) {
            mergesortRec(list, rightStartAt, rightEndAt, threshold);
        } else {
            insertionSort(list, rightStartAt, rightEndAt + 1);
        }
        merge(list, lower, middle, upper);
    }

    /**
     * @param list
     * @param lower
     * @param middle
     * @param upper
     * @param <T>
     */
    private static <T extends Comparable<? super T>> void merge(List<T> list, int lower, int middle, int upper) {
        int leftLength = middle - lower + 1;
        int rightLength = upper - middle;

        List<T> leftSide = new ArrayList<T>(leftLength);
        List<T> rightSide = new ArrayList<T>(rightLength);

        for (int i = 0; i < leftLength; i++) {
            leftSide.add(list.get(lower + i));
        }
        for (int i = 0; i < rightLength; i++) {
            rightSide.add(list.get(middle + 1 + i));
        }

        int i = 0, j = 0;

        int currentIndex = lower;
        while (i < leftLength && j < rightLength) {
            int leftVsRightCmp = leftSide.get(i).compareTo(rightSide.get(j));
            if (leftVsRightCmp <= 0) {
                list.set(currentIndex, leftSide.get(i));
                i++;
            } else {
                list.set(currentIndex, rightSide.get(j));
                j++;
            }
            currentIndex++;
        }

        while (i < leftLength) {
            list.set(currentIndex, leftSide.get(i));
            i++;
            currentIndex++;
        }

        while (j < rightLength) {
            list.set(currentIndex, rightSide.get(j));
            j++;
            currentIndex++;
        }
    }

    private static <T extends Comparable<? super T>> int partition(List<T> list, PivotChooser<T> chooser, int lowerIndex, int upperIndex) {
        int pivotIndex = chooser.getPivotIndex(list, lowerIndex, upperIndex);
        System.out.println("pivot index: " + pivotIndex);
        int currentIndex = lowerIndex;

        for (int j = currentIndex; j <= upperIndex - 1; j++) {
            int compareValue = list.get(j).compareTo(list.get(pivotIndex));
            if (compareValue < 0) {
                currentIndex++;
                T temp = list.get(j);
                list.set(j, list.get(currentIndex));
                list.set(currentIndex, temp);
            }
        }

        currentIndex++;
        T temp = list.get(currentIndex);
        list.set(upperIndex, temp);
        list.set(currentIndex, list.get(upperIndex));
        return currentIndex;
    }

    public static <T extends Comparable<? super T>> void quicksortRec(List<T> list, PivotChooser<T> chooser, int lowerIndex, int upperIndex) {
        if (lowerIndex < upperIndex) {
            int partitionIndex = partition(list, chooser, lowerIndex, upperIndex);
            quicksortRec(list, chooser, lowerIndex, partitionIndex - 1);
            quicksortRec(list, chooser, partitionIndex + 1, upperIndex);
        }
    }

    public static <T extends Comparable<? super T>> void quicksort(List<T> list, PivotChooser<T> chooser) {
        quicksortRec(list, chooser, 0, list.size() - 1);
    }

    public static List<Integer> generateAscending(int size) {
        return List.of();
    }

    public static List<Integer> generatePermuted(int size) {
        return List.of();
    }

    public static List<Integer> generateDescending(int size) {
        return List.of();
    }


    // Needs to be deleted later
    private static <T> void printList(String label, List<T> lst) {
        System.out.println(label + ":");
        for (T item : lst) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }

    private static <T> void printListRange(String label, List<T> lst, int start, int end) {
        System.out.println(label + ":");
        for (T item : lst.subList(start, end)) {
            System.out.print(item + ", ");
        }
        System.out.println();
    }
}

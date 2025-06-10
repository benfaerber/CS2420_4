package assign05;
import java.util.ArrayList;
import java.util.List;

/**
 * This class performs two sorting mechanisms (Merge/Insertion) to sort the List
 * Generates lists in ascending, descending, and permuted (ie changed order of) order.
 *
 * @author Benjamin Faerber and David Chen
 * @version 2025-06-03
 */
public class ListSorter {
    /**
     * Perform insertion sort on the specified range of the list.
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
     * Perform insertion sort on an entire list with insertion sort.
     *
     * @param list the full list to be sorted
     * @param <T>  the type the list contains
     */
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list) {
        insertionSort(list, 0, list.size());
    }

    /**
     * Perform merge sort on a given list, switch to insertion sort when the sub-array size is less than the threshold.
     *
     * @param list - <T> type list of objects
     * @param threshold - int size of the sub-array to perform insertion sort
     * @param <T> - any type of object
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
     * A private recursive implementation of merge sort.
     * This is an auxiliary method, so this helps the driver method (see: mergesort) do its job.
     *
     * @param list      The list to merge sort
     * @param lower     the lower bound to start at
     * @param upper     the upper bound to end at
     * @param threshold when to switch to using insertion sort (quicker for small lists)
     * @param <T>       the type the data it contains
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
     * Private method to merge the sorted auxiliary list back to the original list.
     *
     * @param list - <T> of auxiliary list we are merging
     * @param lower - the first index of the section to merge
     * @param middle - the middle index of the section to merge
     * @param upper - the last index of the section to merge
     * @param <T> - the type contained in the list
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

    /**
     * Sort a list using quick sort and a custom pivot chooser.
     *
     * @param list - the list to be sorted
     * @param chooser - A chooser that implements the PivotChooser protocol
     * @param <T> - The type the list contains
     */
    public static <T extends Comparable<? super T>> void quicksort(List<T> list, PivotChooser<T> chooser) {
        quicksortRec(list, chooser, 0, list.size() - 1);
    }

    /**
     * The auxialary method that powers quick sort.
     *
     * @param list - the list to be sorted
     * @param chooser - A chooser that implements the PivotChooser protocol
     * @param lowerIndex - the index to start quick sorting at
     * @param upperIndex - the index to end quick sorting at
     * @param <T> - the type the list contains
     */
    private static <T extends Comparable<? super T>> void quicksortRec(List<T> list, PivotChooser<T> chooser, int lowerIndex, int upperIndex) {
        if (lowerIndex < upperIndex) {
            int partitionPoint = partition(list, chooser, lowerIndex, upperIndex);
            quicksortRec(list, chooser, lowerIndex, partitionPoint - 1);
            quicksortRec(list, chooser, partitionPoint + 1, upperIndex);
        }
    }

    /**
     * Split the list into quick sorting sections.
     *
     * @param list - the list to be partioned
     * @param chooser - the object that chooses the quick sort pivot
     * @param low - where to start the partion
     * @param high - where to end the partion
     * @return - the partition point
     * @param <T> - the type contained in the list
     */
    private static <T extends Comparable<? super T>> int partition(List<T> list, PivotChooser<T> chooser, int low, int high) {
        int pivotIndex = chooser.getPivotIndex(list, low, high);
        T pivot = list.get(pivotIndex);
        swap(list, pivotIndex, high);

        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            if (list.get(j).compareTo(pivot) < 0) {
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, high);
        return i + 1;
    }

    /**
     * Swaps 2 elements in the list.
     *
     * @param list - the list to swap elements in
     * @param indexA - the first index to swap
     * @param indexB - the second index to swap
     * @param <T> - the type contained in the list
     */
    private static <T> void swap(List<T> list, int indexA, int indexB) {
        T temp = list.get(indexA);
        list.set(indexA, list.get(indexB));
        list.set(indexB, temp);
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

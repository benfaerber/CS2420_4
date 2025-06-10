package assign05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListSorter {
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list, int start, int end) {
        for (int i = start; i < end; i++) {
            for (int j = i; j > 0; j--) {
                T currentUnsortStar = list.get(j);
                T currentUnsortEnd = list.get(j - 1);
                if (currentUnsortStar.compareTo(currentUnsortEnd) < 0) {
                    T temp = list.get(j);
                    list.set(j, currentUnsortEnd);
                    list.set(j-1, temp);
                }
            }
        }
    }

    // We dont need this?
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list) {
        insertionSort(list, 0, list.size());
    }

    public static <T extends Comparable<? super T>> void mergesort(List<T> list, int threshold) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("Threshold must be a positive number");
        }

        boolean shouldInsert = list.size() <= threshold;
        if (shouldInsert) {
            System.out.println("Using Insertion Sort");
            insertionSort(list);
            return;
        }
        mergesortRec(list, 0, list.size() - 1, threshold);
    }

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
            printList("Before Left Insertion Sort", list);
            insertionSort(list, leftStartAt, leftEndAt);
            printList("After Left Insertion Sort", list);
        }
        printListRange("Left Range After Sort", list, leftStartAt, leftEndAt);


        // Right SIDE
        int rightStartAt = middle + 1;
        int rightEndAt = upper;
        int rightSize = rightEndAt - rightStartAt;

        printListRange("Right Range Before Sort", list, rightStartAt, rightEndAt);
        System.out.println("Right ends at " + rightStartAt + " " + rightEndAt);
        if (rightSize > threshold) {
            mergesortRec(list, rightStartAt, rightEndAt, threshold);
        } else {
            printList("Before right Insertion Sort", list);
            insertionSort(list, rightStartAt, rightEndAt);
            printList("After right Insertion Sort", list);
        }
        printListRange("Right Range After Sort", list, rightStartAt, rightEndAt);


        merge(list, lower, middle, upper);
    }

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

    public static <T extends Comparable<? super T>> void quicksort(List<T> list, PivotChooser<T> chooser) {

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

}

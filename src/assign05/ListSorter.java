package assign05;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListSorter {
    public static <T extends Comparable<? super T>> void insertionSort(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
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
        mergesortRec(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<? super T>> void mergesortRec(List<T> list, int lower, int upper) {
        // Base Cases
        if (lower == upper) {
            return;
        }

        int middle = (lower + upper) / 2;

        mergesortRec(list, lower, middle);
        mergesortRec(list, middle + 1, upper);

        merge(list, lower, middle, upper);
    }

    private static <T extends Comparable<? super T>> void merge(List<T> list, int lower, int middle, int upper) {
        int leftLength = middle - lower + 1;
        int rightLength  = upper - middle;

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

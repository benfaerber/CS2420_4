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
                if (currentUnsortStar.compareTo(currentUnsortEnd) > 0) {
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
            insertionSort(list);
            return;
        }

        mergesortRec(list, 0, list.size() - 1);
    }

    private static <T extends Comparable<? super T>> void mergesortRec(List<T> list, int lower, int upper) {
        List<T> auxStorage = new ArrayList<T>();

        int middle = (lower + upper) / 2;

        mergesortRec(list, lower, middle);
        mergesortRec(list, middle + 1, upper);

        // merge(list, lower, middle, upper);
    }

    private static <T extends Comparable<? super T>> void merge(List<T> list, int lower, int middle, int upper) {

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

package assign05;

import java.util.List;

public class FirstPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    public int getPivotIndex(List<E> list, int leftIndex, int rightIndex) {
        return 0;
    }
}
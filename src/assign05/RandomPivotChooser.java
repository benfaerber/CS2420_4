package assign05;

import java.util.List;
import java.util.Random;

public class RandomPivotChooser<E extends Comparable<? super E>> implements PivotChooser<E> {
    public int getPivotIndex(List<E> list, int leftIndex, int rightIndex) {
        Random rand = new Random();
        return rand.nextInt(leftIndex, rightIndex);
    }
}
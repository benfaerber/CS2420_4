package assign03;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Timing experiment for determining if a value is contained in a set.
 *
 * @author CS 2420 Course Staff
 * @version 2025-05-22
 */
public class ArraySetContainsTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "setSize";
    private static int problemSizeMin = 0;
    private static int problemSizeCount = 500;
    private static int problemSizeStep = 5;
    private static int experimentIterationCount = 50;

    private ArraySet<Integer> set = new ArraySet<Integer>();
    private Integer value;

    private Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new ArraySetContainsTimingExperiment();
        timingExperiment.printResults();
    }

    public ArraySetContainsTimingExperiment() {
        super(
            problemSizeDescription,
            problemSizeMin,
            problemSizeCount,
            problemSizeStep,
            experimentIterationCount
        );
    }

    /**
     * Populate the set with the given number of elements and define an element to search for.
     * @param problemSize - the size of the set
     *
     * @implNote We construct the set only one time in the constructor above. In order
     * to set up each experiment, we will clear the set and populate it with new elements.
     * This will likely be more memory-efficient than constructing a new set each time.
     * However, if you prefer, you may define set to be a new ArraySet each time.
     *
     * @implNote By populating the set with random elements, the compiler won't be able
     * to optimize this code which will make the timing experiment more reliable. Each new
     * element will be larger than the previous element by a random amount between 5 and 10.
     *
     * @implNote Since the setup is not being timed, the elements may be inserted in order
     * from smallest to largest in order to speed up the insertion process.
     */
    @Override
    protected void setupExperiment(int problemSize) {
        this.set.clear();
        int element = 100;
        for (int i = 0; i < problemSize; i++) {
            element += rng.nextInt(5, 11);
            this.set.add(element);
        }
        this.value = rng.nextInt(100, element + 1);
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        this.set.contains(this.value);
    }
}

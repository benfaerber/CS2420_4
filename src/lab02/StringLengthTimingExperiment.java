package lab02;

import java.util.Random;

public class StringLengthTimingExperiment extends TimingExperiment {
	private static String problemSizeDescription = "arraySize";
    private static int problemSizeMin = 200000;
    private static int problemSizeCount = 30;
    private static int problemSizeStep = 10000;
    private static int experimentIterationCount = 50;

    private int[] array;
    private Random rng = new Random();

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new StringLengthTimingExperiment();
        timingExperiment.printResults();
    }

    public StringLengthTimingExperiment() {
        super(
            problemSizeDescription,
            problemSizeMin,
            problemSizeCount,
            problemSizeStep,
            experimentIterationCount
        );
    }

    /**
     * Populate the array with random integers.
     * @param problemSize - the size of the array
     */
    @Override
    protected void setupExperiment(int problemSize) {
        array = new int[problemSize];
        for (int i = 0; i < problemSize; i++) {        	
            array[i] = i;
        }
    }

    /**
     * Run the computeMinimum function on the array.
     */
    @Override
    protected void runComputation() {
        ArrayUtility.computeMinimum(array);
    }
}

package timing;

import java.io.FileWriter;
import java.util.Arrays;

/**
 * Abstract base class for running timing experiments.
 *
 * @author CS 2420 course staff
 * @version 2025-05-22
 */
public abstract class TimingWithTimestampExperiment {

    // These attributes control the problem sizes and the number of times to perform each experiment.
    protected String problemSizeDescription;
    protected int problemSizeMin;
    protected int problemSizeStep;
    protected int problemSizeCount;
    protected int experimentIterationCount;

    private long lastTime = 0;

    /**
     * Constructs a general timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeStep          - step size (difference) between consecutive problem sizes
     * @param problemSizeCount         - number of problem sizes to use in the experiment
     * @param experimentIterationCount - number of times to run computation for a given problem size
     */
    public TimingWithTimestampExperiment(
        String problemSizeDescription,
        int problemSizeMin,
        int problemSizeCount,
        int problemSizeStep,
        int experimentIterationCount
    ) {
        this.problemSizeDescription = problemSizeDescription;
        this.problemSizeMin = problemSizeMin;
        this.problemSizeStep = problemSizeStep;
        this.problemSizeCount = problemSizeCount;
        this.experimentIterationCount = experimentIterationCount;
    }

    /**
     * Runs the timing experiment and prints the results.
     */
    public void printResults() {
        StringBuilder results = new StringBuilder();
        System.out.println(problemSizeDescription + "\ttime (ns)");
        int size = problemSizeMin;
        for (int i = 0; i < problemSizeCount; i++) {
            long medianElapsedTime = computeMedianElapsedTime(size);
            this.lastTime = medianElapsedTime;
            System.out.println(size + "\t" + medianElapsedTime);
            results.append(medianElapsedTime).append("\n");
            size += problemSizeStep;
        }

        // David, I added this to copy the results automatically
        // It should work on your computer if you download xclip
        ClipboardHelper.copyToClipboard(results.toString());

        try {
            FileWriter fileWriter = new FileWriter("timestamp.txt");
            fileWriter.write(lastTime + "ms");
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Computes the median time elapsed to run the computation for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     * @return the median elapsed time of the experiment iterations
     */
    protected long computeMedianElapsedTime(int problemSize) {
        long[] elapsedTimes = new long[experimentIterationCount];
        for (int i = 0; i < experimentIterationCount; i++) {
            elapsedTimes[i] = computeElapsedTime(problemSize);
        }
        Arrays.sort(elapsedTimes);
        return elapsedTimes[experimentIterationCount / 2] / 1_000_000;
    }

    /**
     * Computes the time elapsed to run the computation once for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     * @return the time elapsed
     */
    protected long computeElapsedTime(int problemSize) {
        setupExperiment(problemSize);
        long startTime = System.nanoTime();
        runComputation();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Abstract method for setting up the infrastructure for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     */
    protected abstract void setupExperiment(int problemSize);

    /**
     * Abstract method to run the computation to be timed.
     */
    protected abstract void runComputation();
}

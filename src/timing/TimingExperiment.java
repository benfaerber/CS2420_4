package timing;

import java.util.Arrays;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/**
 * Abstract base class for running timing experiments.
 *
 * @author CS 2420 course staff
 * @version 2025-05-22
 */
public abstract class TimingExperiment {

    // These attributes control the problem sizes and the number of times to perform each experiment.
    protected String problemSizeDescription;
    protected int problemSizeMin;
    protected int problemSizeStep;
    protected int problemSizeCount;
    protected int experimentIterationCount;

    /**
     * Constructs a general timing experiment.
     *
     * @param problemSizeDescription   - description of the problem size for the experiment
     * @param problemSizeMin           - minimum array size
     * @param problemSizeStep          - step size (difference) between consecutive problem sizes
     * @param problemSizeCount         - number of problem sizes to use in the experiment
     * @param experimentIterationCount - number of times to run computation for a given problem size
     */
    public TimingExperiment(
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
            System.out.println(size + "\t" + medianElapsedTime);
//            System.out.println(medianElapsedTime);
            results.append(medianElapsedTime).append("\n");
            size += problemSizeStep;
        }

        // David, I added this to copy the results automatically
        // It should work on your computer if you download xclip
        ClipboardHelper.copyToClipboard(results.toString());
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
        return elapsedTimes[experimentIterationCount / 2];
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

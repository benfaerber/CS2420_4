package assign06;

import java.util.ArrayList;
import java.util.Random;

import timing.TimingExperiment;

/**
 * Experiment to measure the running times of copying the elements of a linked list using
 * an enhanced for-loop, for various problem sizes.
 *
 * @author CS 2420 course staff
 * @version February 20, 2025
 */
public class LinkedListStackPushTimingExperiment extends TimingExperiment {
    private static String problemSizeDescription = "Stack Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 60_000;
    private static int problemSizeStep = 5;
    private static int experimentIterationCount = 30;

    private final Random rng = new Random();

    private LinkedListStack<Integer> stack;
    private int currentProblemSize;

    public static void main(String[] args) {
        System.out.println("LinkListStack Timing Experiment");
        TimingExperiment timingExperiment = new SinglyLinkedListForEachTimingExperiment();
        timingExperiment.printResults();
    }

    public LinkedListStackPushTimingExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    /**
     * Fills the SinglyLinkedList with the given number of integers and creates an
     * empty ArrayList.
     *
     * @param problemSize - the number of integers to fill the linked list
     */
    @Override
    protected void setupExperiment(int problemSize) {
        stack = new LinkedListStack<Integer>();
        this.currentProblemSize = problemSize;
    }

    /**
     * Runs the copyLinkedListToArrayList method.
     */
    @Override
    protected void runComputation() {
        for (int i = 0; i < this.currentProblemSize; i++) {
            int randomInt = rng.nextInt(500);
            this.stack.push(randomInt);
        }
    }
}
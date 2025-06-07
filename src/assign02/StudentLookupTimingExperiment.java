package assign02;

import java.util.Arrays;
import java.util.Random;

/**
 * Timing experiment class to study the behavior of looking up a student by uNID.
 *
 * @author CS 2420 course staff & ??
 * @version 2025-05-15
 */
public class StudentLookupTimingExperiment {

    static String problemSizeDescription = "Student List Length";
    /** There can be a minimum of 1 student */
    static int problemSizeMin = 1;
    static int problemSizeStep = 10;
    static int problemSizeCount = 500;
    static int experimentIterationCount = 20;

    public static void main(String[] args) {
        StudentLookupTimingExperiment timingExperiment = new StudentLookupTimingExperiment();

        timingExperiment.printResults();
        System.out.println("Done!");
    }

    private CS2420Class cs2420Class;
    private int randomUNID;
    private static final Random rng = new Random();

    public StudentLookupTimingExperiment() {}

    /**
     * Runs the timing experiment and prints the results.
     */
    public void printResults() {
        System.out.println(problemSizeDescription + "\ttime (ns)");
        int size = problemSizeMin;
        computeMedianElapsedTime(size + 1); // warm-up the system
        for (int i = 0; i < problemSizeCount; i++) {
            long medianElapsedTime = computeMedianElapsedTime(size);
            System.out.println(size + "\t" + medianElapsedTime);
            size += problemSizeStep;
        }
    }

    /**
     * Computes the median time elapsed to run the computation for a given problem size.
     *
     * @param problemSize - the problem size for one experiment
     * @return the median elapsed time of the experiment iterations
     */
    private long computeMedianElapsedTime(int problemSize) {
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
    private long computeElapsedTime(int problemSize) {
        setupExperiment(problemSize);
        long startTime = System.nanoTime();
        runComputation();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    /**
     * Setup the experiment to lookup a random uNID in a CS2420 class of a given size.
     *
     * @param problemSize - the number of students in the CS2420 class
     */
    private void setupExperiment(int problemSize) {
        // populate cs2420Class so that it has `problemSize` number of students
        populateCS2420Class(problemSize);

        // select a random uNID to lookup
        randomUNID = rng.nextInt(2 * problemSize);
    }

    /**
     * Search for the randomUNID in the cs2420Class.
     */
    private void runComputation() {
        cs2420Class.lookup(randomUNID);
    }

    /**
     * Helper method to setup a CS2420 class with a specified number of students.
     *
     * @param numberOfStudents - the number of students to add
     */
    private void populateCS2420Class(int numberOfStudents) {
        // reset cs2420Class to be a new empty class
        cs2420Class = new CS2420Class();

        // add the given number of students
        for (int i = 0; i < numberOfStudents; i++) {
            int uNID = 2 * i + 1; // uNIDs will be odd numbers
            String firstName = "first name #" + i;
            String lastName = "last name #" + i;
            EmailAddress contactInfo = new EmailAddress(firstName, "email.com");
            CS2420Student student = new CS2420Student(
                firstName,
                lastName,
                uNID,
                contactInfo
            );
            cs2420Class.addStudent(student);
        }
    }
}

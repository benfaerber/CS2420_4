package assign09;

import timing.ClipboardHelper;
import timing.TimingExperiment;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class TimingGoodExperiment extends TimingExperiment {

    private static String problemSizeDescription = "HashMap Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 50;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 10;

    private HashTable<StudentGoodHash, Float> students;
    private ArrayList<StudentGoodHash> studentList;
    private int currentCollisions;

    public static void main(String[] args) {
        TimingGoodExperiment experiment = new TimingGoodExperiment();
        experiment.printResults();
    }

    public TimingGoodExperiment() {
        super(problemSizeDescription, problemSizeMin, problemSizeCount, problemSizeStep, experimentIterationCount);
    }

    @Override
    protected void setupExperiment(int problemSize) {
        students = new HashTable<>();
        studentList = new ArrayList<>();
        currentCollisions = 0;

        for (int i = 0; i < problemSize; i++) {
            StudentGoodHash student = new StudentGoodHash(i, "First" + i, "Last" + i);
            studentList.add(student);
        }
    }

    @Override
    protected void runComputation() {
        for (StudentGoodHash student : studentList) {
            currentCollisions += students.putWithCollisionCount(student, (float)Math.random());
        }
    }

    @Override
    public void printResults() {
        StringBuilder csv = new StringBuilder();
        System.out.println("Problem Size" + "\t" + "Time (ns)" + "\t" + "Avg Collisions");

        int size = problemSizeMin;
        for (int i = 0; i < problemSizeCount; i++) {
            long[] times = new long[experimentIterationCount];
            long totalCollisions = 0;

            for (int j = 0; j < experimentIterationCount; j++) {
                setupExperiment(size);
                long start = System.nanoTime();
                runComputation();
                long end = System.nanoTime();
                times[j] = end - start;
                totalCollisions += currentCollisions;
            }

            Arrays.sort(times);
            long medianTime = times[experimentIterationCount / 2];
            long avgCollisions = totalCollisions / experimentIterationCount;

            System.out.println(size + "\t" + medianTime + "\t" + avgCollisions);
            csv.append(size).append(",").append(medianTime).append(",").append(avgCollisions).append("\n");

            size += problemSizeStep;
        }

        ClipboardHelper.copyToClipboard(csv.toString());
    }
}
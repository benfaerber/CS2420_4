package assign09;

import timing.TimingExperiment;

import java.util.ArrayList;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class GoodTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "HashMap Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 1000;
    private static int problemSizeStep = 10;
    private static int experimentIterationCount = 30;

    private HashTable<StudentGoodHash, Float> students;
    private ArrayList<StudentGoodHash> studentList;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new GoodTimingExperiment();
        timingExperiment.printResults();
    }

    public GoodTimingExperiment() {
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
     */
    @Override
    protected void setupExperiment(int problemSize) {
        students = new HashTable<>();
        studentList = new ArrayList<>();
        for (int i = 0; i < problemSizeCount; i++) {
            String firstName = Integer.toString(i * 2);
            String lastName = Integer.toString(i);

            StudentGoodHash student = new StudentGoodHash(i, firstName, lastName);
            studentList.add(student);
        }
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        for (StudentGoodHash student : studentList) {
            students.put(student, (float)Math.random());
        }
    }
}

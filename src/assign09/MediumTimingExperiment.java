package assign09;

import timing.TimingExperiment;

import java.util.ArrayList;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class MediumTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "HashMap Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 1000;
    private static int problemSizeStep = 10;
    private static int experimentIterationCount = 30;

    private HashTable<StudentMediumHash, Float> students;
    private ArrayList<StudentMediumHash> studentList;

    public static void main(String[] args) {
        TimingExperiment timingExperiment = new MediumTimingExperiment();
        timingExperiment.printResults();
    }

    public MediumTimingExperiment() {
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
        studentList = new ArrayList();
        for (int i = 0; i < problemSizeCount; i++) {
            String firstName = Integer.toString(i * 2);
            String lastName = Integer.toString(i);

            StudentMediumHash student = new StudentMediumHash(i, firstName, lastName);
            studentList.add(student);
        }
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        for (StudentMediumHash student : studentList) {
            students.put(student, (float)Math.random());
        }
    }
}

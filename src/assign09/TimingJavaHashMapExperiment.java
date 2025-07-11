package assign09;

import timing.TimingExperiment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Timing experiment for XXXXXXXXXXXXXXXX
 *
 * @author Benjamin Faerber
 * @version 2025-06-12
 */
public class TimingJavaHashMapExperiment extends TimingExperiment {

    private static String problemSizeDescription = "HashMap Size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 50;
    private static int problemSizeStep = 100;
    private static int experimentIterationCount = 10;

    private ArrayList<String> toInsertKeys = new ArrayList<>();
    private ArrayList<Integer> toInsertValues = new ArrayList<>();
    private HashMap<String, Integer> map;


    public static void main(String[] args) {
        TimingExperiment timingExperiment = new TimingJavaHashMapExperiment();
        timingExperiment.printResults();
    }

    public TimingJavaHashMapExperiment() {
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
        map = new HashMap<>();
        toInsertKeys = new ArrayList<>();
        toInsertValues = new ArrayList<>();
        for (int i = 0; i < problemSizeCount; i++) {
            toInsertKeys.add("key" + i);
            toInsertValues.add(i);
        }
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
        for (int i = 0; i < toInsertKeys.size(); i++) {
            String key = toInsertKeys.get(i);
            Integer value = toInsertValues.get(i);
            map.put(key, value);
        }
    }
}

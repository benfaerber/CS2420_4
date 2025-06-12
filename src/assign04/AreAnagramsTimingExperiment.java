package assign04;

import java.util.Random;
import timing.TimingExperiment;

/**
 * Timing experiment for determining add to a set
 *
 * @author Benjamin Faerber
 * @version 2025-05-22
 */
public class AreAnagramsTimingExperiment extends TimingExperiment {

    private static String problemSizeDescription = "String size";
    private static int problemSizeMin = 1;
    private static int problemSizeCount = 300;
    private static int problemSizeStep = 1;
    private static int experimentIterationCount = 30;
    
    private String fakeWordA;
    private String fakeWordB;


    public static void main(String[] args) {
        TimingExperiment timingExperiment = new AreAnagramsTimingExperiment();
        timingExperiment.printResults();
    }

    public AreAnagramsTimingExperiment() {
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
    	String[] letters = "abcdefghijklmnopqrstuvwxyz".split("");
    	StringBuilder sb1 = new StringBuilder();
    	StringBuilder sb2 = new StringBuilder();
    	
    	Random rand = new Random();
    	for (int i = 0; i < problemSize; i++) {
    		sb1.append(letters[rand.nextInt(0, letters.length)]);
    		sb2.append(letters[rand.nextInt(0, letters.length)]);
    	}
    	
//    	System.out.println(this.fakeWordA + " " + this.fakeWordB);
    	this.fakeWordA = sb1.toString();
    	this.fakeWordB = sb2.toString();
    }

    /**
     * Run the contains method for the set and value constructed during setup.
     */
    @Override
    protected void runComputation() {
    	AnagramChecker.areAnagrams(this.fakeWordA, this.fakeWordB);
    }
}

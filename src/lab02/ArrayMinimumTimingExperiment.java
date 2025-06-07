package lab02;

import java.util.Arrays;
import java.util.Random;

/**
 * Timing experiment to measure the time it takes to find the minimum integer in
 * arrays of various sizes.
 *
 * @author CS 2420 Course Staff
 * @version 2025-05-23
 */
public class ArrayMinimumTimingExperiment {

	private int[] array;
	// Random number generator to populate the array
	private Random rng = new Random();
	int arraySizeMin;
	int arraySizeCount;
	int arraySizeStep;
	int experimentIterationCount;

	
	public static void main(String[] args) {
//		int arraySizeMin = 200000;
//		int arraySizeCount = 30;
//		int arraySizeStep = 10000;
//		int experimentIterationCount = 50;
		
		int arraySizeMin = 200000;
		int arraySizeCount = 30;
		int arraySizeStep = 10000;
		int experimentIterationCount = 50;
		
//		int arraySizeMin = 50_000;
//		int arraySizeCount = 100;
//		int arraySizeStep = 1_000;
//		int experimentIterationCount = 50;
		
		// Instantiate the timing experiment
		ArrayMinimumTimingExperiment timingExperiment = new ArrayMinimumTimingExperiment(
		    arraySizeMin,
		    arraySizeCount,
		    arraySizeStep,
		    experimentIterationCount
		);
		
		// Run the experiment and print the results.
		timingExperiment.printResults();
	}
	
	/**
	 * Constructor to build a timing experiment for computing the minimum integer in an array
	 * @param arraySizeMin - minimum array size
	 * @param arraySizeCount - number of array sizes to use in the experiment
	 * @param arraySizeStep - Step size between consecutive array sizes
	 * @param experimentIterationCount - Number of times to run computation for a given array size
	 */
	public ArrayMinimumTimingExperiment(
	    int arraySizeMin,
	    int arraySizeCount,
	    int arraySizeStep,
	    int experimentIterationCount
	) {
	    this.arraySizeMin = arraySizeMin;
	    this.arraySizeCount = arraySizeCount;
	    this.arraySizeStep = arraySizeStep;
	    this.experimentIterationCount = experimentIterationCount;
	}

	/**
	 * Run the timing experiment and print the results.
	 */
	public void printResults() {

		System.out.println("arraySize\ttime (ns)");

		// Initialize the array size at the minimum value
		int arraySize = arraySizeMin;

		// Iterate through the array sizes 
		for (int i = 0; i < arraySizeCount; i++) {
		    // Compute the median elapsed time for the given array size
		    long medianElapsedTime = computeMedianElapsedTime(arraySize);

		    // Print the results
		    System.out.println(arraySize + "\t\t" + medianElapsedTime);

		    // Increment the array size
		    arraySize += arraySizeStep;
		}

	}

	/**
	 * Populate the array with random integers.
	 * 
	 * @param arraySize - size of the array
	 */
	private void setupArray(int arraySize) {
		array = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			array[i] = rng.nextInt();
		}
	}

	/**
	 * Run the computeMinimum function on the array.
	 */
	private void runComputation() {
		ArrayUtility.computeMinimum(array);
	}

	/**
	 * Compute the time elapsed to find the minimum of an array.
	 * 
	 * @param arraySize - size of the array
	 * @return the time elapsed
	 */
	private long computeElapsedTime(int arraySize) {
		setupArray(arraySize);

		long startTime = System.nanoTime();
		runComputation();
		long endTime = System.nanoTime();

		return endTime - startTime;
	}
	
	/**
	 * Compute the median time elapsed to find the minimum of an array.
	 * @param arraySize - the size of the array
	 * @return the median elapsed time
	 */
	private long computeMedianElapsedTime(int arraySize) {
	    long[] elapsedTimes = new long[experimentIterationCount];
	    for (int i = 0; i < experimentIterationCount; i++) {
	        elapsedTimes[i] = computeElapsedTime(arraySize);
	    }
	    Arrays.sort(elapsedTimes);
	    return elapsedTimes[experimentIterationCount / 2];
	}
}

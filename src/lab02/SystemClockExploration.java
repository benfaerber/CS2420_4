package lab02;

/**
 * Explore how the system clock works with various calls to
 * System.currentTimeMillis() and System.nanoTime().
 *
 * @author CS 2420 Course Staff
 * @version 2025-05-23
 */
public class SystemClockExploration {
    public static void main(String[] args) {
//        measureCallsToCurrentTimeMillis();
        measureCallsToNanoTime();
        computeTimeToCallNanoTime();
    }

    /**
     * Measure how many times currentTimeMillis() can be called before it advances.
     */
    private static void measureCallsToCurrentTimeMillis() {
        System.out.println("\n100 observations of how many times currentTimeMillis() can be called before it advances.");
        int checkCount;
        long prevTime, currTime;
        for (int i = 0; i < 100; i++) {
            prevTime = System.currentTimeMillis();
            checkCount = 1;
            currTime = System.currentTimeMillis();
            while (currTime == prevTime) {
                checkCount++;
                currTime = System.currentTimeMillis();
            }
            System.out.println(
                "\tTime checks: " + checkCount +
                    "\tElapsed time (ms): " + (currTime - prevTime)
            );
        }
    }

    /**
     * Measure how many times you can call nanoTime() before it advances.
     */
    private static void measureCallsToNanoTime() {
        System.out.println("\n100 observations of how many times nanoTime() can be called before it advances.");
        int checkCount;
        long prevTime, currTime;
        for (int i = 0; i < 100; i++) {
            prevTime = System.nanoTime();
            checkCount = 1;
            currTime = System.nanoTime();
            while (currTime == prevTime) {
                checkCount++;
                currTime = System.nanoTime();
            }
            System.out.println(
                "\tTime checks: " + checkCount +
                    "\tElapsed time (ns): " + (currTime - prevTime)
            );
        }
    }

    /**
     * Compute total and average time in nanoseconds to call
     * System.nanoTime() one million times.
     */
    private static void computeTimeToCallNanoTime() {
        int iterationCount = 1_000_000;
        long endTime = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < iterationCount; i++) {
            endTime = System.nanoTime();
        }

        System.out.println("\nObserving the time it takes to call System.nanoTime() one million times");
        System.out.println("\tTotal time: " + (endTime - startTime) + " ns");
        System.out.println("\tAverage time: " + ((double) (endTime - startTime) / iterationCount) + " ns per call");
    }
}

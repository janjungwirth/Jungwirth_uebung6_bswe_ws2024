package at.fh_burgenland.bswe.algo.countingsort;

import java.util.ArrayList;
import java.util.Collections;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Simple Counting Sort implementation:
 * - Find max value
 * - Count occurrences in a count array (Hilfsarray)
 * - Build prefix sums in count array (aufsummieren)
 * - Fill output array by placing elements at correct positions
 */
public class CountingSort {
    private static final Logger logger = LogManager.getLogger("ConsoleLogger");
    private static final Logger fileLogger = LogManager.getLogger("FileLogger");
    /**
     * Sorts the given list using Counting Sort.
     *
     * @param list list of non-negative integers
     * @return sorted list
     * @throws ArrayIndexOutOfBoundsException if negative numbers are in input List
     */
    public ArrayList<Integer> sort(ArrayList<Integer> list) throws ArrayIndexOutOfBoundsException {
        long time = System.nanoTime();

        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        long iterationsCounter = 0;
        int max = Collections.max(list);
        int[] count = new int[max + 1];

        // Count occurrences
        for (int num : list) {
            count[num]++;
            iterationsCounter++;
        }

        // Build prefix sums
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
            iterationsCounter++;
        }

        ArrayList<Integer> sorted = new ArrayList<>(Collections.nCopies(list.size(), 0));

        // Place elements from right to left for stability
        for (int i = list.size() - 1; i >= 0; i--) {
            int value = list.get(i);
            int pos = count[value];
            sorted.set(pos - 1, value);
            count[value]--;
            iterationsCounter++;
        }
        long totalTimeToSort = (System.nanoTime() - time);
        logger.info("Counting sort completed. Sorting an input of size {} took {} total iteration steps in {} microseconds", list.size(), iterationsCounter, totalTimeToSort / 1000);
        fileLogger.info(System.getenv().get("COMPUTERNAME") + ": Counting sort completed. Sorting an input of size {} took {} total iteration steps in {} microseconds", list.size(), iterationsCounter, totalTimeToSort / 1000);
        return sorted;
    }
}

package at.fh_burgenland.bswe.algo.bubblesort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * This class provides an implementation of the classic Bubble Sort algorithm.
 * The Bubble Sort algorithm sorts a list by repeatedly stepping through the list, comparing adjacent
 * elements, and swapping them if they are in the wrong order.
 * This process is repeated until the list is sorted.
 */

public class BubbleSortClassic {

    private static final Logger logger = LogManager.getLogger("ConsoleLogger");
    private static final Logger fileLogger = LogManager.getLogger("FileLogger");

    public ArrayList<Integer> sort(ArrayList<Integer> list) {
        //Timer and counter for performance logging
        long time = System.nanoTime();
        long iterationsCounter = 0;

        // Bubble Sort Classic implementation
        boolean swapped;
        for (int i = 0; i < list.size() - 1; i++) {
            swapped = false;
            for (int j = 0; j < list.size() - 1 - i; j++) {
                iterationsCounter++;
                if (list.get(j) > list.get(j + 1)) {
                    // Swap elements
                    int temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                    swapped = true;
                }
            }
            // If no elements were swapped, the list is already sorted
            if (!swapped) break;
        }

        //Log time and iterations
        long totalTimeToSort = (System.nanoTime() - time);
        logger.info("Bubble Sort Classic completed. Sorting an input of size {} took {} total iteration steps in {} microseconds", list.size(), iterationsCounter, totalTimeToSort / 1000);
        fileLogger.info(System.getenv().get("COMPUTERNAME") + ": Bubble Sort Classic completed. Sorting an input of size {} took {} total iteration steps in {} microseconds", list.size(), iterationsCounter, totalTimeToSort / 1000);
        //Return results
        return list;
    }
}

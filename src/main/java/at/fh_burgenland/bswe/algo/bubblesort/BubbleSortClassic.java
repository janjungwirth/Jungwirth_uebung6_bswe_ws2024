package at.fh_burgenland.bswe.algo.bubblesort;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class BubbleSortClassic {

    private static final Logger logger = LogManager.getLogger("ConsoleLogger");

    public ArrayList<Integer> sort(ArrayList<Integer> list) {
        //Timer and counter for performance logging
        long time = System.nanoTime();
        long iterationsCounter = 0;

        //TODO: Algorithm implementation

        //Log time and iterations

        long totalTimeToSort = (System.nanoTime() - time);
        logger.info("Bubble Sort Classic completed. Sorting an input of size {} took {} total iteration steps in {} microseconds", list.size(), iterationsCounter, totalTimeToSort / 1000);
        //Return results
        return null;
    }
}

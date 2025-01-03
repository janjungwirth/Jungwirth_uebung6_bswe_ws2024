package at.fh_burgenland.bswe.algo.util;

import at.fh_burgenland.bswe.algo.bubblesort.BubbleSortClassic;
import at.fh_burgenland.bswe.algo.bubblesortdevideconquer.BubbleSortDivideConquer;
import at.fh_burgenland.bswe.algo.countingsort.CountingSort;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Provides a simple menu for choosing input and sorting options.
 */
public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CountingSort countingsort = new CountingSort();
    private static final BubbleSortClassic bubbleSortClassic = new BubbleSortClassic();
    private static final BubbleSortDivideConquer<Integer> bubbleSortDC = new BubbleSortDivideConquer<>();
    /**
     * Shows the main menu loop.
     */
    public void show() {
        while(true) {
            System.out.println("Welcome in this sorting algorithm application!");
            ColorHelper.printBlue("First choose how to enter the sorting data:");
            ColorHelper.printYellow("1 - Enter from console ...");
            ColorHelper.printYellow("2 - Read from file ...");
            System.out.println("X - exit");
            String line = scanner.nextLine();
            switch (line) {
                case "X", "x" -> System.exit(0);
                case "1" -> enterFromConsole();
                case "2" -> sortFromFile();
                default -> System.err.println("Invalid input. Try again.");
            }
        }
    }

    private void sortMenu(ArrayList<Integer> data) {
        if (data == null || data.isEmpty()) return;
        while(true) {
            System.out.println(data);
            ColorHelper.printBlue("The above list has been entered. Please choose a sorting algorithm:");
            ColorHelper.printYellow("1 - Bubble Sort 'classic'");
            //ColorHelper.printYellow("2 - Bubble Sort 'odd / even'");
            ColorHelper.printYellow("2 - Bubble Sort 'divide & concur''");
            ColorHelper.printYellow("3 - Counting Sort (positive integers only!)");
            System.out.println("X - cancel");
            String line = scanner.nextLine();
            List<Integer> sorted = null;
            switch (line) {
                case "X", "x" -> {
                    return;
                }
                case "1" -> {
                    try {
                        ColorHelper.printBlue("Running Bubble Sort Classic...");
                        sorted = bubbleSortClassic.sort(data);
                        if (sorted == null) {
                            throw new RuntimeException("Bubble Sort Classic returned null. Sorting failed.");
                        }
                        ColorHelper.printGreen("Sorting completed using Bubble Sort Classic!");
                    } catch (RuntimeException e) {
                        System.err.println("An error occurred while running Bubble Sort Classic: " + e.getMessage());
                        throw e;
                    }
                }
                case "2" -> {
                    try {
                        ColorHelper.printBlue("Running Bubble Sort Divide & Conquer...");
                        sorted = bubbleSortDC.sort(data);
                        if (sorted == null) {
                            throw new RuntimeException("Bubble Sort Divide & Conquer returned null. Sorting failed.");
                        }
                        ColorHelper.printGreen("Sorting completed using Bubble Sort Divide & Conquer!");
                    } catch (RuntimeException e) {
                        System.err.println("An error occurred while running Bubble Sort Divide & Conquer: " + e.getMessage());
                        throw e;
                    }
                }
                case "3" ->  {
                    try {
                        sorted = countingsort.sort(data);
                    } catch (ArrayIndexOutOfBoundsException x) {
                        System.err.println("Counting sort can not handle negative numbers! Sorting aborted.");
                        return;
                    }
                }
                default -> {
                    System.err.println("Invalid input. Try again.");
                    continue;
                }
            }
            System.out.println("Sorted result:");
            ColorHelper.printGreen(sorted.toString());
            return;
        }
    }
    private void sortFromFile() {
        ArrayList<Integer> data = loadFile();
        sortMenu(data);
    }

    private ArrayList<Integer> loadFile() {
        while(true) {
            ColorHelper.printBlue("Please choose the file to load:");
            System.out.println("Alternatively enter any valid file path to be loaded.");
            ColorHelper.printYellow("1 - digits.txt");
            ColorHelper.printYellow("2 - numbers_1_to_100.txt");
            ColorHelper.printYellow("3 - Random-Zahlen-die-größer-als-1000-sind.txt");
            ColorHelper.printYellow("4 - Random-Zahlen-von-1-zu-1000.txt");
            String line = scanner.nextLine();
            Path p;
            try {
                if (line.length() == 1) {
                    switch (line) {
                        case "1" -> {
                            p = Paths.get("src", "main", "resources", "digits.txt");
                        }
                        case "2" -> {
                            p = Paths.get("src", "main", "resources", "numbers_1_to_100.txt");
                        }
                        case "3" -> {
                            p = Paths.get("src", "main", "resources", "Random-Zahlen-die-größer-als-1000-sind.txt");
                        }
                        case "4" -> {
                            p = Paths.get("src", "main", "resources", "Random-Zahlen-von-1-zu-1000.txt");
                        }
                        default -> {
                            throw new Exception("Number from 1 - 4 needed.");
                        }
                    }
                } else {
                    p = Paths.get(line);
                }
            } catch (Exception x) {
                System.err.println( "Invalid Input! Enter a number from 1 - 4 or an exact path. Try again! Detailed Error: " + x.getMessage());
                continue;
            }
            return FileReader.readFile(p);
        }
    }

    private void enterFromConsole() {
        String[] input = getListStringFromUser().split(", ");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < input.length; i++) {
            list.add(Integer.parseInt(input[i]));
        }
        sortMenu(list);
    }

    private String getListStringFromUser() {
        while (true) {
            ColorHelper.printBlue("Enter the numbers to be sorted. Input must match the format: 'digit-comma-space-digit...'. Duplicates will result in an error.");
            System.out.println("For example '1, 3, 7, 4, 11, 1231, -50, 6, 9' would be a valid input.");
            String line = scanner.nextLine();
            line = line.trim();
            if (!line.matches("^-?\\d+(, -?\\d+)*$")) {
                System.err.println("Input must match the format: 'digit-comma-space-digit...' try again:");
                continue;
            }
            return line;
        }
    }
}

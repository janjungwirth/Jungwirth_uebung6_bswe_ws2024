package at.fh_burgenland.bswe.algo.util;

import at.fh_burgenland.bswe.algo.bubblesort.BubbleSortClassic;
import at.fh_burgenland.bswe.algo.countingsort.CountingSort;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides a simple menu for choosing input and sorting options.
 */
public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CountingSort countingsort = new CountingSort();
    private static final BubbleSortClassic bubbleSortClassic = new BubbleSortClassic();
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
            ColorHelper.printYellow("2 - Bubble Sort 'odd / even'");
            ColorHelper.printYellow("3 - Bubble Sort 'devide & concur''");
            ColorHelper.printYellow("4 - Counting Sort (positive integers only!)");
            System.out.println("X - cancel");
            String line = scanner.nextLine();
            ArrayList<Integer> sorted = null;
            switch (line) {
                case "X", "x" -> {
                    return;
                }
                case "1" -> {
                    sorted = bubbleSortClassic.sort(data); //TODO @Camila
                    throw new RuntimeException("TODO");
                }
                case "2" -> throw new RuntimeException("TODO"); //TODO @Jan
                case "3" -> throw new RuntimeException("TODO"); //TODO @Jan
                case "4" ->  {
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
            ColorHelper.printBlue("Please enter the exact file path or press enter to use the default (resources/digits.txt)");
            String line = scanner.nextLine();
            Path p;
            try {
                if (line.isEmpty()) {
                    p = Paths.get("src", "main", "resources", "digits.txt");
                } else {
                    p = Paths.get(line);
                }
            } catch (InvalidPathException x) {
                System.err.println( "Invalid Path! Try again! Detailed Error: " + x.getMessage());
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

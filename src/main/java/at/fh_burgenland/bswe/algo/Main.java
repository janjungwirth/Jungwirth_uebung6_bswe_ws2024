package at.fh_burgenland.bswe.algo;

import at.fh_burgenland.bswe.algo.util.Menu;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;

@Log4j2
public class Main {

    public static void main(String[] args) {
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        print2darr(arr);
        System.out.println();
        arr = rotateArrayClockwise(arr);
        print2darr(arr);
        System.out.println();
        countArrayClockw(arr);

        Menu menu = new Menu();
        menu.show();
        //TODO:
        // - Implement Bubble Sort variations
        // - Implement performance testing
        // - Run performance testing on different devices
        // - Write complexity and performance analysis pdf
    }

    public static int[][] rotateArrayClockwise(int[][] array) {

        int length = array.length;
        int[][] rotated = new int[length][length];

        for(int i=0;i<(array.length);i++)
            for (int j=0;j<(array[0].length);j++)
                rotated[i][j]=array[length-1-j][i];

        return rotated;
    }

    public static void countArrayClockw(int[][] array) {

        int length = array.length;
        int[][] rotated = new int[length][length];
        int counter = 0;

        for(int i=0;i<(array.length);i++)
            for (int j=0;j<(array[0].length);j++) {
                counter++;
                rotated[i][j]=counter;
                array[length-1-j][i] = counter;
            }

        print2darr(array);
        System.out.println();
        print2darr(rotated);
    }

    public static void print2darr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }
}

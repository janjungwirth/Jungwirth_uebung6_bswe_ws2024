package at.fh_burgenland.bswe.algo.countingsort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CountingSortTest {

    private final CountingSort sorter = new CountingSort();
    private final Random random = new Random();

    @Test
    @DisplayName("when given an empty list, should return empty list")
    public void testEmptyList() {
        ArrayList<Integer> input = new ArrayList<>();
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("when given a single element, should return the same single element")
    public void testSingleElement() {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(5));
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertEquals(Arrays.asList(5), result);
    }

    @Test
    @DisplayName("when input is already sorted, should remain sorted")
    public void testAlreadySorted() {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4), result);
    }

    @Test
    @DisplayName("when input is reverse ordered, should become sorted in ascending order")
    public void testReverseOrder() {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    @DisplayName("when input contains duplicates, should preserve all duplicates in sorted order")
    public void testDuplicates() {
        ArrayList<Integer> input = new ArrayList<>(Arrays.asList(2, 5, 5, 2, 8, 5));
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertEquals(Arrays.asList(2, 2, 5, 5, 5, 8), result);
    }

    @Test
    @DisplayName("when given a small random input, should return correctly sorted output")
    public void testRandomSmall() {
        ArrayList<Integer> input = generateRandomList(10, 0, 50);
        ArrayList<Integer> sorted = new ArrayList<>(input);
        Collections.sort(sorted);
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertEquals(sorted, result);
    }

    @Test
    @DisplayName("when given a medium random input, should return correctly sorted output")
    public void testRandomMedium() {
        ArrayList<Integer> input = generateRandomList(100, 0, 100);
        ArrayList<Integer> sorted = new ArrayList<>(input);
        Collections.sort(sorted);
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertEquals(sorted, result);
    }

    @Test
    @DisplayName("when given a large random input, should return correctly sorted output")
    public void testRandomLarge() {
        ArrayList<Integer> input = generateRandomList(1000, 0, 1000);
        ArrayList<Integer> sorted = new ArrayList<>(input);
        Collections.sort(sorted);
        ArrayList<Integer> result = sorter.sort(input);
        Assertions.assertEquals(sorted, result);
    }

    /**
     * Helper method to generate a list of random integers.
     */
    private ArrayList<Integer> generateRandomList(int size, int min, int max) {
        ArrayList<Integer> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt((max - min) + 1) + min);
        }
        return list;
    }
}

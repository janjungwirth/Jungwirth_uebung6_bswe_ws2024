package at.fh_burgenland.bswe.algo.BubbleSortDevideConcur;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BubbleSortDeviceConcurTest {
    BubbleSortDeviceConcur<Integer> bs = new BubbleSortDeviceConcur<>();

    @Test
    @DisplayName("Test {} Expect {}")
    public void testEmptyList(){
        List<Integer> testList = List.of();
        List<Integer> expectedList = List.of();

        testList = bs.sort(testList);

        assertEquals(testList, expectedList);
    }

    @Test
    @DisplayName("Test null Expect {}")
    public void testNullList(){
        List<Integer> testList = null;
        List<Integer> expectedList = List.of();

        testList = bs.sort(testList);

        assertEquals(testList, expectedList);
    }

    @Test
    @DisplayName("Test {4,3,2,1} Expect {1,2,3,4}")
    public void testPopulatedEvenList(){
        List<Integer> testList = List.of(4,3,2,1);
        List<Integer> expectedList = List.of(1,2,3,4);
        testList = bs.sort(testList);
        assertEquals(testList, expectedList);
    }

    @Test
    @DisplayName("Test {5,4,3,2,1} Expect {1,2,3,4,5}")
    public void testPopulatedOddList(){
        List<Integer> testList = List.of(5,4,3,2,1);
        List<Integer> expectedList = List.of(1,2,3,4,5);
        testList = bs.sort(testList);
        assertEquals(testList, expectedList);
    }

    @Test
    @DisplayName("Test {5,5,3,2,1} Expect {1,2,3,5,5}")
    public void testListPopulatedWithOneDuplicate(){
        List<Integer> testList = List.of(5,5,3,2,1);
        List<Integer> expectedList = List.of(1,2,3,5,5);
        testList = bs.sort(testList);
        assertEquals(testList, expectedList);
    }

    @Test
    @DisplayName("Test {1} Expect {1}")
    public void testListPopulatedWithOneNumberOnly(){
        List<Integer> testList = List.of(1);
        List<Integer> expectedList = List.of(1);
        testList = bs.sort(testList);
        assertEquals(testList, expectedList);
    }

    @Test
    @DisplayName("Test {1,2,3,4,5} Expect {1,2,3,4,5}")
    public void testAlreadySortedList(){
        List<Integer> testList = List.of(1,2,3,4,5);
        List<Integer> expectedList = List.of(1,2,3,4,5);
        testList = bs.sort(testList);
        assertEquals(testList, expectedList);
    }
}
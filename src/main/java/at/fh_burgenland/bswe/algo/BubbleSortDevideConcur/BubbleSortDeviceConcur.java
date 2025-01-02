package at.fh_burgenland.bswe.algo.BubbleSortDevideConcur;

import java.util.LinkedList;
import java.util.List;

public class BubbleSortDeviceConcur <T extends Comparable<T>>{

    /**
     * Sorts the given list of elements using a bubble sort algorithm implemented in a concurrent sublist manner.
     *
     * @param items the list of elements to be sorted; if null or empty, an empty list is returned
     * @return a sorted list of elements in ascending order
     */
    public List<T> sort(List<T> items) {
        if(items==null || items.isEmpty())
            return new LinkedList<>();
        if( items.size()==1)
            return items;

        final BubbleSortDeviceConcur<T> bs = new BubbleSortDeviceConcur<>();
        final LinkedList<LinkedList<T>> parts = bs.partIntoLists(items);
        do{
            bs.bubbleSubLists(parts);
            parts.getFirst().addFirst(parts.getLast().getFirst());
            parts.getLast().removeFirst();
        }while(!parts.getLast().isEmpty());
        bs.bubbleSubLists(parts);
        return parts.getFirst();
    }

    /**
     * Bubble Sort both parts.
     * This funktion is used to bubble the sub Lists.
     * @param parts the parts
     */
    private void bubbleSubLists(LinkedList<LinkedList<T>> parts) {
        BubbleSortDeviceConcur<T> bs = new BubbleSortDeviceConcur<>();
        parts.set(0, bs.bubbleSort(parts.getFirst()));
        parts.set(1, bs.bubbleSort(parts.getLast()));
    }

    /**
     * Part List in Parts.
     * @param items the initial List
     * @return Parted List.
     */
    private LinkedList<LinkedList<T>> partIntoLists(final List<T> items){
        LinkedList<LinkedList<T>> chunks = new LinkedList<>();
        chunks.add(new LinkedList<>(items.subList(0, items.size()/2)));
        chunks.add(new LinkedList<>(items.subList(items.size()/2, items.size())));
        return chunks;
    }

    /**
     * The Core of the Algorithm.
     * This function bubbles the List in the correct order.
     * @param items the to be sorted List.
     * @return a sorted List.
     */
    private LinkedList<T> bubbleSort(final List<T> items){
        LinkedList<T> itemsCopy = new LinkedList<>(items);
        for(int y = 0; y < items.size(); y++){
            boolean sorted = true;
            for (int i = 0; i < itemsCopy.size()-1; i++) {
                if(itemsCopy.get(i).compareTo(itemsCopy.get(i+1))>0){
                    sorted = false;
                    swap(itemsCopy, i);
                }
            }
            if(sorted) break;
        }
        return itemsCopy;
    }

    /**
     * Swaps two adjacent elements in a list.
     *
     * @param <T> the type of elements in the list, which must be comparable
     * @param items the list containing the elements to be swapped
     * @param i the index of the first element to be swapped with its adjacent element
     */
    private static <T extends Comparable<T>> void swap(List<T> items, int i) {
        T temp = items.get(i);
        items.set(i, items.get(i +1));
        items.set(i +1, temp);
    }
}

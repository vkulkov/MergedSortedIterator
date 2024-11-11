import java.util.*;

public class MergedSortedIterator implements Iterator<Integer> {
    private static class IteratorEntry {
        int value;
        Iterator<Integer> iterator;

        IteratorEntry(int value, Iterator<Integer> iterator) {
            this.value = value;
            this.iterator = iterator;
        }
    }

    private PriorityQueue<IteratorEntry> minHeap;

    public MergedSortedIterator(List<Iterator<Integer>> iterators) {
        minHeap = new PriorityQueue<>(Comparator.comparingInt(entry -> entry.value));

        // Initialize the priority queue with the first element of each iterator (if available)
        for (Iterator<Integer> iterator : iterators) {
            if (iterator.hasNext()) {
                minHeap.offer(new IteratorEntry(iterator.next(), iterator));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !minHeap.isEmpty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        // Extract the smallest element from the heap
        IteratorEntry entry = minHeap.poll();
        int result = entry.value;

        // If the iterator has more elements, add the next element to the heap
        if (entry.iterator.hasNext()) {
            minHeap.offer(new IteratorEntry(entry.iterator.next(), entry.iterator));
        }

        return result;
    }

    public static void main(String[] args) {
        // Example
        List<Integer> list1 = Arrays.asList(6, 8, 19, 21, 32, 66, 67, 77, 89);
        List<Integer> list2 = Arrays.asList(1, 3, 5, 24, 33, 45, 57, 59, 89);
        List<Integer> list3 = Arrays.asList(2, 4, 9, 18, 22, 44, 46, 89, 89);

        Iterator<Integer> it1 = list1.iterator();
        Iterator<Integer> it2 = list2.iterator();
        Iterator<Integer> it3 = list3.iterator();

        MergedSortedIterator mergedIterator = new MergedSortedIterator(Arrays.asList(it1, it2, it3));

        while (mergedIterator.hasNext()) {
            System.out.println(mergedIterator.next());
        }
    }
}

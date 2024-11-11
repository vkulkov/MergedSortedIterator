##Explanation of the Code
###IteratorEntry Class:
- This helper class stores each integer value along with the iterator it came from. This allows us to efficiently pull the next element from the correct iterator.

###PriorityQueue Initialization:
- A min-heap (PriorityQueue) is used, ordered by the integer values from each iterator. The heap is initialized with the first element from each iterator.

###hasNext() Method:
- Returns true if there are more elements across all iterators (i.e., the heap is not empty).

###next() Method:
- Retrieves and removes the smallest element from the heap.
- If the iterator that contributed the smallest element has more elements, the next element from this iterator is added to the heap.

###Efficiency:
- This approach ensures efficient merging by keeping only O(N) elements in memory at any given time (one per iterator). 
- Each insertion and removal operation on the heap takes O(log N), making this solution optimal for merging sorted iterators.

##Algorithm
###Memory Usage:
- At any given time, the algorithm only holds one element from each iterator in the min-heap (i.e., only the current smallest element from each iterator).
- This ensures that the entire content of each iterator is never loaded into memory.

###No Full Sorting in Advance:
- The algorithm sorts elements incrementally as they are processed, using the PriorityQueue (min-heap) to determine the smallest element among the current set of elements from each iterator.
- This way, sorting only happens locally within the heap, and we don’t attempt to sort all elements globally from all iterators upfront.

###Single Pass Through Iterators:
- Each iterator is only advanced as needed to find the next smallest element, rather than loading or pre-processing all elements from each iterator.

The algorithm satisfies the requirement by performing a lazy, on-the-fly merge of the iterators, efficiently yielding the globally sorted sequence without creating a large intermediate data structure containing all elements. This approach maintains low memory usage and avoids loading all elements at once, making it both memory-efficient and compliant with the restriction.
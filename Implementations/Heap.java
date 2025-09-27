// Jai Ganesh
package algorithm.implementation;
import java.util.*;

public class Heap {
    // private constructor
    private Heap(byte heapType) {
        queue = new ArrayList<Integer>();
        this.heapType = heapType;
    }
    private ArrayList<Integer> queue;

    /* 
    heap type can take values (0 or 1) 
    0 (default) means min heap
    1 means max heap
    */
    private byte heapType = 0;

    // Min heap construction
    public static Heap getMinHeap() {
        return new Heap((byte) 0);
    }

    // Max heap construction
    public static Heap getMaxHeap() {
        return new Heap((byte) 1);
    }

    // maintains heap property by propogation heap changes downwards
    private void heapifyDown(int startIndex) {
        int leftChildIndex = 2 * startIndex + 1;
        int rightChildIndex = 2 * startIndex + 2;
        
        // min heap case
        if(heapType == 0) {
            int smallestIndex = startIndex;

            if(leftChildIndex < queue.size() && queue.get(leftChildIndex) < queue.get(smallestIndex)) {
                smallestIndex = leftChildIndex;
            }
            if(rightChildIndex < queue.size() && queue.get(rightChildIndex) < queue.get(smallestIndex)) {
                smallestIndex = rightChildIndex;
            }

            if(smallestIndex != startIndex) {
                int parent = queue.get(startIndex);
                queue.set(startIndex, queue.get(smallestIndex));
                queue.set(smallestIndex, parent);
                heapifyDown(smallestIndex);
            }
        }

        // max heap case
        if(heapType == 1) {
            int largestIndex = startIndex;

            if(leftChildIndex < queue.size() && queue.get(leftChildIndex) > queue.get(largestIndex)) {
                largestIndex = leftChildIndex;
            }
            if(rightChildIndex < queue.size() && queue.get(rightChildIndex) > queue.get(largestIndex)) {
                largestIndex = rightChildIndex;
            }

            if(largestIndex != startIndex) {
                int parent = queue.get(startIndex);
                queue.set(startIndex, queue.get(largestIndex));
                queue.set(largestIndex, parent);
                heapifyDown(largestIndex);
            }
        }
    }

    // maintains heap property by propogation heap changes upwards
    private void heapifyUp(int startIndex) {
        if(startIndex == 0) return;

        int parentIndex = (startIndex - 1) / 2;
        // parent exists
        if(parentIndex >= 0) {
            // min heap case
            if(heapType == 0) {
                if(queue.get(parentIndex) > queue.get(startIndex)) {
                    int parent = queue.get(parentIndex);
                    queue.set(parentIndex, queue.get(startIndex));
                    queue.set(startIndex, parent);
                    heapifyUp(parentIndex);
                }
            }

            // max heap case
            if(heapType == 1) {
                if(queue.get(parentIndex) < queue.get(startIndex)) {
                    int parent = queue.get(parentIndex);
                    queue.set(parentIndex, queue.get(startIndex));
                    queue.set(startIndex, parent);
                    heapifyUp(parentIndex);
                }
            }
        }
    }

    public void push(Integer element) {
        queue.add(element);
        heapifyUp(queue.size() - 1);
    }

    public Integer pop() {
        // Queue is empty, there is nothing to remove
        if(queue.size() == 0) return null;

        Integer lastElement = queue.get(queue.size() - 1);
        Integer firstElement = queue.get(0);
        queue.set(0, lastElement);
        queue.remove(queue.size() - 1);
        heapifyDown(0);
        return firstElement;
    }

    public ArrayList<Integer> getElements() {
        return queue;
    }
}

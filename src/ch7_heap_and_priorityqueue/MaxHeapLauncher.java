package ch7_heap_and_priorityqueue;

public class MaxHeapLauncher {
    public static void main(String[] args) {
        MaxHeap testHeap = new MaxHeap(30);

        testHeap.insert(1);
        testHeap.deleteMax();
        testHeap.insert(2);
        testHeap.insert(4);

        System.out.println(testHeap.maximum());

        testHeap.printHeap();
    }
}

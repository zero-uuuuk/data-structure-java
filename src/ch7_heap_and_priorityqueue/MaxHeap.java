package ch7_heap_and_priorityqueue;

import java.util.Arrays;

import static java.lang.Math.pow;

public class MaxHeap {
    // Declaration
    int count = 0; // The number of element
    int capacity; // Capacity of element
    int[] array; // To implement heap

    MaxHeap(int capacity){
        this.capacity = capacity;
        array = new int[capacity];
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    // Represent Child, Parent.
    // Indexes are used.
    public int left(int i){
        return 2 * i + 1;
    }

    public int right(int i){
        return 2 * i + 2;
    }

    public int parent(int i){
        return (i-1) / 2;
    }

    // Return Maximum: O(1)
    // First Element is the maximum.
    public int maximum(){
        if(count==0){ // Exception Handling
            System.err.println("No elements");
        }
        return array[0];
    }

    // Insert: O(log n)
    // Swap until parent is larger than key.
    public void insert(int key){
        int i = count;
        int p = parent(i);

        while(i > 0 && array[p] < key){ // Move one step.
            array[i] = array[p];
            i = p;
            p = parent(i);
        }

        array[i] = key;
        count++;
    }

    // MaxHeapify: O(log n)
    public void maxHeapify(int i){
        int l = left(i);
        int r = right(i);
        int largest;
        int temp;

        // First, Find Largest Index among (left child, right child, itself)
        if(l < count && array[l] > array[i])
            largest = l;
        else
            largest = i;
        if(r < count && array[r] > array[largest])
            largest = r;

        // Second, Swap Largest and I-th element
        if(largest!=i){
            temp=array[i];
            array[i] = array[largest];
            array[largest] = temp;

            // Third, Repeat
            maxHeapify(largest);
        }

    }

    // DELETE_MAX: O(log n)
    public int deleteMax(){
        int largest = array[0];
        // First, Change Head with Last element
        array[0] = array[count-1];
        count--;

        // Second, MaxHeapify from head
        maxHeapify(0);

        // Third, return largest that is head
        return largest;
    }

    // BuildMaxHeap: O(n)
    public void buildMaxHeap(int[] arr){

        // Base: Init members.
        array = new int[arr.length];
        count = 0;
        capacity = arr.length;

        // First, Copy all content of arr
        for(int i=0; i<arr.length; i++){
            array[i] = arr[i];
            count++;
        }

        // Second, For non-leaf nodes, Repeat maxHeapify
        for(int i=(arr.length-1)/2; i>=0; i--)
            maxHeapify(i);
    }

    // HeapSort: O(nlog n)
    public int[] heapSort(int[] arr){
        //First, make into max-heap
        buildMaxHeap(arr);

        int[] returnArr = new int[arr.length]; // A little space needed, I can say it is in-placed sort.

        // Second, Delete head n times
        for(int i=arr.length-1; i>=0; i--){
            returnArr[i] = deleteMax();
        }

        // Third, Return it.
        return returnArr;
    }

    // Function to print heap as a tree
    public void printHeap() {
        int level = 1;
        int height = 0;
        for(int key:array) {
            if (key != 0) {
                System.out.print((" ".repeat(6 - height)) + key);
                level += 1;
                if (pow(2, level - 1) % level == 0) {
                    System.out.println();
                    height++;
                }
            }
        }
    }
}

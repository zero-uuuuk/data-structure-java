package ch9_search_and_sorting;

import java.util.Arrays;
import java.util.Random;

public class Sorting {

    static int[] array = {3,1,4,8,2,7,9,5,6};

    public static void printArray(int[] arr){
        for(int i: arr)
            System.out.print(i + " ");
    }

    public static void selectionSort(int[] arr){
        // Point: Maintain two subarrays in a given array
        // Those are Sorted Subarray / Unsorted remaining array
        // Time complexity: O(n^2)
        // In-place sort

        // 1. Find Minimum in Unsorted remaining array
        for(int i=0; i<arr.length-1; i++){
            int min = i;
            for(int j=i+1; j<arr.length; j++){
                if(arr[min] > arr[j])
                    min = j;
            }

            // 2. Insert Minimum at the rightmost of sorted array.
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }

        printArray(arr);
    }

    public static void insertionSort(int[] arr){
        // Point: Pick in the unsorted array then Insert into the sorted array.
        // Go-back to remain stable.
        // Time complexity: O(log n)
        for(int i=1; i<arr.length; i++){

            // 1. Pick value
            int value = arr[i];

            // 2. Go back until find proper location.
            int j = i;
            while(j >= 1 && arr[j-1] > value){
                arr[j] = arr[j-1]; // Push element to get one space.
                --j;
            }
            arr[j] = value;
        }

        printArray(arr);
    }

    public static void mergeSort(int[] arr, int[] temp, int left, int right){
        // Point: Divide and Conquer, AND sort when merge
        // Time Complexity: O(nlog n) ( cn(merge) * log n(height))
        if(left<right){
            int mid = (left+right)/2;
            mergeSort(arr, temp, left, mid); // Recursively divide
            mergeSort(arr, temp, mid+1, right);
            merge(arr, temp, left, mid+1, right);
            if(left == 0 && right == 8){
                printArray(arr); // Print
            }
        }
    }

    public static void merge(int[] arr, int[] temp, int lPos, int rPos, int rEnd){
        int lEnd = rPos-1;
        int tPos = lPos;
        int size = rEnd - lPos + 1;

        // 1. Fill the temp array from two sub-arrays
        // Just like moving pointers
        while(lPos <= lEnd && rPos <= rEnd){
            if(arr[lPos] <= arr[rPos])
                temp[tPos++] = arr[lPos++];
            else
                temp[tPos++] = arr[rPos++];
        }
        while(lPos <= lEnd){
            temp[tPos++] = arr[lPos++];
        }

        while(rPos <= rEnd){
            temp[tPos++] = arr[rPos++];
        }

        // 2. Copied into the original array
        for(int i=0; i<size; i++, --rEnd){
            arr[rEnd] = temp[rEnd];
        }
    }

    public static void quickSort(int[] arr, int low, int high){
        if(low<high){
            int p = partition(arr, low, high);
            // int p = randomPartition(arr, low, high)
            quickSort(arr, low, p-1);
            quickSort(arr, p+1, high);
        }
    }

    public static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int left = low, right = high;

        while (left < right) {
            while (arr[left] < pivot)  // pivot의 값과 비교
                left++;
            while (arr[right] >= pivot)
                right--;

            if (left < right) {
                // 값 교환을 해야 함
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        // pivot을 올바른 위치로 이동
        arr[high] = arr[left];
        arr[left] = pivot;

        return left;
    }

    public static int randomPartition(int[] arr, int low, int high){
        Random random = new Random();
        int i = random.nextInt(high - low + 1) + low;

        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return partition(arr, low, high);
    }

    public static int[] countingSort(int[] arrayA){
        // Point: Imagine, Put value into proper location, and Be careful of Index number
        // Time Complexity: O(k + n)

        // Create ArrayB and ArrayC
        int[] arrayB = new int[arrayA.length];
        int k = Arrays.stream(arrayA).max().getAsInt()+1; // Maximum of arrayA
        int[] arrayC = new int[k];

        // Initialize arrayC
        for(int i=0; i<arrayA.length; i++){
            arrayC[arrayA[i]] += 1;
        }

        // Express arrayC in prefix sum
        for(int i=1; i<k; i++)
        {
            arrayC[i] += arrayC[i-1];
        }

        // Allocate value into B
        for(int i=arrayA.length-1; i>=0; i--){
            arrayB[arrayC[arrayA[i]]-1] = arrayA[i];
            arrayC[arrayA[i]] -= 1;
        }

        return arrayB;
    }

    public static int[] countingSortForRadixSort(int[] arrayA, int exp){
        int[] arrayB = new int[arrayA.length];
        int[] temp = new int[arrayA.length];

        // Get number in (epx/10) digit
        for(int i=0; i<arrayA.length; i++){
            temp[i] = (arrayA[i] / exp) % 10;
        }

        int k = Arrays.stream(temp).max().getAsInt()+1;
        int[] arrayC = new int[k];

        for(int i=0; i<arrayA.length; i++){
            arrayC[temp[i]] += 1;
        }

        for(int i=1; i<k; i++)
        {
            arrayC[i] += arrayC[i-1];
        }

        // Point!!!: Store original value of arrayA into the arrayB!!
        for(int i=arrayA.length-1; i>=0; i--){
            arrayB[arrayC[temp[i]]-1] = arrayA[i];
            arrayC[temp[i]] -= 1;
        }

        return arrayB;
    }

    public static int[] radixSort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        int digit = String.valueOf(max).length();
        int[] returnArray = new int[array.length];

        for(int i=1; i<=digit; i++){
            if(i==1)
                returnArray = countingSortForRadixSort(array, (int)Math.pow(10, i-1));
            else
                returnArray = countingSortForRadixSort(returnArray, (int)Math.pow(10, i-1));
        }
        return returnArray;
    }

    public static int randomizedSelect(int[] arr, int low, int high, int i){
        if(low == high){
            return arr[low];
        }
        int p = randomPartition(arr, low, high); //Pivot's absolute Index 1
        int k = p-low+1; //Pivot's rank 2
        if(i == k) //Pivot is the answer
            return arr[p];
        else if(i<k)
            return randomizedSelect(arr, low, p-1, i);
        else
            return randomizedSelect(arr, p+1, high, i-k);
    }
    public static void main(String[] args) {
//        int[] arr = {3,1,4,8,2,7,9,5,6};
//        int[] temp = new int[9];
//        selectionSort(arr);
//        insertionSort(arr);
//        mergeSort(arr, temp, 0, 8);
//        quickSort(array, 0, 8);
//        printArray(array);

//        int[] arrayForCountingSort = {2,5,3,0,2,3,0,3};
//        printArray(countingSort(arrayForCountingSort));

//        int[] arrayForRadixSort = {123, 2154, 222, 4, 283, 1560, 1061, 2150};
//        printArray(radixSort(arrayForRadixSort));

        int[] arrayForRandomizedSelect = {8, 2,6,7,1,5,10,4};
        for(int i=1; i<=8; i++)
            System.out.println(randomizedSelect(arrayForRandomizedSelect, 0, arrayForRandomizedSelect.length-1, i));
    }
}

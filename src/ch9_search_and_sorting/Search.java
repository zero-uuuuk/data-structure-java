package ch9_search_and_sorting;

public class Search {

    static int[] sortedArray;
    static int[] notSortedArray;

    public Search() {
        sortedArray = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        notSortedArray = new int[]{3, 1, 6, 2, 9, 8, 7, 4, 5};
    }

    // Time Complexity: O(n)
    public static int LinearSearch(int target) {
        for (int i = 0; i < notSortedArray.length; i++)
            if (target == notSortedArray[i])
                return i;
        return -1;
    }

    // Time Complexity: O(log n)
    public static int BinarySearchRecursive(int start, int end, int target) {
        if (start > end)
            return -1;
        else {
            int middle = (int) (start + end) / 2;
            if (sortedArray[middle] == target)
                return middle;
            else if (sortedArray[middle] > target)
                return BinarySearchRecursive(start, middle - 1, target);
            else
                return BinarySearchRecursive(middle + 1, end, target);
        }
    }

    // Time Complexity: O(log n)
    // Space Complexity: O(1)
    public static int BinarySearchIterative(int start, int end, int target) {
        while(start <= end) {
            int middle = (int)(start+end)/2;
            if(sortedArray[middle] == target)
                return middle;
            else if(sortedArray[middle] > target) {
                end = middle - 1;
            }
            else
                start = middle + 1;
        }
        return -1;
    }


    public static void main(String[] args) {
        Search search = new Search();
        System.out.println(search.LinearSearch(10));
        System.out.println(search.BinarySearchRecursive(0, search.sortedArray.length-1, 5 ));
        System.out.println(search.BinarySearchIterative(0, search.sortedArray.length-1, 4 ));
    }
}

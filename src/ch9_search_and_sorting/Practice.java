package ch9_search_and_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    Todo: Implement Binary Search
        : Determine Perfect Square using Binary Search

*/
public class Practice {
    public static int binarySearch(int[] arr, int target, int start, int end){
        while(start<=end){
            int mid = (start+end)/2;
            if(arr[mid] == target)
                return mid;
            else if(arr[mid] > target)
                end = mid-1;
            else
                start = mid+1;
        }
        return -1;
    }

    public static boolean perfectSquareBinarySearch(int target){
        int start = 0, end = target;

        while(start <= end) {
            int mid = (start + end) / 2;
            if(mid * mid == target)
                return true;
            else if(mid * mid < target)
                start = mid + 1;
            else
                end = mid - 1;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            // Get Input
            String[] command = br.readLine().split(" ");
            if (command[0].equalsIgnoreCase("B")) {
                int arrayLength = Integer.parseInt(command[1]);
                int target = Integer.parseInt(command[2]);

                int[] arr = new int[arrayLength];
                String[] temp  = br.readLine().split(" ");
                for (int i = 0; i < arrayLength; i++) {
                    arr[i] = Integer.parseInt(temp[i]);
                }
                // Do Binary Search
                System.out.println(binarySearch(arr, target, 0, arrayLength-1));
            }
            else if (command[0].equalsIgnoreCase("P")){
                int target = Integer.parseInt(command[1]);

                // Do find perfect square
                System.out.println(perfectSquareBinarySearch(target));
            }



        }
    }
}

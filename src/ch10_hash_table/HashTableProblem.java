package ch10_hash_table;

/*
    TODO:
        1. Given a list of numbers and a number k, return whether any two numbers
           from the list add up to k.
        2. Given an array of pairs, find all symmetric paris in it. Assume the first
           elements of all pairs are distinct.
*/

/*
    NOTE:
        Problem1.
            (1). Save arr's element into the key, Save Target-element into the value.
            (2). Just Look up whether map.containsValue(arr's element).
            (3). Time Complexity: O(n).
        Problem2.
            (1). Save all into the map.
            (2). Current key's value is in the map.key && map.get(map.key) == current key -> Pairs.
            (3). Time Complexity: O(n).
            (4). Add a list to record.
            (5). To satisfy perfect condition, use ArrayList() as key.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class HashTableProblem {
    public static void isTwoSum() throws IOException {
        // Get Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int target = Integer.parseInt(firstLine[0]);
        int numberOfList = Integer.parseInt(firstLine[1]);
        int[] arr = new int[numberOfList];

        String[] strArr = br.readLine().split(" ");

        for (int i = 0; i < numberOfList; i++) {
            arr[i] = Integer.parseInt(strArr[i]);
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], target-arr[i]);
        }

        for(Integer key:map.keySet()){
            if(map.containsValue(key))
            {
                System.out.println("T");
                return;
            }
        }
        System.out.println("F");
    }

    public static void isSymmetricPairs() throws IOException{
        // Get Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int pairsCount = Integer.parseInt(br.readLine());

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0; i < pairsCount; i++){
            String[] strArr = br.readLine().split(" ");
            int first = Integer.parseInt(strArr[0]);
            int second = Integer.parseInt(strArr[1]);

            map.computeIfAbsent(first, k -> new ArrayList<>()).add(second);
        }

        // Checking set.
        Set<Integer> set = new HashSet<>();

        // Algorithm
        for(Integer key:map.keySet()) {
            for (Integer value : map.get(key)) {
                if (map.get(value) != null && map.get(value).contains(key))
                {
                    if(!set.contains(key) || !set.contains(value)) {
                        set.add(key);
                        set.add(value);
                        System.out.println(key + " " + value);
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
//        isTwoSum();
        isSymmetricPairs();
    }
}

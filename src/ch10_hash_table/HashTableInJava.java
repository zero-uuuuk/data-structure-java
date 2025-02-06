package ch10_hash_table;

import java.util.HashMap;

/*
    TODO: In java, hash-table is implemented by
        (1). HashMap(In single-thread environment, allow null)
        (2). ConcurrentHashMap(In multi-thread environment, Don't allow null)
        Both don't allow duplicated keys.
        Initialization
        - HashMap<Key-type, String-type> map = new HashMap<>();
        Methods
        - put(key, value);
        - get(key): return key;
        - containsKey(key): return true or false;
        - remove(key): remove key and value;
        - keySet(), valueSet()
 */
public class HashTableInJava {
    public static void main(String[] args) {
        // Check Duplicated elements
        HashMap<Integer, Integer> map1 = new HashMap<>();
        int[] arr1 = {5, 7, 1};

        for(int i=0; i<arr1.length; i++){
            map1.put(arr1[i], null);
        }


        if(map1.size() == arr1.length){
            System.out.println("There is no repeated element.");
        }else
            System.out.println("There are repeated elements.");


        // Check Subset
        HashMap<Integer, Integer> map2 = new HashMap<>();
        HashMap<Integer, Integer> map3 = new HashMap<>();

        int[] a = {4, 6, 8};
        int[] b = {2,4,5,8};

        for(int i=0; i<a.length; i++){
            map2.put(a[i], null);
        }
        for(int i=0; i<b.length; i++){
            map3.put(b[i], null);
        }

        for(Integer i : map2.keySet()){
            if(!map3.containsKey(i))
            {
                System.out.println("Not subset");
                System.exit(0);
            }
        }
        System.out.println("Subset");
    }
}

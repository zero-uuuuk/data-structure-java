package ch11_advanced_data;
import java.util.TreeMap;
import java.util.TreeSet;

/*
    TODO: Java 에서 TreeMap, TreeSet 이 Red-Black Tree 로 구현된다.
 */
public class RedBlackTreeInJava {
    public static void main(String[] args) {
        TreeMap<Integer, String> treeMap = new TreeMap<>();

        treeMap.put(3, "Apple");
        treeMap.put(1, "Banana");
        treeMap.put(5, "Cherry");
        treeMap.put(2, "Date");

        // 자동 정렬된 키 출력
        System.out.println(treeMap);
        // 출력: {1=Banana, 2=Date, 3=Apple, 5=Cherry}

        // 특정 키 검색
        System.out.println(treeMap.get(3)); // 출력: Apple

        // 가장 작은 키와 값
        System.out.println(treeMap.firstEntry()); // 출력: 1=Banana

        // 가장 큰 키와 값
        System.out.println(treeMap.lastEntry());

        TreeSet<Integer> treeSet = new TreeSet<>();

        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(8);
        treeSet.add(1);
        treeSet.add(3);

        System.out.println(treeSet);
        // 출력: [1, 2, 3, 5, 8] (자동 정렬)

        // 특정 값이 있는지 확인
        System.out.println(treeSet.contains(3)); // 출력: true
        System.out.println(treeSet.contains(10)); // 출력: false

        // 가장 작은 값
        System.out.println(treeSet.first()); // 출력: 1

        // 가장 큰 값
        System.out.println(treeSet.last()); // 출력: 8

        // 특정 값보다 큰 값 중 가장 작은 값
        System.out.println(treeSet.higher(3)); // 출력: 5

        // 특정 값보다 작은 값 중 가장 큰 값
        System.out.println(treeSet.lower(3)); // 출력: 2
    }
    }


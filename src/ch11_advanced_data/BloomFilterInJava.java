package ch11_advanced_data;
import java.util.BitSet;
import java.util.Random;

import static java.lang.Math.pow;

class BloomFilter {
    private final int size;       // 비트 배열 크기
    private final int numHashes;  // 해시 함수 개수
    private final BitSet bitSet;  // 비트 배열

    public BloomFilter(int size, int numHashes) {
        this.size = size;
        this.numHashes = numHashes;
        this.bitSet = new BitSet(size);
    }

    // 해시 함수 생성 (간단한 해싱 기법 사용)
    private int[] hash(String key) {
        int[] hashes = new int[numHashes];
        Random rand = new Random(key.hashCode()); // key 기반 랜덤 시드 설정
        for (int i = 0; i < numHashes; i++) {
            hashes[i] = Math.abs(rand.nextInt()) % size; // 0 ~ (size - 1) 사이의 값 생성
        }
        return hashes;
    }

    // 원소 추가
    public void add(String key) {
        int[] hashes = hash(key);
        for (int hash : hashes) {
            bitSet.set(hash);
        }
    }

    // 원소 존재 여부 확인
    public boolean contains(String key) {
        int[] hashes = hash(key);
        for (int hash : hashes) {
            if (!bitSet.get(hash)) {
                return false; // 하나라도 0이면 존재할 가능성이 없음
            }
        }
        double probability = pow((1- pow(1-((double) 1 /size), numHashes)), 3);
        System.out.println(probability);
        return true; // 모든 해시 위치가 1이면 존재할 가능성이 높음 (False Positive 가능)
    }

    // 비트 배열 출력 (디버깅용)
    public void printBitSet() {
        System.out.println(bitSet);
    }
}

public class BloomFilterInJava {
    public static void main(String[] args) {
        BloomFilter filter = new BloomFilter(1000, 3); // 비트 배열 크기: 1000, 해시 함수 개수: 3

        // 데이터 추가
        filter.add("apple");
        filter.add("banana");
        filter.add("cherry");

        // 존재 여부 확인
        System.out.println(filter.contains("apple"));   // true (확실함)
        System.out.println(filter.contains("banana"));  // true (확실함)
        System.out.println(filter.contains("grape"));   // false (확실함)
        System.out.println(filter.contains("mango"));   // false or true (False Positive 가능)

        // 비트 배열 출력
        filter.printBitSet();
    }
}

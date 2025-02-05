# Priority Queue와 Max Heap

---

## 1. Priority Queue

### 1.1 특징
- `pop()`, `peek()`는 **가장 높은 우선순위** 요소에서 작동.
- `Insert`는 자유롭게 삽입 가능하지만, 삭제(`pop()`)는 **우선순위 순서**에 따라 진행.
- 삽입은 Natural Order를 따르며, 삭제는 Queue의 규칙과 동일.

### 1.2 주요 메서드
- `Insert(x)`  
  → 우선순위 큐에 `x` 삽입.
- `Maximum()`  
  → 큐에서 최댓값 반환(= `peek()`).
- `DeleteMax()`  
  → 큐에서 최댓값 삭제 및 반환(= `poll()`).

### 1.3 활용 사례
- **병원 응급 시스템**  
  환자의 심각성에 따라 삽입(`Insert`), 심각도가 높은 순서로 삭제(`pop()`).
- **작업 스케줄링**, **최단 경로 문제**, **최소 신장 트리**.

---

## 2. Max Heap

### 2.1 특징
- **부모 노드 > 자식 노드** 관계 유지(Max-Heap의 경우).  
  (Min-Heap의 경우, 부모 노드 < 자식 노드)
- **Complete Tree** 성질 보유: 왼쪽부터 빈틈없이 채워짐.
- 자식 노드 간 대소 비교는 하지 않음.  
  따라서 **탐색(Search)** 메서드는 없음.

---

### 2.2 구현

#### 2.2.1 멤버 선언
```java
int count = 0;        // 현재 요소 개수
int capacity;         // 최대 요소 개수
int[] array;          // Heap 구현에 사용할 배열

MaxHeap(int capacity) {
    this.capacity = capacity;
    array = new int[capacity];
}

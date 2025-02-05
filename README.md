# Lecture Notes

## Lecture 1 - ADT와 Recursive Algorithm

### 1. ADT (Abstract Data Type)
- ADT는 Data Structure의 일종의 Guide line이다.
- 예:
  - Heap이라는 ADT(Guideline)가 주어지면 이를 array로 구현한다.
  - LinkedList라는 ADT가 주어지면, 해당 ADT의 조건을 만족하는 Data Structure를 list로 구현한다.
- ADT에 포함되어야 할 4가지:
  1. Function name
  2. Argument types
  3. Result types
  4. Description of what the function does

### 2. Recursive Algorithm
- 특정 문제를 sub-problem으로 쪼개서 해결하는 방식.
- 조건:
  1. Base case (Termination)
  2. Recursive case
- 대표적인 예시:
  - Fibonacci Sequence
  - Factorial
  - Hanoi Tower

## Lecture 2 - Time Complexity와 Space Complexity

### 1. Space Complexity
- 알고리즘의 메모리 사용량 평가.
- Input data의 size와 상관없이 알고리즘 자체의 memory space만 고려.

### 2. Time Complexity
- 알고리즘의 시간 복잡도를 평가.
- Hardware (RAM, CPU)와 Input data에 상관없이 걸리는 시간 체크.

#### 2.1 Big-Oh
- 시간 복잡도의 상한선.
- 예: 2n+7인 알고리즘은 O(n)의 시간 복잡도를 가짐.
- 2n+7 <= cn을 만족하는 c와 n(0)을 찾아 증명.

#### 2.2 Big-Omega
- 시간 복잡도의 하한선.
- 예: 2n+7인 알고리즘은 Ω(n)의 시간 복잡도를 가짐.

#### 2.3 Big-Theta
- Big-Oh와 Big-Omega의 조건을 모두 만족.

- 일반적으로 최악의 경우를 고려해 Big-Oh 사용.

## Lecture 3 - LinkedList

### 1. 개요
- Array 사용의 단점을 보완:
  - Dynamic size 변경의 어려움.
  - 연속적인 메모리 사용.
- LinkedList는 이러한 단점을 해결.

### 2. 주요 특징 및 Method
- Node 기반 Data Structure.
- 각 Node는 자기 자신의 값과 다음 Node를 가리키는 Pointer를 가짐.
- 시작 Node는 Head로 지칭.

#### Method
1. Traversing
2. Search
3. Insert(x):
  - 제일 앞: x.next 설정 후 Head 변경.
  - 마지막: 마지막 Node의 next를 x로 설정.
  - 중간: x.next를 temp로 설정.
4. Delete(x)

#### 확장
- **Doubly Linked List**:
  - next와 prev Node 모두를 가리킴.
  - Method는 기존 LinkedList와 동일.

- **Java의 LinkedList**:
  - `List<String> list = new LinkedList<>();`
  - add, delete, find, size() 및 Iterator 사용 가능.
  - JVM이 메모리를 관리.

## Lecture 4 - Stack and Queue

### 1. Stack
- First In, Last Out으로 작동.
- **Java 선언 및 Method**:
  - 선언: `Stack<dataType> stack = new Stack<>();`
  - Method:
    1. `push()`: stack의 뒤에서 삽입.
    2. `pop()`: stack의 맨 뒤를 삭제 후 반환.
    3. `peek()`: stack의 맨 뒤를 반환.
    4. `empty()`: stack이 비어 있는지 여부를 반환.

#### 활용 예시
- Recursive function의 call stack.
- Text editor의 Undo 기능.
- Web browser의 방문 기록.
- 수학 표현의 괄호 매칭.
- HTML 태그 매칭.

### 2. Queue
- First In, First Out으로 작동.
- **Java 선언 및 Method**:
  - 선언: `Queue<dataType> queue = new Queue<>();`
  - Method:
    1. `offer()`: queue의 뒤에서 삽입.
    2. `poll()`: queue의 head를 제거 후 반환.
    3. `peek()`: queue의 head를 반환.

#### 활용 예시
- Printing documents.
- Message queues.

### 3. 활용
1. Palindrome 인식:
  - queue와 stack에 저장 후 queue는 뒤에서, stack은 앞에서 비교.
2. Graph에서 Flight Map 검색:
  - Reachable한 목적지를 queue, stack에 저장 후 추가 reachable 고려.

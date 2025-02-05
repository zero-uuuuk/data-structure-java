Lecture6 - AVL Tree

1. 등장 배경
- 기존 Binary Tree
    - Insert, Delete 작업을 여러 번 수행 시, Tree의 Balance가 무너지는 현상 발생.
    - 이를 'Skew tree'라고 말하며, Insert, Delete, Search 등의 Time comeplexity는 O(log n)으로 유지되지 못함.

- AVL Tree의 조건
    - Binary Search Tree
    - 모든 노드에 대해서 -1 <= leftTree.height - rightTree.height <= 1 일 것.
    - Height h가 주어질 때, 가능한 노드를 꽉 채워서 Tree를 만들어야된다는 말과 같음.

2. 구현
- 기본 개념: Rotation
    - Insert, Delete 등으로 Balance가 무너질 경우, Rotation(Trinode Restructuring)으로 이를 해결
    - (1). 최초의 Balance가 발생한 Node: z, z의 child 중 높이가 더 큰 Node: y, y의 child 중 높이가 더 큰 Node: x(높이가 같을 경우 y의 방향을 따라간 것)로 지정
    - (2). Insert / Delete 이후 root.left / right height를 분석 후 LL, LR, RR, RL Rotation 적용.
    - (3). Root의 Height update


3. 분석 조건
- 이때 root = z
- Insert
  (1). root.left.height - root.right.height > 1: Left tree가 더 높을 경우 ( y = Left)
  - key < root.left.key: 새로 Insert된 key가 또 Left tree로 갔을 경우 -> LL Rotation ( x = Left)
  - key > root.left.key: 새로 Insert된 key가 Right tree로 갔을 경우 -> LR Rotation ( x = Right)
  (2). root.right.height - root.left.height > 1: Right tree가 더 높을 경우 ( y = Right)
  - key < root.right.key: 새로 Insert된 key가 Left tree로 갔을 경우 -> RL Rotation ( x = Left)
  - key > root.right.key: 새로 Insert된 key가 또 Right tree로 갔을 경우 -> RR Rotation ( x = Right)

- Delete
  (1). root.left.height - root.right.height > 1: Left tree가 더 높을 경우 ( y = Left)
  - root.left.left.height - root.left.right.height >= 0 : 하위 Left tree가 더 높을 경우 -> LL Rotation( x = Left)
  - else : 하위 Right tree가 더 높을 경우 -> LR Rotation( x = Right)

  (2). root.right.height - root.left.height > 1: Right tree가 더 높을 경우 ( y = Right)
  - root.right.right.height - root.right.left.height >= 0 : 하위 Right tree가 더 높을 경우 -> RR Rotation( x = Right)
  - else : 하위 Left tree가 더 높을 경우 -> RL Rotation( x = Left) 

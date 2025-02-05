//package avl_tree_ch6.practice;
//
//class AVLNode {
//    int key, height;
//    AVLNode left, right;
//
//    AVLNode(int d) {
//        key = d;
//        height = 1; // 새 노드의 초기 높이는 1
//    }
//}
//
//class AVLTree {
//    private AVLNode root;
//
//    // 노드의 높이를 가져오는 유틸리티 메서드
//    private int height(AVLNode n) {
//        return n == null ? 0 : n.height;
//    }
//
//    // 주어진 두 숫자 중 최대값을 반환
//    private int max(int a, int b) {
//        return (a > b) ? a : b;
//    }
//
//    // 오른쪽 회전
//    private AVLNode rightRotate(AVLNode y) {
//        AVLNode x = y.left;
//        AVLNode T2 = x.right;
//
//        // 회전 수행
//        x.right = y;
//        y.left = T2;
//
//        // 높이 업데이트
//        y.height = max(height(y.left), height(y.right)) + 1;
//        x.height = max(height(x.left), height(x.right)) + 1;
//
//        return x; // 새로운 루트 반환
//    }
//
//    // 왼쪽 회전
//    private AVLNode leftRotate(AVLNode x) {
//        AVLNode y = x.right;
//        AVLNode T2 = y.left;
//
//        // 회전 수행
//        y.left = x;
//        x.right = T2;
//
//        // 높이 업데이트
//        x.height = max(height(x.left), height(x.right)) + 1;
//        y.height = max(height(y.left), height(y.right)) + 1;
//
//        return y; // 새로운 루트 반환
//    }
//
//    // 노드의 균형 계수를 계산
//    private int getBalance(AVLNode n) {
//        return n == null ? 0 : height(n.left) - height(n.right);
//    }
//
//    // 키를 삽입
//    public AVLNode insert(AVLNode node, int key) {
//        // 1. BST 삽입
//        if (node == null) {
//            return new AVLNode(key);
//        }
//        if (key < node.key) {
//            node.left = insert(node.left, key);
//        } else if (key > node.key) {
//            node.right = insert(node.right, key);
//        } else {
//            return node; // 중복 키는 허용하지 않음
//        }
//
//        // 2. 높이 업데이트
//        node.height = 1 + max(height(node.left), height(node.right));
//
//        // 3. 균형 계수 계산
//        int balance = getBalance(node);
//
//        // 4. 균형을 유지하기 위한 회전 수행
//
//        // LL 케이스
//        if (balance > 1 && key < node.left.key) {
//            return rightRotate(node);
//        }
//
//        // RR 케이스
//        if (balance < -1 && key > node.right.key) {
//            return leftRotate(node);
//        }
//
//        // LR 케이스
//        if (balance > 1 && key > node.left.key) {
//            node.left = leftRotate(node.left);
//            return rightRotate(node);
//        }
//
//        // RL 케이스
//        if (balance < -1 && key < node.right.key) {
//            node.right = rightRotate(node.right);
//            return leftRotate(node);
//        }
//
//        return node; // 노드의 참조 반환
//    }
//
//    public void insert(int key) {
//        root = insert(root, key);
//    }
//
//    // 트리 출력 (중위 순회)
//    public void inOrder(AVLNode node) {
//        if (node != null) {
//            inOrder(node.left);
//            System.out.print(node.key + " ");
//            inOrder(node.right);
//        }
//    }
//
//    public void printTree() {
//        inOrder(root);
//    }
//}
//
//public class Main {
//    public static void main(String[] args) {
//        AVLTree tree = new AVLTree();
//
//        tree.insert(10);
//        tree.insert(20);
//        tree.insert(30);
//        tree.insert(40);
//        tree.insert(50);
//        tree.insert(25);
//
//        System.out.println("중위 순회 결과:");
//        tree.printTree();
//    }
//}

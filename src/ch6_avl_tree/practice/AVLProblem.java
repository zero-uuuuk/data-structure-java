package ch6_avl_tree.practice;

public class AVLProblem {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(60);

        tree.delete(tree.root, 40);
        tree.printTree();
    }
}

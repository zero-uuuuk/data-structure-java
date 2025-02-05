package ch6_avl_tree.practice;

public class AVLNode {
    int key;
    int height = 0;

    AVLNode left;
    AVLNode right;

    AVLNode(int key, AVLNode left, AVLNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
                "height=" + height +
                ", key=" + key +
                '}';
    }
}

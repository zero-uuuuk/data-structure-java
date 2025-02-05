package ch6_avl_tree.practice;

public class AVLTree {
    AVLNode root;

    public int getHeight(AVLNode node){
        if(node == null)
            return 0;
        else
            return node.height;
    }

    public AVLNode insert(AVLNode root, int key){
        if(root == null)
            root = new AVLNode(key, null, null);
        else if(key < root.key){
           root.left = insert(root.left, key);
           if(getBalance(root) > 1){
               if(key < root.left.key)
                   root = leftRotate(root);
               else
                   root = leftRightRotate(root);
           }
        }
        else if(key > root.key){
            root.right = insert(root.right, key);
            if(getBalance(root) < -1){
                if(key > root.right.key)
                    root = rightRotate(root);
                else
                    root = rightleftRotate(root);
            }
        }
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        return root;
    }

    public void insert(int key){
        root = insert(root, key);
    }

//    public AVLNode search(AVLNode root, int key)   {
//
//        if(root == null)
//            System.out.println("The tree is empty");
//        else if(root.key == key)
//            return root;
//        else if(root.key > key)
//            root.left = search(root.left, key);
//        else
//            root.right = search(root.right, key);
//        return root;
//    }
//
//    public AVLNode search(int key){
//        return search(root, key);
//    }

    public AVLNode delete(AVLNode root, int key){
        if(root == null)
            return root;
        else if(key < root.key){
            root.left = delete(root.left, key);
        }else if(key > root.key){
            root.right = delete(root.right, key);
        }else {
            if(root.left == null)
            {
                return root.right;
            }
            else if (root.right == null)
                return root.left;
            AVLNode succ = findMin(root.right);
            root.key = succ.key;
            root.right = delete(root.right, root.key);
            }
        if(root == null)
            return root;

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        if(getBalance(root) > 1){
            if(getHeight(root.left.left) - getHeight(root.left.right) >= 0)
                root = leftRotate(root);
            else
                root = leftRightRotate(root);
        }
        if(getBalance(root) < -1){
            if(getHeight(root.right.left) - getHeight(root.right.right) >= 0)
                root = rightleftRotate(root);
            else
                root = rightRotate(root);
        }

        return root;
    }

    private AVLNode findMin(AVLNode node) {
        AVLNode current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public int getBalance(AVLNode root){
        return getHeight(root.left) - getHeight(root.right);
    }

    public AVLNode leftRotate(AVLNode root){
        AVLNode y = root.left;
        root.left = y.right;
        y.right = root;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    public AVLNode rightRotate(AVLNode root){
        AVLNode y = root.right;
        root.right = y.left;
        y.left = root;
        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }

    public AVLNode leftRightRotate(AVLNode root){
        root.left = rightRotate(root.left);
        return leftRotate(root);
    }

    public AVLNode rightleftRotate(AVLNode root){
        root.right = leftRotate(root.right);
        return rightRotate(root);
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(AVLNode node, int depth) {
        if (node == null) return;

        // 오른쪽 서브트리를 먼저 출력
        printTree(node.right, depth + 1);

        // 현재 노드 출력 (들여쓰기 포함)
        System.out.println(" ".repeat(depth * 4) + node.key);

        // 왼쪽 서브트리를 출력
        printTree(node.left, depth + 1);
    }
}

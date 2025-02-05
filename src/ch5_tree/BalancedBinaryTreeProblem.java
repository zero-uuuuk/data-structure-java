package ch5_tree;

import java.util.*;

/*
   Problem
    : (1). Sorted 된 Array 가 주어졌을 때, Binary Balanced Tree 로 바꾸고, min, max Method 만들기 이때 'Node' 중심으로 Tree 를 만들 것.
    : (2). Search Method 구현 및 예외 처리
    : (3). Traversal 구현

   Idea
    : BinarySearch 의 성질을 이용해서 Recursive 하게 Middle 값을 ch5_tree.TreeNode 로 만들기
    : ch5_tree.TreeNode 로 만들었다면, Constructor 로 Left/Right Node 연결
 */
class TreeNode{
    public int key;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int key, TreeNode left, TreeNode right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "ch5_tree.TreeNode{" +
                "key=" + key +
                '}';
    }
}

class BinaryTree{
    public TreeNode root;

    public BinaryTree(){

    }

    public BinaryTree(List<Integer> list){
        this.root = makeBinarySearch(list, 0, list.size()-1);
    }

    public TreeNode makeBinarySearch(List<Integer> list, int leftIndex, int rightIndex){

        if(leftIndex > rightIndex) { // Base case(Termination)
            return null;
        }

        int middleIndex = (leftIndex + rightIndex) / 2;

        TreeNode leftNode = makeBinarySearch(list, leftIndex, middleIndex-1); // Middle index 를 제외한 Left List 로 Recursive
        TreeNode rightNode = makeBinarySearch(list, middleIndex+1, rightIndex); // Right List 로 Recursive

        TreeNode middleNode = new TreeNode(list.get(middleIndex), leftNode, rightNode);

        return middleNode;
    }

    public TreeNode findMin(){
        TreeNode x = root;
        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    public TreeNode findMax(){
        TreeNode x = root;
        while(x.right != null){
            x = x.right;
        }
        return x;
    }

    public TreeNode searchByKey(TreeNode temp, int key) throws Exception{
        /*
            Description: Find Proper node using key
            Idea: Recursively go down, comparing value with childs
            Return:
                If find -> Return node
                Else -> Throws exception
         */
        if (temp == null) {
            throw new Exception("Failed to find key");
        } else {
            if (temp.key == key)
                return temp;
            else if (temp.key > key)
                return searchByKey(temp.left, key);
            else if (temp.key < key)
                return searchByKey(temp.right, key);

        }
        return null;
    }

    public void inorderTraversal(TreeNode temp){
        /*
            Description: Traverse Tree in 'inorder'
            Idea: Recursively go down, put sout in proper location
         */
        if(temp != null) {
            inorderTraversal(temp.left);
            System.out.print(temp.key + " ");
            inorderTraversal(temp.right);
        }
    }

    public void preOrderTraversal(TreeNode temp){
        if(temp != null) {
            System.out.print(temp.key + " ");
            preOrderTraversal(temp.left);
            preOrderTraversal(temp.right);
        }
    }

    public void postOrderTraversal(TreeNode temp){
        if(temp != null) {
            postOrderTraversal(temp.left);
            postOrderTraversal(temp.right);
            System.out.print(temp.key + " ");
        }
    }

    public TreeNode insertByKey(int key, TreeNode node) {
        if(root == null)
        {
            root = new TreeNode(key, null, null);
        }
        else if(node == null){
            node = new TreeNode(key, null, null);
        }
        else if(key < node.key){
            node.left = insertByKey(key, node.left);
        }
        else if(key > node.key){
            node.right = insertByKey(key, node.right);
        }
        return node;
    }

    public void printPrettyTree() {
        int height = getHeight(root);
        printTree(Collections.singletonList(root), 1, height);
    }

    private void printTree(List<TreeNode> nodes, int level, int height) {
        if (nodes.isEmpty() || allElementsNull(nodes)) {
            return;
        }

        int floor = height - level;
        int edgeLines = (int) Math.pow(2, Math.max(floor - 1, 0));
        int firstSpaces = (int) Math.pow(2, floor) - 1;
        int betweenSpaces = (int) Math.pow(2, floor + 1) - 1;

        printSpaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<>();
        for (TreeNode node : nodes) {
            if (node != null) {
                System.out.print(node.key);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                System.out.print(" ");
                newNodes.add(null);
                newNodes.add(null);
            }
            printSpaces(betweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (TreeNode node : nodes) {
                printSpaces(firstSpaces - i);
                if (node == null) {
                    printSpaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (node.left != null) {
                    System.out.print("/");
                } else {
                    printSpaces(1);
                }

                printSpaces(i + i - 1);

                if (node.right != null) {
                    System.out.print("\\");
                } else {
                    printSpaces(1);
                }

                printSpaces(edgeLines + edgeLines - i);
            }
            System.out.println();
        }

        printTree(newNodes, level + 1, height);
    }

    private void printSpaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    private boolean allElementsNull(List<TreeNode> list) {
        for (TreeNode node : list) {
            if (node != null) {
                return false;
            }
        }
        return true;
    }

}


public class BalancedBinaryTreeProblem {
    public static void main(String[] args) {
        Integer[] arr = {11, 23, 36, 48, 51, 59, 63, 71, 86, 92};
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        if (list.stream().sorted().toList().equals(Arrays.asList(arr))) {
            BinaryTree test = new BinaryTree(list);

            test.printPrettyTree();

            System.out.println(test.findMin());
            System.out.println(test.findMax());
            try {
                System.out.println(test.searchByKey(test.root, 11));
                test.inorderTraversal(test.root);
                System.out.println();
                test.preOrderTraversal(test.root);
                System.out.println();
                test.postOrderTraversal(test.root);
                System.out.println();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        else{
            System.out.println("BUILD: invalid input");
        }

        BinaryTree newTest = new BinaryTree();
        newTest.insertByKey(7, newTest.root);
        newTest.insertByKey(6, newTest.root);
        newTest.insertByKey(5, newTest.root);
        newTest.insertByKey(9, newTest.root);
        newTest.insertByKey(3, newTest.root);

        newTest.printPrettyTree();





    }
}

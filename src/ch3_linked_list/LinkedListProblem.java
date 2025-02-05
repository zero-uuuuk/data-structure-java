package ch3_linked_list;/*
    Problem: LinkedList 구현하기
    Condition:
        (1). Node는 이름과 ID를 가짐.
        (2). add() 구현 시 자동으로 ascending order가 되도록.
        (3). delete(), find(), printAll() 함수 구현.

    Point:
        (1). 우선 Node를 먼저 구현하기.
        (2). LinkedList에 Head Node와 다른 Method 규정하기.
        (3). while문을 이용한 Traversing.
 */

class StudentNode{
    StudentNode next = null;
    String studentName;
    int studentID;

    public StudentNode(String studentName, int studentID){
        this.studentID = studentID;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "ch3_linked_list.StudentNode{" +
                "studentName='" + studentName + '\'' +
                ", studentID=" + studentID +
                '}';
    }
}

class StudentLinkedList {
    StudentNode head = null;

    public void add(StudentNode node) {
        if (head == null) { // (0). Insert First Node
            head = node;
            return;
        }

        if (node.studentID < head.studentID) { // (1). Insert Head
            node.next = head;
            head = node;
        } else {
            StudentNode x = head;
            while (x.next != null) { // x가 마지막 노드일 때 까지
                if (node.studentID > x.studentID && node.studentID < x.next.studentID) { //(2). Insert Middle
                    node.next = x.next;
                    x.next = node;
                    return;
                }
                x = x.next;
            }
            if(x.studentID < node.studentID)
                x.next = node; // (3). Insert Last
            else{
                System.out.println("Addition failed");
            }
        }
    }

    public void printAll() {
        StudentNode x = head;
        while (x.next != null) {
            System.out.println(x);
            x = x.next;
        }
        System.out.println(x);
    }

    public void delete(int id) {
        if (head.studentID == id) {
            head = head.next;
        } else {
            StudentNode x = head;
            while (x.next.next != null) {
                if (x.next.studentID == id) {
                    x.next = x.next.next;
                    return;
                }
                x = x.next;
            }
            if(x.next.studentID == id)
                x.next = null;
            else{
                System.out.println("Deletion Failed");
            }
        }
    }

    public StudentNode find(int id) {
        if (head.studentID == id) {
            return head;
        } else {
            StudentNode x = head;
            while (x.next.next != null) {
                if (x.next.studentID == id) {
                    x.next = x.next.next;
                    return x.next;
                }
                x = x.next;
            }
            if(x.next.studentID == id)
                return x.next;
            else{
                System.out.println("Search Failed");
                return null;
            }
        }
    }
}

public class LinkedListProblem {
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();
        list.add(new StudentNode("Mike", 10003));
        list.add(new StudentNode("Alice", 10000));
        list.add(new StudentNode("Tom", 10010));
        list.add(new StudentNode("Time", 10010));
        list.printAll();

        System.out.println(list.find(10001));
        System.out.println(list.find(10010));
        System.out.println();
        list.delete(10003);
        list.delete(10003);
        list.printAll();

        list.add(new StudentNode("SexyBoy", 10030));
        list.printAll();

    }
}

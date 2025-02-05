package ch4_stack_and_queue;/*
    Problem: Check whether the given string is palindrome.
    Point:
        (1). Stack과 Queue의 성질을 이용.
        (2). 앞 / 뒤에서 비교하면서 전진.
        (3). Java에서 Queue는 LinkedList로도 구체화.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class PalindromeProblem {

    public static boolean isPalindrome(String str){
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        // Store into stack and queue
        for(int i=0; i<str.length(); i++){
            stack.add(str.charAt(i));
            queue.add(str.charAt(i));
        }

        // Remove one char coincidentally up/down
        for(int i=0; i<str.length(); i++){
            if(stack.pop() != queue.poll())
                return false;
        }
        return true;

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while(true)
        {
            String str = input.nextLine();
            if(isPalindrome(str))
                System.out.println("Palindrome");
            else
                System.out.println("Not Palindrome");
        }
    }
}

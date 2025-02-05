package ch4_stack_and_queue;/*
    Problem: Check whether the given string is balance
    Condition:
        각 종류에 해당하는 괄호가 열린 순서대로 닫혀야함.
        Example: '{{}}{}', '{{{}}}', '([a])[b]({})'
    Point:
        (1). {, (, [가 있을 경우 Stack에 넣기
        (2). ], ), } 이 올 때, pop과 같은 종류인지 비교한 뒤. 같으면 pop, 아니면 skip
        (3). 최종적으로 stack이 비어있으면 balance
 */


import java.util.Scanner;
import java.util.Stack;

public class BalanceProblem {

    public static boolean isBalanced(String str){
        Stack<Character> stack = new Stack<>();

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) == '[' || str.charAt(i) == '{' || str.charAt(i) == '(')
                stack.add(str.charAt(i));
            if((str.charAt(i) == ']' && stack.peek() == '[') || (str.charAt(i) == '}' && stack.peek() == '{') || (str.charAt(i) == ')' && stack.peek() == '('))
                stack.pop();
            System.out.println(stack);
        }

        return stack.empty();

    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while(true)
        {
            String str = input.nextLine();
            if(isBalanced(str))
                System.out.println("Balanced");
            else
                System.out.println("Not Balanced");
        }
    }
}

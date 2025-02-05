package ch2_complexity_recursive;

public class HanoiProblem {
    /*
    Hanoi Problem
    Point:
        (1). 문제를 작은 문제로 접근한다.
        (2). spare, target, source 구분을 위한 변수를 사용한다.
        (3). 종료 조건을 설정한다.

    Ex) 3개의 탑을 옮기는 문제는
    2개의 탑을 source -> spare로 옮기고
    1개의 탑을 source -> target으로 옮기고
    2개의 탑을 spare -> target으로 옮긴다.
     */

    public static void Hanoi(int n, int a, int b, int c) {
        // a = source location, b = spare location, c = target location

        // Base case
        if (n == 0)
            return;
            // Recursive Case
        else {
            Hanoi(n - 1, a, c, b); // Move Source -> Spare
            System.out.printf("Move %d to %d\n", a, c); // Move Source -> Target
            Hanoi(n - 1, b, a, c); // Move Spare -> Target
        }
    }

    public static void main(String[] args) {
        Hanoi(2, 1, 2, 3);
    }
}

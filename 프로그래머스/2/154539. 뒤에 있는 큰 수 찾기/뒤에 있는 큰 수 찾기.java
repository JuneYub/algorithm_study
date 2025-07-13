import java.util.*;

class Solution {
    public static int n;
    public static int[] result;
    public int[] solution(int[] numbers) {
        n = numbers.length;
        result = new int[n];
        // 스택을 이용하여 주어진 배열의 뒤에서부터 탐색한다.
        // 1. 스택이 비어있으면 -1
        // 2. 스택에서 값을 꺼내 현재 값과 비교한다.
        // 2-1. 현재값이 스택보다 작다면 스택의 값을 계속 꺼내면서 큰 값이 나온다면 배열에 넣는다.
        // 2-2. 현재값이 스택보다 크거나 같다면 스택의 값을 pop한다.
        // 3. 현재 값을 스택에 넣는다.
        Stack<Integer> stack = new Stack<>();
        for(int i = n-1; i >= 0; i--) {
            int curr = numbers[i];

            while(!stack.isEmpty() && stack.peek() <= curr) {
                stack.pop();
            }
            if(stack.isEmpty())
                result[i] = -1;
            else
                result[i] = stack.peek();
            stack.push(curr);
        }
        return result;
    }
}
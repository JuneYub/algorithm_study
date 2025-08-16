import java.util.*;

class Solution {

    static Queue<Long> nums = new ArrayDeque<>();
    static Queue<Character> opers = new ArrayDeque<>();
    static char[] operArr = {'+', '-', '*'};
    static long answer = 0;

    public long solution(String expression) {
        nums.clear();
        opers.clear();
        answer = 0;


        parsingExpression(expression);

        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[3];

        makeOrder(order, visited);
        return answer;
    }

    public static long calcu(List<Integer> order) {
        Queue<Long> orgNums = new ArrayDeque<>(nums);
        Queue<Character> orgOpers = new ArrayDeque<>(opers);
        for(int idx : order) {
            Deque<Long> tmpNums = new ArrayDeque<>();
            Deque<Character> tmpOpers = new ArrayDeque<>();

            tmpNums.add(orgNums.poll());
            while(!orgOpers.isEmpty()) {
                if(orgOpers.peek() == operArr[idx]) {
                    long a = tmpNums.pollLast();
                    long b = orgNums.poll();
                    long c = 계산(a, b, orgOpers.poll());

                    tmpNums.add(c);
                } else {
                    tmpNums.add(orgNums.poll());
                    tmpOpers.add(orgOpers.poll());
                }

            }

            orgNums = tmpNums;
            orgOpers = tmpOpers;
        }
        return Math.abs(orgNums.poll());
    }

    public static long 계산(long a, long b, char c) {
        if(c == '+') return a+b;
        else if(c == '*') return a*b;
        else return (a-b);
    }

    public static void makeOrder(List<Integer> order, boolean[] visited) {
        if(order.size() == 3) {
            long v = calcu(order);
            answer = Math.max(v, answer);
        }

        else {
            for(int i = 0; i < 3; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    order.add(i);
                    makeOrder(order, visited);
                    visited[i] = false;
                    order.remove(order.size()-1);
                }
            }
        }
    }


    public static void parsingExpression(String expression) {
        long num = 0;
        boolean buildingNum = false;
        for(int i = 0; i < expression.length(); i++) {

            char c = expression.charAt(i);
            if(Character.isDigit(c)) {
                num = (num * 10) + (c-'0');
                buildingNum = true;
            } else {
                nums.add(num);
                buildingNum = false;
                opers.add(c);
                num = 0;
            }
        }
        nums.add(num);
    }
}
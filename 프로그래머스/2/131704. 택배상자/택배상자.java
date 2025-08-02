import java.util.*;

class Solution {
    public int solution(int[] order) {
        //order = new int[]{5, 8, 7, 4, 1, 4, 3,2, 6};
        Stack<Integer> stack = new Stack<>();
        int answer = 0;
        int cur = 1; // 1~N

        for(int i = 0; i < order.length; i++) {
            int orderNum = order[i];
            //System.out.println(orderNum + " 현재 순서");
            while(cur < orderNum) {
                stack.add(cur);
                //System.out.println(cur + " 스택에 추가");
                cur++;
            }
            
            if(cur == orderNum) {
                //System.out.println(cur + " 동일하므로 정답 ++");
                cur++;
                answer++;
                continue;
            }
            
            if(cur > orderNum) {
                
                while(!stack.isEmpty()) {
                    int top = stack.peek();
                    //System.out.println(top +  " " + orderNum);
                    if(top == orderNum) {
                        stack.pop();
                        answer++;
                        //cur++;
                        break;
                    }
                    else if(top > orderNum) {
                        return answer;
                    }
                }
            }
            
        }
        
        
        return answer;
    }
}
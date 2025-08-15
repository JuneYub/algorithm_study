import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        
        long totalSum = 0;
        long leftTotal = 0; // 투포인터에서 초기값
        int[] queue = new int[queue1.length + queue2.length];
        
        for(int i = 0; i < queue1.length; i++) {
            totalSum += queue1[i];
            leftTotal += queue1[i];
            totalSum += queue2[i];
            
            queue[i] = queue1[i];
        }
        
        // 합이 홀수이면 불가능
        if(totalSum % 2 == 1) return -1;
        
        for(int i = 0; i < queue2.length; i++) {
            //System.out.println(queue1.length + i);
            queue[queue1.length + i] = queue2[i];
        }
        
        // for(int i = 0; i < queue.length; i++) {
        //     System.out.print(queue[i] + " ");
        // }
        
        int p1 = 0;
        int p2 = queue1.length - 1;
        
        int answer = 0;
        while(leftTotal != (totalSum / 2)) {
            

            if(leftTotal < (totalSum / 2)) {
                p2++;
                if(p2 >= queue.length) break;
                leftTotal += queue[p2];
            } else if(leftTotal > (totalSum / 2)) {
                leftTotal -= queue[p1];
                p1++;
            }

            answer++;
            if(leftTotal == (totalSum / 2)) break;
        }
        
        if(p2 >= queue.length) return -1;
    
        return answer;
    }
}
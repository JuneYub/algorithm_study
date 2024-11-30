import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        
        int sum = 0;
        int startIdx = 0;
        int endIdx = -1;
        
        int length = Integer.MAX_VALUE;
        int ansStart = 0;
        int ansEnd = 0;
        
        while(endIdx < sequence.length) {
            
            if(sum == k) {
                //System.out.println("sum == k 현재 end " + endIdx);
                if(length > endIdx - startIdx + 1) {
                    length = endIdx - startIdx + 1;
                    ansStart = startIdx;
                    ansEnd = endIdx;
                    
                }
                
                endIdx++;
                if(endIdx < sequence.length) {
                    sum += sequence[endIdx];
                }
            }
            
            if(sum < k) {
                
                endIdx++;
                //System.out.println("sum < k 현재 end " + endIdx);
                if(endIdx < sequence.length) {
                    sum += sequence[endIdx];
                }
            }
            
            if(sum > k) {
                //System.out.println("sum > k 현재 start " + startIdx);
                sum -= sequence[startIdx];
                startIdx++;
                if(startIdx > endIdx) break;
            }
        }
        
        answer[0] = ansStart;
        answer[1] = ansEnd;
        
        return answer;
    }
}

    
    
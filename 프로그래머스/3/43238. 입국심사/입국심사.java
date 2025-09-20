import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);
        
        long left = 0;
        long right = times[times.length-1]*(long)n;
        
        long answer = 0;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            long res = countProcessedPeople(times, mid);
            if(res >= n) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }
        
        return answer;
        
    }
    
    static public long countProcessedPeople(int[] times, long mid) {
        long totalN = 0;
        for(int time : times) {
            totalN += (mid / time);
        }
        return totalN;
    }
}
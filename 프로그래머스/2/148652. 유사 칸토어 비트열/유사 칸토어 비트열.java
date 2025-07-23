import java.util.*;

class Solution {
    public int solution(int n, long l, long r) {
        
        int answer = 0;
        
        for(long i = l-1; i < r; i++) {
            if(!isOne(n, i)) answer++;
        }
        
        return answer;
    }
    
    public boolean isOne(int n, long i) {
        
        if(n == 0) return false;
        
        long sectionLength = (long) Math.pow(5, n-1);
        long index = i / sectionLength;
        
        if(index == 2) return true;
        
        return isOne(n-1, i % sectionLength);
    }
}
import java.util.*;

class Solution {
    int num;
    public int solution(int storey) {
        num = storey;
        int answer = 0;
        
        
        while(num != 0) {
            
            int n = num % 10;
            if(n == 0) {
                
            }
            else if(n < 5) {
                answer += n;
                num -= n;
            }
            else if(n > 5) {
                answer += (10-n);
                num += (10-n);
            }
            else {
                if(num / 10 % 10 >= 5) {
                    answer += (10-n);
                    num += (10-n);
                } else {
                    answer += n;
                    num -= n;                    
                }
            }
            
            
            num /= 10;
        }
        
        
        return answer;
    }
}
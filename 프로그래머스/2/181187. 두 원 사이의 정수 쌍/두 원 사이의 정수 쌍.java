import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        
        long result = 0;
        
        for(int x = 1; x <= r2; x++) {
            int maxY = (int)Math.floor(Math.sqrt((long)r2*r2 - (long)x*x));
            //System.out.println(maxY);
            int minY = 0;
            if (x < r1) {
                minY = (int) Math.ceil(Math.sqrt((long)r1 * r1 - (long)x * x));
                //System.out.println("minY " + minY);
            }
            result += (maxY - minY + 1);
            
        }
        
        return result*4;
    }
}
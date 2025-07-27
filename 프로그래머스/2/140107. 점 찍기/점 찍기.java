import java.util.*;

class Solution {
    public long solution(int k, int d) {
        
        long result = 0;
        for(int x = 0; x <= d; x++) {
            // x일때 y의 최대값 구하기
            if(x % k == 0) {
                // x=0, y맥스 4, x=1, y맥스 3, x=2, y맥스 2, x=4, y맥스 0
                int maxY = (int) Math.sqrt((long)d*d - (long)x*x);
                //System.out.println(x + " " +maxY);
                result += (long) ((maxY / k)+1);     
                //System.out.println(x+ " "+ maxY + " " + result + " " + ((maxY / k)+1));
            }

        }
        return result;
        
    }
}
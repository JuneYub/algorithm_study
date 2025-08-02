import java.util.*;

class Solution {
    public static int n;
    public int solution(int[] elements) {
        n = elements.length;
        
        Set<Long> set = new HashSet<>();

        // idx 4인 경우 4+0, 4+1, 4+2, 4+3 , 4+4 = 4
        for(int idx = 0; idx < n; idx++) {

            // i는 부분 수열의 길이
            // 0, 0+1, 0+2, 0+3
            for(int i = 0; i < n; i++) {
                Long sum = 0L;
                for(int j = idx; j <= (idx+i); j++) {
                    int cur = (j)%n;
                    sum += elements[cur];
                }
                
                set.add(sum);
                //System.out.println(sum);
            }
            //System.out.println("============");

        }       
        
        return set.size();
    }
}
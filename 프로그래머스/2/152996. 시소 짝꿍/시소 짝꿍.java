import java.util.*;

class Solution {
    public static long answer = 0;
    public static Map<Double, Integer> map = new HashMap<>();
    public long solution(int[] weights) {
        
        // 100 100 180 270 360
        Arrays.sort(weights);
        for(int w : weights) {
            Double lw =  w + 0.0;
            
            // 같거나, 2/3, 2/4, 3/4
            Double a = lw*2/3;
            Double b = lw*2/4;
            Double c = lw*3/4;
            
            if(map.get(lw) != null) answer+=map.get(lw);
            if(map.get(a) != null) answer+=map.get(a);
            if(map.get(b) != null) answer+=map.get(b);
            if(map.get(c) != null) answer+=map.get(c);
            
            if(map.get(lw) == null) map.put(lw, 1);
            else
                map.put(lw, map.get(lw)+1);
        }
        
        return answer;
    }
}
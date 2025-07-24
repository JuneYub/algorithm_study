import java.util.*;

class Solution {
    public int solution(int vn, int vk, int[] enemy) {
        
        int n = vn;
        int k = vk;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
        
        for(int i = 0; i < enemy.length; i++) {
            pq.add(enemy[i]);
            
            if(n < enemy[i]) {
                if(k == 0) return (i);
                k--;
                n += pq.poll();
                
                n -= enemy[i];
            } else {
                n -= enemy[i];
            }
            
            
        }
        
        return enemy.length;
    }
}
import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> q = new PriorityQueue<>((a, b) -> b-a);
        
        for(int i = 0; i < tangerine.length; i++) {
            int key = tangerine[i];
            if(map.get(key) == null) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
        
        int remainK = k;
        int answer = 0;
        
        for(int key : map.keySet()) {
            q.add(map.get(key));
        }
        
        while(!q.isEmpty()) {
            int num = q.poll();
            
            answer++;
            remainK -= num;

            if(remainK <= 0) return answer;
        }
        
        for(int key : map.keySet()) {

        }
        return answer;
    }
}
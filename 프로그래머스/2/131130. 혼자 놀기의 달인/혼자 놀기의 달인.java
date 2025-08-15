import java.util.*;

class Solution {
    
    public static int[] cards;
    public int solution(int[] cardsSample) {
        int n = cardsSample.length;
        cards = cardsSample;
        boolean[] visited = new boolean[n];
        List<Integer> groupSizes = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                int size = dfs(visited, i);
                groupSizes.add(size);
            }
        }
        
        if(groupSizes.size() < 2) return 0;
        
        groupSizes.sort(Collections.reverseOrder());
        
        return groupSizes.get(0) * groupSizes.get(1);
        
        
    }
    
    public static int dfs(boolean[] visited, int idx) {
        
        if(visited[idx]) return 0;
        visited[idx] = true;
        return 1+dfs(visited, cards[idx] - 1);
        
    }
        
        
}
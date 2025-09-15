import java.util.*;
class Solution {
    public static int answer = 0;
    public static int[] visited;
    public static int n;
    public static int[][] computers;
    public int solution(int an, int[][] acomputers) {

        n = an;
        visited = new int[n];
        computers = acomputers;
        
        Arrays.fill(visited, -1);
        
        for(int y = 0; y < n; y++) {
            if(visited[y] != -1) continue;
            dfs(y);
            answer++;
        }
        
        return answer;
    }
    
    public static void dfs(int node) {
        
        visited[node] = answer;
        for(int i = 0; i < n; i++) {
            if(node == i) continue;
            if(visited[i] != -1) continue;
            if(computers[node][i] != 1) continue;
            dfs(i);
        }
        
    }
}
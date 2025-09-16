import java.util.*;

class Solution {
    
    public static List<String> ans = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        
        boolean[] visited = new boolean[tickets.length];
        
        dfs("ICN", "ICN", visited, 0, tickets);
        
        Collections.sort(ans);
        
        return ans.get(0).split(" ");
    }
    
    public static void dfs(String cur, String path, boolean[] visited, int depth, String[][] tickets) {
        if(depth == tickets.length) {
            ans.add(path);
            return;
        }
        
        for(int i = 0; i < visited.length; i++) {
            if(!visited[i] && cur.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(tickets[i][1], path+" "+tickets[i][1], visited, depth+1, tickets);
                visited[i] = false;
            }
        }
    }
}
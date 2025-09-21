import java.util.*;



class Solution {
    
    static class Position {
        int num;
        int cnt;
        
        Position(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
    
    public static List<List<Integer>> graph = new ArrayList<>();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < roads.length; i++) {
            int start = roads[i][0];
            int end = roads[i][1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }
         
        boolean[] visited = new boolean[n+1];
        int[] answer = bfs(n, destination, visited);
        
        int[] res = new int[sources.length];
        for(int i = 0; i < sources.length; i++) {
            res[i] = answer[sources[i]];
            if(res[i] == Integer.MAX_VALUE) res[i] = -1;
        }
        
        return res;
    }
    
    public static int[] bfs(int n, int tgt, boolean[] visited) {
        
        Queue<Position> q = new ArrayDeque<>();
        
        q.add(new Position(tgt, 0));
        visited[tgt] = true;

        int[] ans = new int[n+1]; 
        Arrays.fill(ans, Integer.MAX_VALUE);
        
        while(!q.isEmpty()) {
            Position cur = q.poll();
            ans[cur.num] = Math.min(cur.cnt, ans[cur.num]);
            
            for(int y = 0; y < graph.get(cur.num).size(); y++) {
                int node = graph.get(cur.num).get(y);
                if(visited[ node ]) continue;
                
                visited[ node ] = true;
                q.add(new Position(node, cur.cnt + 1));
                
            }
        }
        
        return ans;
    }
}
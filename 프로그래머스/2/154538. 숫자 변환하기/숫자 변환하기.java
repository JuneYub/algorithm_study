import java.util.*;

class Position {
    int num, cnt;
    
    public Position(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }
}

class Solution {
    public static int x, y, n;
    public static int[] visited;
    public static int result = Integer.MAX_VALUE;
    public static int maxLen = 1_000_000;
    public int solution(int x, int y, int n) {
        x = x;
        y = y;
        n = n;
        visited = new int[1_000_001];
        for(int i = 0; i <= maxLen; i++) {
            visited[i] = Integer.MAX_VALUE;
        }
        
        Queue<Position> q = new ArrayDeque<>();
        
        q.add(new Position(x, 0));
        
        while(!q.isEmpty()) {
            Position p = q.poll();
            
            if(p.num == y && result > p.cnt) {
                result = p.cnt;
                continue;
            }
            
            int x1 = p.num + n;
            int x2 = p.num * 2;
            int x3 = p.num * 3;
            
            if(x1 <= maxLen && visited[x1] >= p.cnt+1) {
                q.add(new Position(x1, p.cnt+1));
                visited[x1] = p.cnt+1;
            }
            
            if(x2 <= maxLen && visited[x2] >= p.cnt+1) {
                q.add(new Position(x2, p.cnt+1));
                visited[x2] = p.cnt+1;
            }
            
            if(x3 <= maxLen && visited[x3] >= p.cnt+1) {
                q.add(new Position(x3, p.cnt+1));
                visited[x3] = p.cnt+1;
            }            
        }
        if(result == Integer.MAX_VALUE) return -1;
        return result;
    }
}
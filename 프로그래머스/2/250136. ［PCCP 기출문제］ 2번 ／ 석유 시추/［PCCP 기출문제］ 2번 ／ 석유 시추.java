import java.util.*;

class Position {
    int x;
    int y;
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    static int[][] map;
    static boolean[][] visited;
    static int maxY, maxX;
    static Map<Integer, Integer> countMap = new HashMap<>();
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    
    static int oilId = 1;
    
    public int solution(int[][] land) {
        
        maxY = land.length;
        maxX = land[0].length;
        
        map = new int[maxY][maxX];
        visited = new boolean[maxY][maxX];
            
        for(int y = 0; y < maxY; y++) {
            for(int x = 0; x < maxX; x++) {
                if(land[y][x] == 1 && !visited[y][x]) {
                    int cnt = bfs(x, y, land);
                    countMap.put(oilId++, cnt);
                }
            }
        }
        
        return findAns();
    }
    
    public static int findAns() {
        
        int maxOilSum = 0;
        for(int x = 0; x < maxX; x++) {
            
            int oilSum = 0;
            boolean[] oilVisited = new boolean[oilId-1];
            for(int y = 0; y < maxY; y++) {
                
                if(map[y][x] != 0 && !oilVisited[map[y][x] -1]) {
                    oilSum += countMap.get(map[y][x]);
                    oilVisited[map[y][x] - 1] = true;
                }
            }
            
            maxOilSum = Math.max(oilSum, maxOilSum);
        }
        return maxOilSum;
    }
    
    public static int bfs(int x, int y, int[][] land) {
        int cnt = 1;
        
        Queue<Position> q = new ArrayDeque<>();
        visited[y][x] = true;
        map[y][x] = oilId;
        q.add(new Position(x, y));
        
        while(!q.isEmpty()) {
            Position p = q.poll();
            
            for(int i = 0; i < 4; i++) {
                int newX = dx[i] + p.x;
                int newY = dy[i] + p.y;
                
                if(!isRange(newX, newY)) continue;
                if(land[newY][newX] == 0) continue;
                if(visited[newY][newX]) continue;     
                
                visited[newY][newX] = true;
                map[newY][newX] = oilId;
                q.add(new Position(newX, newY));
                cnt++;
                
            }
        }
        return cnt;
    }
    
    public static boolean isRange(int x, int y) {
        if(x < 0 || x >= maxX || y < 0 || y >= maxY) return false;
        return true;
    }
}
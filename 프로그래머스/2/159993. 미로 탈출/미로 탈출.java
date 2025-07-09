import java.util.*;


class Position {
    int x,y;
    int cnt;
    public Position(int y, int x, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

class Solution {
    
    static char[][] map;
    static int xLen = 0;
    static int yLen = 0;
    
    static int sY = 0;
    static int sX = 0;
    
    static int lY = 0;
    static int lX = 0;
    
    static int eY = 0;
    static int eX = 0;
    
    static boolean findLever = false;
    static boolean findExit = false;
    
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    
    public int solution(String[] maps) {
        
        yLen = maps.length;
        xLen = maps[0].length();
        map = new char[yLen][xLen];
        
        for(int y = 0; y < maps.length; y++) {
            for(int x = 0; x < maps[y].length(); x++) {
                map[y][x] = maps[y].charAt(x);
                if(map[y][x] == 'L') {
                    lY = y;
                    lX = x;
                }
                else if(map[y][x] == 'S') {
                    sY = y;
                    sX = x;
                }
                else if(map[y][x] == 'E') {
                    eY = y;
                    eX = x;
                }
            }
        }
        int result = 0;
        result += findLever();
        if(!findLever) return -1;
        result += findExit();
        if(!findExit) return -1;
        return result;
    }
    
    public static int findLever() {
        visited = new boolean[yLen][xLen];
        
        Queue<Position> q = new ArrayDeque<>();
        
        q.add(new Position(sY, sX, 0) );
        visited[sY][sX] = true;
        
        while(!q.isEmpty()) {
            Position pos = q.poll();
            
            if(pos.y == lY && pos.x == lX) {
                findLever = true;
                return pos.cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int newX = pos.x + dx[i];
                int newY = pos.y + dy[i];
                if(!isRange(newY, newX)) continue;
                if(map[newY][newX] == 'X') continue;
                if(visited[newY][newX]) continue;
                
                visited[newY][newX] = true;
                q.add(new Position(newY, newX, pos.cnt + 1));
                
            }
        }
        
        return 10000;
        
    }
    
    public static int findExit() {
        visited = new boolean[yLen][xLen];
        
        Queue<Position> q = new ArrayDeque<>();
        
        q.add(new Position(lY, lX, 0) );
        visited[lY][lX] = true;
        
        while(!q.isEmpty()) {
            Position pos = q.poll();
            
            if(pos.y == eY && pos.x == eX) {
                findExit = true;
                return pos.cnt;
            }
            
            for(int i = 0; i < 4; i++) {
                int newX = pos.x + dx[i];
                int newY = pos.y + dy[i];
                if(!isRange(newY, newX)) continue;
                if(map[newY][newX] == 'X') continue;
                if(visited[newY][newX]) continue;
                
                visited[newY][newX] = true;
                q.add(new Position(newY, newX, pos.cnt + 1));
                
            }
        }
        
        return 10000;
        
    }
    
    public static boolean isRange(int y, int x) {
        if(y < 0 || y >= yLen || x < 0 || x >= xLen) return false;
        return true;
    }
}
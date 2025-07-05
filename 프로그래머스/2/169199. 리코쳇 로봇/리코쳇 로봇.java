import java.util.*;

class Position {
    int x, y;
    int cnt;
    
    public Position(int y, int x, int cnt){
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

class Solution {
    
    public int[] dx = {0, 1, 0, -1};
    public int[] dy = {-1, 0, 1, 0};
    public char[][] map;
    public int lengthX = 0;
    public int lengthY = 0;
    public boolean[][] visited;
    public int result = Integer.MAX_VALUE;
    
    public int solution(String[] board) {
        
        lengthX = board[0].length();
        lengthY = board.length;
        visited = new boolean[lengthY][lengthX];
        
        Queue<Position> queue = new ArrayDeque<Position>();
        
        map = new char[lengthY][lengthX];
        
        for(int y = 0 ; y < lengthY; y++) {
            for(int x = 0 ; x < lengthX; x++) {
                map[y][x] = board[y].charAt(x);
                if(map[y][x] == 'R') {
                    queue.add(new Position(y, x, 0));
                    visited[y][x] = true;
                }
            }
        }
        
        
        while(!queue.isEmpty()) {
            Position p = queue.poll();
            
            // G인 경우 도착
            if(map[p.y][p.x] == 'G') {
                result = Math.min(p.cnt, result);
                continue;
            }
            
            for(int i = 0; i < 4; i++) {
                
                int nowX = p.x;
                int nowY = p.y;
                
                while(true) {
                    int nx = nowX + dx[i];
                    int ny = nowY + dy[i];
                    if(!isRange(ny,nx) || map[ny][nx] == 'D') break;
                    nowX = nx;
                    nowY = ny;
                }
                
                if(visited[nowY][nowX]) continue;
                visited[nowY][nowX] = true;
                queue.add(new Position(nowY, nowX, p.cnt+1));
                
                
            }
        }
        
        if(result == Integer.MAX_VALUE) return -1;
        return result;
    }

    public boolean isRange(int y, int x) {
        if(y < 0 || y>= lengthY || x < 0 || x>=lengthX) return false;
        return true;
    }

    
}
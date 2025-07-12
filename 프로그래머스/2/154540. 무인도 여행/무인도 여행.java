import java.util.*;

class Position {
    int x, y;
    
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    public static char[][] map;
    public static boolean[][] visited;
    public static int xlen;
    public static int ylen;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};
    public static List<Integer> result = new ArrayList<>();
    
    
    public int[] solution(String[] maps) {
        
        ylen = maps.length;
        xlen = maps[0].length();
        map = new char[ylen][xlen];
        visited = new boolean[ylen][xlen];
        
        for(int y = 0; y < ylen; y++) {
            for (int x = 0; x < xlen; x++) {
                map[y][x] = maps[y].charAt(x);
            }
        }    
        
        for(int y = 0; y  < ylen; y++) {
            for(int x = 0; x < xlen; x++) {
                if(map[y][x] != 'X' && !visited[y][x]) {
                    
                    search(y, x);
                }
            }
        }
        
        if(result.size() == 0) {
            int[] answer = {-1};
            return answer;
        }
        
        Collections.sort(result);
        
        int[] answer = result.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
    
    public static void search(int y, int x) {
        
        int sum = 0;
        sum += map[y][x] - '0';
        visited[y][x] = true;
        //System.out.println(map[y][x]);
        Queue<Position>  q = new ArrayDeque<>();
        q.add(new Position(x, y));
        
        while(!q.isEmpty()) {
            Position p = q.poll();
            for(int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];
                
                if(isRange(newY, newX) && map[newY][newX] != 'X' &&!visited[newY][newX]) {
                    q.add(new Position(newX, newY));
                    visited[newY][newX] = true;
                    sum += map[newY][newX]- '0';
                }
            }
        }
        
        result.add(sum);
        
    }
    
    public static boolean isRange(int y, int x) {
        if(y < 0 || y >= ylen || x < 0 || x >= xlen) return false;
        return true;
    }
}
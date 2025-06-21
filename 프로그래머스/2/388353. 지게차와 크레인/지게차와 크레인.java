import java.util.*;
import java.io.*;

class Position {
    int x, y;
    
    Position(int x, int y) { 
        this.x = x;
        this.y = y;
    }
}

class Solution {
    
    public static int n, m;
    public static int[][] map;
    public static int[] dx = {0, 1, 0 ,-1};
    public static int[] dy = {-1, 0, 1, 0};
    
    public int solution(String[] storage, String[] requests) {
        
        n = storage.length;
        m = storage[0].length();
        
        map = new int[n][m];
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                map[i][j] = storage[i].charAt(j) - 'A';
            }
        }
        
        // 요청 수행
        for(int i = 0; i < requests.length; i++) {
            if(requests[i].length() == 1) {
                forkLift(requests[i].charAt(0) - 'A');
            } else {
                crain(requests[i].charAt(0) - 'A');
            }       
        }
        
        int result = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] != -1) result++;
            }
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == -1) {
                    System.out.print("  ");
                }
                else {
                    System.out.print((char)(map[i][j] + 'A') + " ");
                }
            }
            System.out.println();
        }
        
        
        return result;

        
        
    }

    public static void forkLift(int order) {
        List<Position> list = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                
                if(map[i][j] == order) {
                    
                    for(int d = 0; d < 4; d++) {
                        int newY = i + dy[d];
                        int newX = j + dx[d];

                        if(!isRange(newX, newY)) {
                            list.add(new Position(j, i));
                            break;
                        }
                           
                        if(map[newY][newX] == -1) {
                            if(isOutLinked(newX, newY)) {
                                list.add(new Position(j, i));
                                break;
                            }                            
                        }
                    }
                }
                
            }
        }
        
        for(Position pos : list) {
            map[pos.y][pos.x] = -1;
        }
    }
    
    public static void crain(int order) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == order) {
                    map[i][j] = -1;   
                }
            }
        }
    }
    
    public static boolean isOutLinked(int x, int y) {
        Queue<Position> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        
        q.add(new Position(x, y));
        visited[y][x] = true;
        
        while(!q.isEmpty()) {
            Position pos = q.poll();
            for(int i = 0; i < 4; i++) {
                int newX = pos.x+dx[i];
                int newY = pos.y+dy[i];
                
                // 바깥과 이어진 경우 true 바로 반환
                if(!isRange(newX, newY)) return true;
                
                // 그렇지 않은 경우, 비어있는 칸만 다시 추가
                if(map[newY][newX] == -1 && !visited[newY][newX]) {
                    q.add(new Position(newX, newY));
                    visited[newY][newX]  = true;
                }
            }
        }
        return false;
    }
    
    
    public static boolean isRange(int x, int y) {
        if(y < 0 || y >= n || x < 0 || x >= m) return false;
        return true;
    }
    
}
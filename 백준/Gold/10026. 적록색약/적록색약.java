import java.util.*;
import java.lang.*;
import java.io.*;

class Position {
    int x;
    int y;

    Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Main {

    static int n;
    static char[][] map;
    static boolean[][] visited;
    static int cnt, cnt2;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for(int y = 0; y < n; y++) {
            String str = br.readLine();
            for(int x = 0; x < n; x++) {
                map[y][x] = str.charAt(x);
            }
        }

        visited = new boolean[n][n];

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                if(!visited[y][x]) {
                    cnt++;
                    bfs(y, x);
                }
            }
        }

        // 초록색을 모두 빨간색으로 칠하기
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                if(map[y][x] == 'G') {
                    map[y][x] = 'R';
                }
            }
        }

        visited = new boolean[n][n];
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < n; x++) {
                if(!visited[y][x]) {
                    cnt2++;
                    bfs(y, x);
                }
            }
        }

        System.out.println(cnt + " " + cnt2);
        
    } // end of main

    public static void bfs(int y, int x) {
        Queue<Position> q = new ArrayDeque<>();
        q.add(new Position(y, x));
        char color = map[y][x];

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        while(!q.isEmpty()) {
            Position p = q.poll();

            for(int i = 0; i < 4; i++) {
                int newX = dx[i] + p.x;
                int newY = dy[i] + p.y;

                if(isRange(newY, newX) && !visited[newY][newX] && map[newY][newX] == color) {
                    visited[newY][newX] = true;
                    q.add(new Position(newY, newX));
                }
            }
        }
        
    }
    
    public static boolean isRange(int y, int x) {
        if(y < 0 || y >= n || x < 0 || x >= n) {
            return false;
        }
        return true;
    }
}
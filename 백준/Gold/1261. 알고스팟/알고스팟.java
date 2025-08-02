import java.util.*;
import java.lang.*;
import java.io.*;

class Position implements Comparable<Position> {
    int y, x;
    int cost;
    public Position(int y, int x, int cost) {
        this.y = y;
        this.x = x;
        this.cost = cost;
    }

    public int compareTo(Position p) {
        return this.cost - p.cost;
    }
}

class Main {

    public static int n, m;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};
    public static int[][] map;
    public static boolean[][] visited;
    
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];
        for(int y = 0; y < n; y++) {
            String str = br.readLine();
            for(int x = 0; x < m; x++) {
                map[y][x] = str.charAt(x) - '0';
            }
        }
        PriorityQueue<Position> pq = new PriorityQueue<>();
        pq.add(new Position(0, 0, 0));
        visited[0][0] = true;
        
        while(!pq.isEmpty()) {
            Position p = pq.poll();

            if(p.y == n-1 && p.x == m-1) {
                System.out.print(p.cost);
                break;
            }

            for(int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if(isRange(newY, newX) && !visited[newY][newX]) {
                    pq.add(new Position(newY, newX, p.cost + map[newY][newX]));
                    visited[newY][newX] = true;
                }
            }
        }
        
    }

    public static boolean isRange(int y, int x) {
        if(y < 0 || y >= n || x < 0 || x >= m) return false;
        return true;
    }
}
import java.util.*;
import java.io.*;

class Position implements Comparable<Position>{
    int x, y, cnt;
    Position(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }

    public int compareTo(Position p) {
        return this.cnt - p.cnt;
    }

}
public class Main
{

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int[] dirX = {0, 1, 0, -1};
    static int[] dirY = {-1, 0, 1 ,0};
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];


        for(int y = 0; y < n ; y++) {
            String str = br.readLine();
            for(int x = 0; x < n; x++) {
                map[y][x] = str.charAt(x) - '0';
            }
        }

        PriorityQueue<Position> q = new PriorityQueue<>();
        q.add(new Position(0,0, 0));

        while(!q.isEmpty()) {
            Position p = q.poll();
            if(p.x == n-1 && p.y == n-1) {
                System.out.println(p.cnt);
                break;
            }

            for(int i = 0; i < 4; i++) {
                int newX = p.x + dirX[i];
                int newY = p.y + dirY[i];

                if(isRange(newX, newY) && !visited[newY][newX]) {
                    visited[newY][newX] = true;

                    if(map[newY][newX] == 1) {
                        q.add(new Position(newX, newY, p.cnt));
                    }
                    else {
                        q.add(new Position(newX, newY, p.cnt+1));
                    }

                }
            }

        }

    }

    public static boolean isRange(int x, int y ) {
        if(x < 0 || x >= n || y < 0 || y >= n) {
            return false;
        }
        return true;
    }

}

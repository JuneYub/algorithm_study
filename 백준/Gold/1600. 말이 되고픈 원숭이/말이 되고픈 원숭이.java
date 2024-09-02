import java.io.*;
import java.util.*;

class Position implements Comparable<Position> {
    int x, y, cnt, k;

    Position(int x, int y, int cnt, int k) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.k = k;
    }

    public int compareTo(Position p) {
        return this.cnt - p.cnt;
    }
}

public class Main {


    static int k, w, h;
    static int[][] map;
    static int[][][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int[] hx = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};

    static Queue<Position> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        map = new int[h][w];
        visited = new int[h][w][k+1];

        for(int y = 0; y < h; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x< w; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        if(map[h-1][w-1] == 1) {
            System.out.println(-1);
            System.exit(0);
        }
        map[h-1][w-1] = Integer.MAX_VALUE;

        // 최대값으로 다 채움
        for(int y = 0; y < h; y++) {
            for(int x = 0; x< w; x++) {
                Arrays.fill(visited[y][x], Integer.MAX_VALUE);
            }
        }

        // 지도에다가 현재 지점에 방문했다면 몇번째로 방문했는지 기록하자
        // 그렇게 되면 내가 다음 지점에 기록한 점보다 크다면 방문하지 않으면 된다.
        q.add(new Position(0, 0, 0, k));
        visited[0][0][k] = 0;

        while(!q.isEmpty()) {

            Position p = q.poll();
            // 목적지 도착
            if(p.x == w-1 && p.y == h-1) {

                map[p.y][p.x] = Math.min(map[p.y][p.x], p.cnt);
                continue;
            }

            // 4방탐색
            for(int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if(!isRange(newX, newY)) continue;
                if(map[newY][newX] == 1) continue;

                if(visited[newY][newX][p.k] > p.cnt) {
                    visited[newY][newX][p.k] = p.cnt;
                    q.add(new Position(newX, newY, p.cnt+1, p.k));
                }
            }

            // 말 8방 탐색
            if(p.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int newX = p.x + hx[i];
                    int newY = p.y + hy[i];

                    if(!isRange(newX, newY)) continue;
                    if(map[newY][newX] == 1) continue;

                    if(visited[newY][newX][p.k-1] > p.cnt) {
                        visited[newY][newX][p.k-1] = p.cnt;
                        q.add(new Position(newX, newY, p.cnt+1, p.k-1));
                    }
                }
            }
        }

        if(map[h-1][w-1] == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(map[h-1][w-1]);
        }

    }

    static boolean isRange(int x, int y) {
        if(x < 0 || x >= w || y < 0 || y >= h) return false;
        return true;
    }
}
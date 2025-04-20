import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static class Coin {
        int x1, y1, x2, y2, move;

        Coin(int x1, int y1, int x2, int y2, int move) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.move = move;
        }
    }
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int[] coins = new int[4];
        int idx = 0;

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if(board[i][j] == 'o') {
                    coins[idx++] = i;
                    coins[idx++] = j;
                }
            }
        }

        System.out.println(bfs(coins[0], coins[1], coins[2], coins[3]));
        
    }

    static int bfs(int x1, int y1, int x2, int y2) {
        Queue<Coin> q = new ArrayDeque<>();
        q.add(new Coin(x1, y1, x2, y2, 0));
        visited[x1][y1][x2][y2] = true;

        while(!q.isEmpty()) {
            Coin now = q.poll();

            if(now.move >= 10) continue;

            for(int d = 0; d < 4; d++) {
                int nx1 = now.x1 + dx[d];
                int ny1 = now.y1 + dy[d];
                int nx2 = now.x2 + dx[d];
                int ny2 = now.y2 + dy[d];

                boolean out1 = isRange(nx1, ny1);
                boolean out2 = isRange(nx2, ny2);

                // 둘 다 떨어지면 안됨
                if (out1 && out2) continue;

                // 하나만 떨어졌으면 성공
                if (out1 || out2) return now.move + 1;

                // 벽이면 제자리
                if (!out1 && board[nx1][ny1] == '#') {
                    nx1 = now.x1;
                    ny1 = now.y1;
                }
                if (!out2 && board[nx2][ny2] == '#') {
                    nx2 = now.x2;
                    ny2 = now.y2;
                }

                // 방문하지 않은 상태만 큐에 추가
                if (!visited[nx1][ny1][nx2][ny2]) {
                    visited[nx1][ny1][nx2][ny2] = true;
                    q.add(new Coin(nx1, ny1, nx2, ny2, now.move + 1));
                }
            }
        }
        return -1;
    }
    
    static boolean isRange(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}
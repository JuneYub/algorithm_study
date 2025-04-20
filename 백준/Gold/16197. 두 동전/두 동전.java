import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static boolean[][][][] visited;
    static int[] dx = {-1, 1, 0, 0}; // 상 하 좌 우
    static int[] dy = {0, 0, -1, 1};

    static class Coin {
        int x1, y1, x2, y2, cnt;
        Coin(int x1, int y1, int x2, int y2, int cnt) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        int x1 = -1, y1 = -1, x2 = -1, y2 = -1;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'o') {
                    if (x1 == -1) {
                        x1 = i;
                        y1 = j;
                    } else {
                        x2 = i;
                        y2 = j;
                    }
                    board[i][j] = '.'; // 꼭 동전 자리 비워줘야 함
                }
            }
        }

        System.out.println(bfs(x1, y1, x2, y2));
    }

    static int bfs(int x1, int y1, int x2, int y2) {
        Queue<Coin> q = new LinkedList<>();
        q.offer(new Coin(x1, y1, x2, y2, 0));
        visited[x1][y1][x2][y2] = true;

        while (!q.isEmpty()) {
            Coin now = q.poll();

            if (now.cnt >= 10) return -1;

            for (int d = 0; d < 4; d++) {
                int nx1 = now.x1 + dx[d];
                int ny1 = now.y1 + dy[d];
                int nx2 = now.x2 + dx[d];
                int ny2 = now.y2 + dy[d];

                boolean out1 = isOut(nx1, ny1);
                boolean out2 = isOut(nx2, ny2);

                // 둘 다 떨어지면 안 됨
                if (out1 && out2) continue;

                // 하나만 떨어지면 성공
                if (out1 || out2) return now.cnt + 1;

                // 벽이면 이동하지 않음
                if (!out1 && board[nx1][ny1] == '#') {
                    nx1 = now.x1;
                    ny1 = now.y1;
                }
                if (!out2 && board[nx2][ny2] == '#') {
                    nx2 = now.x2;
                    ny2 = now.y2;
                }

                // 이미 방문한 상태면 스킵
                if (visited[nx1][ny1][nx2][ny2]) continue;

                visited[nx1][ny1][nx2][ny2] = true;
                q.offer(new Coin(nx1, ny1, nx2, ny2, now.cnt + 1));
            }
        }

        return -1;
    }

    static boolean isOut(int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= M;
    }
}

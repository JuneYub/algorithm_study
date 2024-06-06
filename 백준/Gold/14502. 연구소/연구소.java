import java.util.*;
import java.io.*;

class Position {
    int x,y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main
{


    static int n, m;
    static int[][] map;
    static int[][] copyMap;
    static boolean [][][][][][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int maxResult = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        copyMap = new int[n][m];
        visited = new boolean[n][m][n][m][n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeWall(new ArrayList<Position>(), new Position(0, 0), 0);
        System.out.println(maxResult);
    }

    public static void makeWall(List<Position> list, Position p, int idx) {
        if(list.size() != 3) {

            for(int y = 0; y < n; y++) {
                search:for(int x = 0; x < m; x++) {

                    if(map[y][x] == 0) {
                        if(list.isEmpty()) {
                            list.add(new Position(x, y));
                            makeWall(list, list.get(list.size() -1 ), idx+1);
                            list.remove(idx);
                        }
                        else {
                            for(Position comp : list) {
                                if(comp.x == x && comp.y == y) {
                                    continue search;
                                }
                            }
                            list.add(new Position(x, y));
                            makeWall(list, list.get(list.size() -1 ), idx+1);
                            list.remove(idx);
                        }
                    }
                }
            }
        } else if(list.size() == 3) {

            if(!visited[list.get(0).y][list.get(0).x][list.get(1).y][list.get(1).x][list.get(2).y][list.get(2).x]) {
                visited[list.get(0).y][list.get(0).x][list.get(1).y][list.get(1).x][list.get(2).y][list.get(2).x] = true;
                for (int y = 0; y < n; y++) {
                    copyMap[y] = map[y].clone();
                }
                copyMap[list.get(0).y][list.get(0).x] = 1;
                copyMap[list.get(1).y][list.get(1).x] = 1;
                copyMap[list.get(2).y][list.get(2).x] = 1;

                int result = countFunction();
                maxResult = Math.max(result, maxResult);

            }
            return;
        }
    }

    public static int countFunction() {
        Queue<Position> q = new ArrayDeque<>();

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(copyMap[y][x] == 2) {
                    q.add(new Position(x, y));

                    while(!q.isEmpty()) {
                        Position p = q.poll();
                        for(int i = 0; i < 4; i++) {
                            int newX = p.x + dx[i];
                            int newY = p.y + dy[i];

                            if(isRange(newX, newY) && copyMap[newY][newX] == 0) {
                                copyMap[newY][newX] = 2;
                                q.add(new Position(newX, newY));
                            }
                        }

                    }
                }
            }
        }

        int cnt = 0;
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(copyMap[y][x] == 0) cnt++;
            }
        }
        return cnt;

    }

    public static boolean isRange(int x, int y) {
        if(y < 0 || y >= n || x < 0 || x >= m) return false;
        return true;
    }

}
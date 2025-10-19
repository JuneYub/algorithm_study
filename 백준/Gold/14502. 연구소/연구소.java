import java.util.*;
import java.lang.*;
import java.io.*;

class Position {
    int x, y;
    Position(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

class Main {

    public static int N, M;
    public static int[][] map;
    public static int[][] tmpMap;
    public static List<Position> plist = new ArrayList<>();
    public static boolean[] visited;
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};
    public static int ans = 0;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tmpMap = new int[N][M];

        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] == 0)
                    plist.add(new Position(y, x));
            }
        }

        visited = new boolean[plist.size()];
        // 벽세우기
        makeWall(0, new ArrayList<>());

        System.out.println(ans);
    }

    public static void makeWall(int start, List<Position> tmp) {
        if(tmp.size() == 3) {

            // 지도를 복사하자.
            for(int y = 0; y < N; y++) {
                tmpMap[y] = Arrays.copyOf(map[y], map[y].length);
            }

            for(Position w : tmp) {
                tmpMap[w.y][w.x] = 1;
            }
            
            // 벽을 3개 세웠다. 바이러스를 퍼트리자
            doVirus();
            return;
        }

        for(int i = start; i < plist.size(); i++) {
            if(!visited[i]) {
                tmp.add(plist.get(i));
                visited[i] = true;
                makeWall(i+1, tmp);
                tmp.remove(tmp.size()-1);
                visited[i] = false;
            }
        }

        
    }

    // 바이러스를 퍼트리고, 안전구역 개수세기
    public static void doVirus() {
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(tmpMap[y][x] == 2) {
                    moveVirus(y, x);
                }
            }
        }

        // 안전구역 개수세기
        int cnt = 0;
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                if(tmpMap[y][x] == 0) {
                   cnt++; 
                }
            }
        }
        //printMap();
        ans = Math.max(ans, cnt);
    }

    
    public static void moveVirus(int y, int x) {
        Queue<Position> q = new ArrayDeque<>();
        q.add(new Position(y, x));
        
        while(!q.isEmpty()) {
            Position p = q.poll();
            for(int i = 0; i < 4; i++) {
                int newY = p.y + dy[i];
                int newX = p.x + dx[i];
                if(!isRange(newY, newX)) continue;
                if(tmpMap[newY][newX] == 1) continue;
                
                if(tmpMap[newY][newX] == 0) {
                    tmpMap[newY][newX] = 2;
                    q.add(new Position(newY, newX));
                }
            }
        }
    }

    public static boolean isRange(int y, int x) {
        if(y < 0 || y >= N || x < 0 || x >= M) return false;
        return true;
    }

    public static void printMap() {
        for(int y = 0; y < N; y++) {
            for(int x = 0; x < M; x++) {
                System.out.print(tmpMap[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("================================================");
    }
}
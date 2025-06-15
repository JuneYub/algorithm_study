import java.util.*;
import java.lang.*;
import java.io.*;

class Position {
    int x; 
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    public static int N, M;
    public static int[][] map;
    public static boolean[][] visited;
    public static int NULL_SPACE = 99;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1 , 0, -1};

    public static int maxRainbow;
    public static int curRainbow;
    
    public static List<Position> marking = new ArrayList<>();
    public static int result = 0;
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        
        for(int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            // 방문 배열 초기화
            visited = new boolean[N][N];
            marking = new ArrayList<>();
            // 최대그룹블록수의 기준점
            int groupX = 0;
            int groupY = 0;
            int maxSize = 0;
            maxRainbow = 0;
            curRainbow = 0;
            
            // 그룹블록 찾기
            for(int y = 0; y < N; y++) {
                for(int x = 0; x < N; x++) {
                    if(!visited[y][x] && map[y][x] >= 1 && map[y][x] <= M) {
                        List<Position> posList = search(y, x, map[y][x]);
                        if(posList.size() < 2) continue;
        
                        if (
                            posList.size() > maxSize ||
                            (posList.size() == maxSize && curRainbow > maxRainbow) ||
                            (posList.size() == maxSize && curRainbow == maxRainbow &&
                             (y > groupY || (y == groupY && x > groupX)))
                        ) {
                            maxSize = posList.size();
                            maxRainbow = curRainbow;
                            groupX = x;
                            groupY = y;
                            marking = posList;
                        }
                    }
                    
                }
            }

            // 마킹된거 다 널로 바꾸기
            for(Position pos : marking) {
                map[pos.y][pos.x] = NULL_SPACE;
            }

            
            if(marking.size() < 2) break;
            result += (marking.size() * marking.size());

            gravity();
            reverseRotation();
            gravity();
            
        }
        System.out.println(result);
    }

    
    // 그룹블록의 사이즈 찾기
    public static List<Position> search(int y, int x, int color) {
        boolean[][] visited0 = new boolean[N][N];
        List<Position> result = new ArrayList<>();
        curRainbow = 0;
        
        Queue<Position> q = new ArrayDeque<>();
        visited[y][x] = true;
        q.add(new Position(x, y));
        result.add(new Position(x, y));
        
        while(!q.isEmpty()) {
            Position p = q.poll();
            //System.out.println("y " + p.y + " x" + p.x + " 비교색 " + color + " 현재색 " + map[p.y][p.x]);

            for(int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if(!isRange(newY, newX)) continue;
                if(map[newY][newX] == -1 || map[newY][newX] == NULL_SPACE) continue;
                if(visited[newY][newX]) continue;
                if(map[newY][newX] != color && map[newY][newX] != 0) continue;
                if(visited0[newY][newX]) continue;
                
                if(map[newY][newX] == 0) {
                    visited0[newY][newX] = true;
                    curRainbow++;
                } else {
                    visited[newY][newX] = true;
                }
                q.add(new Position(newX, newY));
                result.add(new Position(newX, newY));
            }
        }    
        return result;
    }
    
    // 반시계방향
    public static void reverseRotation() {
        // 2, 3 => 3, 2
        int[][] result = new int[N][N];

        for(int y = 0; y < N; y++) {
            for(int x = 0; x < N; x++) {
                result[N - 1 - x][y] = map[y][x];
            }
        }
        map = result;
    } 

    // 중력
    public static void gravity() {
        // 하단부터 상단으로 가면서 하강할 대상 탐색
        for(int x = 0; x < N; x++) {
            for(int y = N-1; y >= 0; y--) {
                
                if(map[y][x] != -1 && map[y][x] != NULL_SPACE) {
                    
                    // 현재 지점부터 하단으로 떨어지는 길이를 카운트한다.
                    int len = 0;
                    for(int curY = y+1; curY < N; curY++) {
                        if(map[curY][x] == NULL_SPACE) {
                            len++;
                        }
                        else {
                            break;
                        }
                    }
                    
                    // 길이만큼 y를 땡겨서 내린다.
                    map[y+len][x] = map[y][x];
                    if(len != 0) {
                        map[y][x] = NULL_SPACE;
                    }

                 }               
            }
        }
    }

    public static boolean isRange(int y, int x) {
        if(y < 0 || y >= N || x < 0 || x >= N)
            return false;
        return true;
    }
}
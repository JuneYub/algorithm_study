import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    public static int n, m;
    public static int[][] map;
    public static int[][] visited;
    public static int groupId = 0;
    public static int count = 0;
    public static Map<Integer, Integer> hashMap = new HashMap<>();
    public static int[] dx = {0, 1, 0, -1};
    public static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new int[n][m];
        
        for(int y = 0; y < n; y++) {
            String str = br.readLine();
            for(int x = 0; x < m; x++) {
                map[y][x] = str.charAt(x) - '0';
            }
        }

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] == 0 && visited[y][x] == 0) {
                    count = 0;
                    ++groupId;
                    dfs(x, y);
                    hashMap.put(groupId, count);
                }
            }
        }

        int[][] result = new int[n][m];
        // 1인 지점을 찾고 주변 0으로 연결된 곳의 개수를 카운트
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] == 1) {
                    result[y][x] = findZero(x, y);
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                sb.append(result[y][x]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public static int findZero(int x, int y) {
        int sum  = 0;
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(isRange(newX, newY) && map[newY][newX] == 0) {
                if(!set.contains(visited[newY][newX])) {
                    set.add(visited[newY][newX]);
                    sum += hashMap.get(visited[newY][newX]);    
                }
                
            }
        }
        sum += 1;
        return sum % 10;
    }

    public static void dfs(int x, int y) {

        visited[y][x] = groupId;
        count++;

        for(int i = 0; i < 4; i++) {
            int newX = x + dx[i];
            int newY = y + dy[i];
            
            if(!isRange(newX, newY)) continue;
            if(map[newY][newX] != 0) continue;
            if(visited[newY][newX] != 0) continue;

            dfs(newX, newY);
            
        }
        
    }

    public static boolean isRange(int x, int y) {
        if(y < 0 || y >= n || x < 0 || x >= m) return false;
        return true;
    }
}
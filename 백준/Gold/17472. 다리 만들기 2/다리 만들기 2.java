import java.util.*;
import java.io.*;

class Position {
    int x, y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Edge implements Comparable<Edge> {
    int start, end, weight;

    Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o1) {
        return this.weight - o1.weight;
    }

    @Override
    public String toString() {
        return this.start + " " + this.end + " " +this.weight + " ";
    }
}

public class Main
{

    static int m, n;
    static int[][] map;
    static boolean[][] visited;
    static int islandSize = 1;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] parents;

    // 그래프
    static List<Edge> edgeList;
    static int[][] graph;
    static int[] parent;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];
        // 섬 구분하기
        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] == 1 && !visited[y][x]) {
                    visited[y][x] = true;
                    map[y][x] = islandSize;
                    countIsland(x, y);
                    islandSize++;
                }
            }
        }
        islandSize--;

        // 그래프 초기화
        graph = new int[islandSize+1][islandSize+1];
        for(int i = 0; i <= islandSize; i++) {
            for(int j = 0; j <= islandSize; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }

        edgeList = new ArrayList<>();

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(map[y][x] != 0) {
                    makeBridege(x, y);
                }
            }
        }

        // 크루스칼 알고리즘
        Collections.sort(edgeList);

        parent = new int[islandSize + 1];

        for(int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        int ans = 0;
        int i = 0;
        for(int j = 0; j < edgeList.size(); j++) {
            Edge edge = edgeList.get(j);

            if(isUnion(edge.start, edge.end)) continue;

            ans += edge.weight;

            if(++i == islandSize-1) break;
        }
        //System.out.println("섬 크기" + islandSize + " 간선 개수 " + i);
        System.out.println((ans == 0 || i != islandSize-1) ? -1 : ans);

    }

    public static int find(int x) {
        if(parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    public static boolean isUnion(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if(rootX == rootY) return true;

        parent[rootY] = rootX;
        return false;


    }

    public static void makeBridege(int x, int y) {
        int startIsland = map[y][x];

        for(int dir = 0; dir < 4; dir++) {
            int newX = x + dx[dir];
            int newY = y + dy[dir];
            int len = 0;

            if(isRange(newX, newY) && map[newY][newX] == 0) {

                while(true) {
                    // 범위를 벗어나면 멈춘다.
                    if(!isRange(newX, newY)) {
                        break;
                    }

                    // 바다라면 계속 진행
                    if(map[newY][newX] == 0) {
                        len++;
                        newX = newX + dx[dir];
                        newY = newY + dy[dir];
                        continue;
                    }

                    else {
                        if(len >= 2) {
                            int endIsland = map[newY][newX];
                            edgeList.add(new Edge(startIsland, endIsland, len));
                            //graph[startIsland][endIsland] = Math.min(graph[startIsland][endIsland], len);

                        }
                        break;
                    }
                }


            }
        }

    }

    public static void countIsland(int x, int y) {
        Queue<Position> q = new ArrayDeque<>();
        q.add(new Position(x, y));

        while(!q.isEmpty()) {
            Position p = q.poll();

            for(int i = 0; i < 4; i++) {
                int newX = p.x + dx[i];
                int newY = p.y + dy[i];

                if(isRange(newX, newY) && !visited[newY][newX] && map[newY][newX] == 1 ) {
                    q.add(new Position(newX, newY));
                    map[newY][newX] = islandSize;
                    visited[newY][newX] = true;
                }
            }
        }

    } // end of countIsland


    public static boolean isRange(int x, int y) {
        if(x < 0 || x >= m || y < 0 || y >= n) {
            return false;
        }
        return true;
    }

}

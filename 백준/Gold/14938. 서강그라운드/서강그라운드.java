import java.util.*;
import java.lang.*;
import java.io.*;

class Position implements Comparable<Position> {
    int end;
    int value;

    public Position(int end, int value) {
        this.end = end;
        this.value = value;
    }

    public int compareTo(Position p) {
        return this.value - p.value;
    }
}

class Main {
    static int n, m, r;
    static int[] items;
    static List<List<Position>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n+1];
        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Position(end, value));
            graph.get(end).add(new Position(start, value));
        }

        int ans = 0;
        for(int i = 1; i <= n; i++) {
            ans = Math.max(dijkstra(i), ans);
        }
        System.out.print(ans);
    }

    public static int dijkstra(int startNode) {
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startNode] = 0;

        PriorityQueue<Position> pq = new PriorityQueue<>();
        pq.add(new Position(startNode, 0));

        int total = 0;
        while(!pq.isEmpty()) {
            Position p = pq.poll();

            if(visited[p.end]) continue;
            visited[p.end] = true;
            
            if(distance[p.end] <= m) {
                total += items[p.end];
            }

            for(int i = 0; i < graph.get(p.end).size(); i++) {
                int next = graph.get(p.end).get(i).end;
                int nextDistance = graph.get(p.end).get(i).value;

                if(distance[next] > nextDistance + p.value) {
                    distance[next] = nextDistance + p.value;
                    pq.add(new Position(next, distance[next]));
                }
            }
                
            
            
        }
        //System.out.println();
        return total;
        
    }
}
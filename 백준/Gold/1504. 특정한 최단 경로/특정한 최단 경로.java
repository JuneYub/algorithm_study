import java.util.*;
import java.lang.*;
import java.io.*;

class Node implements Comparable<Node> {
    int curr;
    int dist;

    public Node(int curr, int dist) {
        this.curr = curr;
        this.dist = dist;
    }

    public int compareTo(Node n) {
        return this.dist - n.dist;
    }
    
}

class Main {
    public static int n, e;
    public static int nodeStart, nodeEnd;
    public static List<List<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, dist));
            graph.get(end).add(new Node(start, dist));
        }

        st = new StringTokenizer(br.readLine());
        nodeStart = Integer.parseInt(st.nextToken());
        nodeEnd = Integer.parseInt(st.nextToken());

        long dist1 = Math.min( (long)dijkstra(1, nodeStart) + dijkstra(nodeStart, nodeEnd) + dijkstra(nodeEnd, n),
                             (long)dijkstra(1, nodeEnd) + dijkstra(nodeEnd, nodeStart) + dijkstra(nodeStart, n));

        
        if(dist1 >= Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(dist1);
        }
    }

    public static int dijkstra(int start, int end) {
        int[] distance = new int[n+1];
        boolean[] visited = new boolean[n+1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node n = pq.poll();

            if(visited[n.curr]) continue;
            visited[n.curr] = true;

            for(Node next : graph.get(n.curr)) {
                if(distance[next.curr] >  distance[n.curr] + next.dist) {
                    distance[next.curr] = distance[n.curr] + next.dist;
                    pq.add(new Node(next.curr, distance[next.curr]));
                }
            }
        }

        return distance[end];
        
        
    }
}
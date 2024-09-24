import java.util.*;
import java.lang.*;
import java.io.*;

class Node implements Comparable<Node> {
    int end, weight;

    public Node(int end, int weight) {
        this.end = end;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

class Main {
    public static int n, m, r;
    public static int[] items;
    public static List<List<Node>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }
        
        int maxItems = 0;
        for (int i = 1; i <= n; i++) {
            maxItems = Math.max(maxItems, bfs(i));
        }
        System.out.println(maxItems);

    }
    public static int bfs(int start) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        q.add(new Node(start, 0));
        dist[start] = 0;

        int total = 0;
        while(!q.isEmpty()) {
            Node current = q.poll();
            int curr = current.end;

            if(dist[curr] < current.weight) continue;

            if(dist[curr] <= m) {
                total += items[curr];
            }

            for(Node next : graph.get(curr)) {
                if(dist[next.end] > dist[curr] + next.weight) {
                    dist[next.end] = dist[curr] + next.weight;
                    q.offer(new Node(next.end, dist[next.end]));
                }
            }
        }
        return total;
    }
}
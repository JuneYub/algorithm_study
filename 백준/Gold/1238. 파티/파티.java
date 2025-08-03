import java.util.*;
import java.lang.*;
import java.io.*;

class Node implements Comparable<Node> {
    int end, time;

    public Node(int end, int time) {
        this.end = end;
        this.time = time;
    }

    public int compareTo(Node n) {
        return this.time - n.time;
    }
}

class Main {
    public static int n, m, x;
    public static List<Node>[] graph, reverseGraph;
    public static int[] visited;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        graph = new ArrayList[n+1];
        reverseGraph = new ArrayList[n+1];
        for(int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, time));
            reverseGraph[end].add(new Node(start, time));
        } // 그래프 초기화 끝

        int[] dist1 = findRoute(x, graph);
        int[] dist2 = findRoute(x, reverseGraph);

        int maxTime = 0;
        for(int i = 1; i <= n; i++) {
            if(i == x) continue;
            int 가는시간 = dist1[i];
            int 오는시간 = dist2[i];
            maxTime = Math.max(maxTime, (가는시간 + 오는시간));
        }
        System.out.println(maxTime);
    }

    public static int[] findRoute(int start, List<Node>[] graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        visited = new int[n+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[start] = 0;

        while(!pq.isEmpty()) {

            Node node = pq.poll();
            // 그래프 탐색
            for(int j = 0; j < graph[node.end].size(); j++) {
                Node curNode = graph[node.end].get(j);
                if(visited[curNode.end] < (node.time + curNode.time) ) continue;
                pq.add(new Node(curNode.end, (node.time + curNode.time) ));
                visited[curNode.end] = node.time + curNode.time;
                //System.out.println(start + " " + end + "까지 가는 길 계산 중 " + (node.time + curNode.time));
            }
        }
        return visited;
    }
}
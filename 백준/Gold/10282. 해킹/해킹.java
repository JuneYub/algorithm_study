
import java.util.*;
import java.lang.*;
import java.io.*;

class Node implements Comparable<Node> {
    int idx, time;

    public Node(int idx, int time) {
        this.idx = idx;
        this.time = time;
    }

    public int compareTo(Node n) {
        return this.time - n.time;
    }
}

class Main {
    public static int n, d, c;
    public static List<Node>[] map;
    public static boolean[] visited;
    public static int INF = Integer.MAX_VALUE;
    public static int[] dist;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for(int i = 0; i < testCase; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            dist = new int[n+1];
            Arrays.fill(dist, INF);
            dist[c] = 0;

            map = (List<Node>[]) new ArrayList[n + 1];
            for(int j = 0; j <= n; j++) {
                map[j] = new ArrayList<>();
            }
            visited = new boolean[n+1];

            // 의존성 표시
            for(int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                map[b].add(new Node(a, s));
            }

            // 시작노드를 큐에 넣는다.
            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(c, 0));

            while(!pq.isEmpty()) {
                Node node = pq.poll();
                if(!visited[node.idx]) {
                    visited[node.idx] = true;
                    for(int j = 0; j < map[node.idx].size(); j++) {
                        int nextTime = map[node.idx].get(j).time + node.time;
                        if(dist[map[node.idx].get(j).idx] > nextTime) {
                            dist[map[node.idx].get(j).idx] = nextTime;
                            pq.add(new Node(map[node.idx].get(j).idx, nextTime));
                        }
                    }
                }

            }

            // 전체 감염되는 컴퓨터 수
            int cnt = 0;
            int totalTime = 0;
            for(int j = 1; j <= n; j++) {
                if(dist[j] != INF) {
                    cnt++;
                    totalTime = Math.max(totalTime, dist[j]);
                }
            }
            System.out.println(cnt + " " + totalTime);
        }


    }
}
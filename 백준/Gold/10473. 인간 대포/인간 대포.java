import java.util.*;
import java.lang.*;
import java.io.*;

class Node implements Comparable<Node> {
    double x, y, time;
    int index;

    public Node(int index, double time) {
        this.index = index;
        this.time = time;
    }

    public Node (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int compareTo(Node n) {
        if(this.time - n.time > 0) 
            return 1;
        else if(this.time - n.time == 0) 
            return 0;
        else 
            return -1;
    }
}


class Main {

    public static double startX, startY;
    public static double endX, endY;
    public static int n;
    public static double[][] graph;
    public static Node[] nodes;
    public static double[] dist;
    public static boolean[] visited;
    public static PriorityQueue<Node> pq = new PriorityQueue<>();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        startX = Double.parseDouble(st.nextToken());
        startY = Double.parseDouble(st.nextToken());

        st = new StringTokenizer(br.readLine());
        endX = Double.parseDouble(st.nextToken());
        endY = Double.parseDouble(st.nextToken());

        n = Integer.parseInt(br.readLine());
        graph = new double[n+2][n+2]; 
        nodes = new Node[n+2];

        nodes[0] = new Node(startX, startY);
        nodes[n+1] = new Node(endX, endY);

        // 노드 배열 초기화
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            Double x = Double.parseDouble(st.nextToken());
            Double y = Double.parseDouble(st.nextToken());
            nodes[i] = new Node(x, y); 
        }

        // 그래프 초기화
        for(int i =0; i < n+2 ; i++) {
            for(int j = 0; j < n+2; j++) {
                if(i == j) {
                    //System.out.print("             ");
                    continue;
                } 

                double distance = getDistance(nodes[i], nodes[j]);
                double walkTime = distance / 5.0;
                double cannonTime = i == 0 ? Double.MAX_VALUE : 2.0 + Math.abs(distance - 50.0) / 5.0;

                graph[i][j] = Math.min(walkTime, cannonTime); 
                //System.out.print(graph[i][j] + " ");
            }
            //System.out.println();
        }

        // 다익스트라 시작
        dist = new double[n+2];
        visited = new boolean[n+2];
        Arrays.fill(dist, Double.MAX_VALUE);
        dist[0] = 0.0;
        pq.add(new Node(0, 0.0));

        while(!pq.isEmpty()) {
            Node current = pq.poll();
            int curIndex = current.index;

            if(visited[curIndex]) continue;
            visited[curIndex] = true;

            for(int i = 0; i < n+2; i++) {
                if(i == curIndex) continue;

                double newTime = dist[curIndex] + graph[curIndex][i];

                if(newTime < dist[i]) {
                    dist[i] = newTime;
                    pq.add(new Node(i, newTime));
                }
            }
        }

        System.out.printf("%.6f\n" ,dist[n+1]);
        
    }

    public static double getDistance(Node n1, Node n2) {
        return Math.hypot(n1.x - n2.x, n1.y - n2.y);
    }
}
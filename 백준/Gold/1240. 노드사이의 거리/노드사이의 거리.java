import java.util.*;
import java.io.*;
class Edge {
	int node;
	int weight;
	
	Edge(int node, int weight) {
		this.node = node;
		this.weight = weight;
	}
	
}

public class Main {

	static int n,m;
	static boolean[] visited;
	static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	
    	// 트리 초기화
    	for(int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<Edge>());
    	}
    	
    	for(int i = 0; i < n-1; i++) {
    		st = new StringTokenizer(br.readLine());
    		int node1 = Integer.parseInt(st.nextToken());
    		int node2 = Integer.parseInt(st.nextToken());
    		int weight = Integer.parseInt(st.nextToken());
    		
    		graph.get(node1).add(new Edge(node2, weight));
    		graph.get(node2).add(new Edge(node1, weight));
    	}
    	
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int startNode = Integer.parseInt(st.nextToken());
    		int endNode = Integer.parseInt(st.nextToken());
    		
    		// 방문 배열 초기화
        	visited = new boolean[n+1];
        	// 시작 방문 처리
    		visited[startNode] = true;
    		dfs(startNode, endNode, 0);	
    	}
    }
    
    public static void dfs(int startNode, int endNode, int length) {
    	
    	if(startNode == endNode) {
    		System.out.println(length);
    		return;
    	}
    	
    	ArrayList<Edge> curNode = graph.get(startNode);
    	for(int i = 0; i < curNode.size(); i++) {
    		
    		int node = curNode.get(i).node;
    		
    		if(!visited[node]) {
    			visited[node] = true;
    			dfs(node, endNode, length + curNode.get(i).weight);
    			visited[node] = false;
    		}
    		
    	}
    	
    }


}

import java.util.*;
import java.io.*;

class Node {
	int curNode;
	int end;
	int weight;
	
	Node(int curNode, int end, int weight) {
		this.curNode = curNode;
		this.end = end;
		this.weight = weight;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.curNode != ((Node)o).curNode) return false;
		if(this.end != ((Node)o).end) return false;
		if(this.weight != ((Node)o).weight) return false;
		
		return true;
	}
	
}

class Vertex {
	int nowVertex;
	int length;
	
	Vertex(int nowVertex, int length) {
		this.nowVertex = nowVertex;
		this.length = length;
	}
}

public class Main {

	static Map<String, Node> map;
	static int v,e,k;
	static int minResult;
	static PriorityQueue<Vertex> pq;
	static int[] dist;
	static List<List<Node>> graph = new ArrayList<>();
	
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		v = Integer.parseInt(st.nextToken());
		e = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(br.readLine());
		
		for(int i = 0; i <= v; i++) {
			graph.add(new ArrayList<Node>());
		}
		
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int curNode = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			graph.get(curNode).add(new Node(curNode, end, weight));
		}
		
		bfs(k);
		for(int i = 1; i <= v; i++) {
			if(dist[i] == Integer.MAX_VALUE) {
				bw.write("INF");
				
			} else {
				bw.write(String.valueOf(dist[i]));
			}
			bw.newLine();
		}
		bw.flush();
	}
	
	public static void bfs(int startNode) throws Exception {
		pq = new PriorityQueue<Vertex>(new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.length - o2.length;
			}
		});
		
		
		boolean[] visited = new boolean[v+1];
		dist = new int[v+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.add(new Vertex(startNode, 0));
		dist[startNode] = 0;
		
		while(!pq.isEmpty()) {
			
			Vertex v = pq.poll();
			
			if(!visited[v.nowVertex]) {
				visited[v.nowVertex] = true;	
			} else {
				continue;
			}
			
			int nowLeng = dist[v.nowVertex];
			
			for(int i = 0; i < graph.get(v.nowVertex).size(); i++) {
				Node node = graph.get(v.nowVertex).get(i);
				
				if(dist[node.end] > node.weight + nowLeng) {
					dist[node.end] = node.weight + nowLeng;
					pq.add(new Vertex(node.end, dist[node.end]));
				}
			}
		}
		

	}
}
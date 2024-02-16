import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int a1, a2;
	static int result = -1;
	static boolean[] visited;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		visited = new boolean[n+1];
		
		List<ArrayList<Integer>> graph = new ArrayList<>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		a1 = Integer.parseInt(st.nextToken());
		a2 = Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int parents = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			graph.get(parents).add(child);
			graph.get(child).add(parents);
		}
		
		visited[a1] = true;
		dfs(a1, 0, graph);
		System.out.println(result);
	}
	
	public static void dfs(int startNode, int depth, List<ArrayList<Integer>> graph) {
		if(startNode == a2) {
			result = depth;
			return;
		}
		
		for(int i = 0; i < graph.get(startNode).size(); i++) {
			if(!visited[graph.get(startNode).get(i)]) {
				visited[graph.get(startNode).get(i)] = true;
				dfs(graph.get(startNode).get(i), depth+1, graph);
				visited[graph.get(startNode).get(i)] = false;
			}
			
		}
	}

}
import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static int[] parents;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		parents = new int[n+1];
		visited = new boolean[n+1];
		StringTokenizer st;
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			
			graph.get(node1).add(node2);
			graph.get(node2).add(node1);
		}
		
		dfs(1);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 2; i < parents.length; i++) {
			bw.write(parents[i]+"");
			bw.newLine();
		}
		bw.flush();
		
	}
	
	public static void dfs(int curNode) {
		for(int i = 0; i < graph.get(curNode).size(); i++) {
			int child = graph.get(curNode).get(i);
			
			if(!visited[child]) {
				parents[child] = curNode;
				visited[child] = true;
				dfs(child);
			}
		}
	}
	
}
    

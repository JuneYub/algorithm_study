import java.util.*;
import java.io.*;


public class Main {

	static int n, r, q;
	static int[] size;
	static boolean[] visited;
	static List<List<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		size = new int[n+1];
		visited = new boolean[n+1];
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		// 그래프의 서브트리정점 개수 체크
		dfs(r);
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			int num = Integer.parseInt(br.readLine());
			sb.append(size[num] + "\n");
		}
		System.out.print(sb.toString());
	}
	
	public static int dfs(int node) {
		visited[node] = true;

		int sum = 0;
		for(int i = 0; i < graph.get(node).size(); i++) {
			if(!visited[graph.get(node).get(i)]) {
			sum += dfs(graph.get(node).get(i));
			}
		}
		size[node] = sum + 1;
		return sum+1;
	}
}

import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		boolean flag = false;
		for(int i = 0; i < n; i++) {
			visited = new boolean[n];
			visited[i] = true;
			if(check(0, i) >= 4) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
	public static int check(int depth, int node) {
		if(depth >= 4) {
			return depth;
		}
		
		int maxReturn = depth;
		for(int i = 0; i < graph.get(node).size(); i++) {
			if(!visited[graph.get(node).get(i)]) {
				visited[graph.get(node).get(i)] = true;
				maxReturn = Math.max(maxReturn, check(depth+1, graph.get(node).get(i)));
				visited[graph.get(node).get(i)] = false;
			}
			
		}
		return maxReturn;
		
	}
}

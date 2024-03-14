import java.util.*;
import java.io.*;


public class Main {
	
	
	static int n, m;
	static int arr[][] = new int[101][2];
	static boolean visited[] = new boolean[101];
	static List<List<Integer>> graph = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			
			graph.get(left).add(right);
		}
		
		for(int i = 1; i <= n; i++) {
			visited = new boolean[101];
			dfs(i,i);
			
		}
		
		
		
		int half = (n+1)/2;
		int ans = 0;
		for(int i = 1; i <= n; i++) {
			if(arr[i][0] >= half || arr[i][1] >= half) {
				ans++;
				//System.out.println(i);
			}
		}
		
		System.out.println(ans);
		
	}
	
	public static void dfs(int current, int start) {
		visited[current] = true;

		for(int i = 0; i <  graph.get(current).size(); i++) {
			if(!visited[graph.get(current).get(i)]) {
				
				int small = graph.get(current).get(i);
				arr[start][0]++; // 현재 수보다는 작은 수의 개수 증가
				arr[small][1]++; // 해당 수보다 큰 수의 개수 증가
				
//				System.out.println(start + " 이 수보다 작은 수 증가");
//				System.out.println(small + " 이 수보다 큰 수 증가");
				
				dfs(graph.get(current).get(i), start);
			}
		}
		
	}
	
}

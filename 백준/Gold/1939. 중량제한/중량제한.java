import java.io.*;
import java.util.*;

class Bridge {
	int end, weight;
	
	public Bridge(int end, int weight) {
		this.end = end;
		this.weight = weight;
	}
}

public class Main {
	static int n, m;
	static boolean[] visited;
	static int start, target;
	static List<List<Bridge>> bridges = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n+1];
		
		for(int i = 0; i <= n; i++) {
			bridges.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			bridges.get(a).add(new Bridge(b, c));
			bridges.get(b).add(new Bridge(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		target = Integer.parseInt(st.nextToken());
		
			
		int left = 1;
		int right = 1_000_000_000;
		int ans = 0;
		
		
		while(left <= right) {
			
			int mid = (left + right) / 2;
			
			if(canCross(mid)) {
				ans = Math.max(ans, mid);
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.print(ans);
	}
	
	public static boolean canCross(int mid) {
		return bfs(mid);
	}
	
	public static boolean bfs(int mid) {
		Arrays.fill(visited, false);
		visited[start] = true;
		
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			if(cur == target) {
				return true;
			}
			
			for(Bridge b : bridges.get(cur)) {
				if(b.weight < mid) continue;
				
				if(!visited[b.end]) {
					visited[b.end] = true;
					q.add(b.end);
				}
			}
			
		}
		return false;
	}
	

}

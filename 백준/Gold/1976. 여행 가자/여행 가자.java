import java.io.*;
import java.util.*;
public class Main {

	static int n, m;
	static Set<Integer> sets[];
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		
		sets = new HashSet[n+1];
		for(int i = 0; i <= n; i++) {
			sets[i] = new HashSet<Integer>();
		}
		
		StringTokenizer st;
		for(int y = 0; y<n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x< n; x++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					sets[y+1].add(x+1);
				}
				
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int first = Integer.parseInt(st.nextToken());
		
		visited = new boolean[n+1];
		dfs(first);
		
		
		boolean isConnect = true;
		for(int i = 1; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(!visited[num]) {
				
				isConnect = false;
				break;
			}
		}
		
		if(isConnect) {
			System.out.print("YES");
		} else {
			System.out.print("NO");
		}
	}
	
	public static void dfs(int num) {
		//System.out.println(num + " " + visited[num]);
		if(!visited[num]) {
			visited[num] = true;
			for(int newNum : sets[num]) {
				dfs(newNum);
			}
		} else {
			return;
		}
	}

}

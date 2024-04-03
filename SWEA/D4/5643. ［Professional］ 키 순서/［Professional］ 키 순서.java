import java.util.*;
import java.io.*;


public class Solution {
	public static int n,m;
	public static int[][] graph;
	public static int[] parents;
	public static int[] child;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			
			graph = new int[n+1][n+1];
			
			parents = new int[n+1];
			child = new int[n+1];
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				// a에서 b로
				graph[a][b] = 1;
			}
			
			// 그래프를 돌면서 해당 노드의 자식과 부모노드의 개수를 카운트한다.
			for(int i = 1; i <= n; i++) {
				// node i의 자식 개수 세기
				int childNum = findChild(i);
				child[i] = childNum;
				
				// node i의 부모 개수 세기
				int parentsNum = findParents(i);
				parents[i] = parentsNum;
			}
			
			int ans = 0;
			for(int i = 1; i <= n; i++) {
				if(parents[i] + child[i] == n-1) ans++;
			}

			System.out.println("#"+tc+" " +ans);
		}

	}

	private static int findParents(int num) {
		boolean[] visited = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(num);
		visited[num] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			// 큐에서 가져온 노드의 자식 노드 탐색
			for(int i = 1; i <= n; i++) {
				if(graph[idx][i] != 0 && !visited[i]) {
					visited[i] = true;
					q.add(i);
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static int findChild(int num) {
		boolean[] visited = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(num);
		visited[num] = true;
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int idx = q.poll();
			// 큐에서 가져온 노드의 자식 노드 탐색
			for(int i = 1; i <= n; i++) {
				if(graph[i][idx] != 0 && !visited[i]) {
					visited[i] = true;
					q.add(i);
					cnt++;
				}
			}
		}
		return cnt;
		
	}


}

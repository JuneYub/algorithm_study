
import java.util.*;
import java.io.*;

public class Main
{
	static int n;
	static int start;
	static int[][] map;
	static int[][] shortestMap;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception  {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		shortestMap = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 플로이드 워셜 알고리즘으로 특정지점에서 다른 지점으로 이동하는 최단 거리를 구한다.
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < n; x++) {
				shortestMap[y][x] = map[y][x];
			}
		}
		
		
		for(int k = 0; k < n; k++) {
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(i == j) continue;
					shortestMap[i][j] = Math.min(shortestMap[i][j], shortestMap[i][k] + shortestMap[k][j]);
				}
			}
		}
		
//		for(int y = 0; y < n; y++) {
//			for(int x = 0; x < n; x++) {
//				System.out.print(shortestMap[y][x] + " ");
//			}
//			System.out.println();
//		}
		
		// 백트래킹을 이용해서 경우의 수를 고려해본다.
		visited = new boolean[n];
		visited[start] = true;
		dfs(start, 0, 0, visited); // 시작지점, 횟수, 누적값, 방문배열을 넘긴다.  
		System.out.println(result);
		
	}
	
	public static void dfs(int start, int cnt, int sum, boolean[] visited) {
		if(cnt == n-1) {
			result = Math.min(result, sum);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(start == i) continue;
			if(!visited[i]) {
				visited[i] = true;
				dfs(i, cnt+1, sum + shortestMap[start][i], visited);
				visited[i] = false;
			}
		}
	}
	
} 	

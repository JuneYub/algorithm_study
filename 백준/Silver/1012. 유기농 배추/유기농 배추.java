
import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] bat;
	static boolean[][] visited;
	static int m, n;
	
	public static void dfs(int x, int y) {
		visited[y][x] = true;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		
		for(int i = 0; i < 4; i++) {
			int newX = x+dx[i];
			int newY = y+dy[i];
			
			if(newX >= 0 && newX < m && newY >= 0 && newY < n) {
				if(bat[newY][newX] == 1 && !visited[newY][newX]) {
					dfs(newX, newY);
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(st.nextToken());
		
		for(int num = 0; num < tc; num++) {
			st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			
			bat = new int[n][m];
			visited = new boolean[n][m];
			
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				bat[y][x] = 1;
			}
			
			int cnt = 0;
			
			for(int x = 0; x < m; x++) {
				for(int y = 0; y < n; y++) {
					if(bat[y][x] == 1 && !visited[y][x]) {
						cnt++;
						dfs(x, y);
					}
				}
			}
			sb.append(cnt).append("\n");
			
		}
		System.out.println(sb.toString());
		
	}// end of main(String args[])  -----------------------------
}      
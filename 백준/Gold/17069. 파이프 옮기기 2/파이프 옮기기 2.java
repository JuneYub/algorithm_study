
import java.util.*;
import java.io.*;



public class Main {

	static int n;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		int[][] map = new int[n+1][n+1];
		
		StringTokenizer st;
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// dp y,x,3방향 
		long dp[][][] = new long[n+1][n+1][3];
		
		
		dp[0][1][0] = 1;
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < n; x++) {
				
				// 우측 방향으로 이동
				if(map[y][x+1] != 1) {
					dp[y][x+1][0] += dp[y][x][0];
					dp[y][x+1][0] += dp[y][x][2];
				}
				//System.out.println(dp[y][x+1][0]);
				
				// 세로 방향으로 이동
				if(map[y+1][x] != 1) {
					dp[y+1][x][1] += dp[y][x][1];
					dp[y+1][x][1] += dp[y][x][2]; 
				}
				//System.out.println(dp[y+1][x][1]);
				// 대각선 방향으로 이동
				if(map[y+1][x+1] != 1 && map[y+1][x] != 1 && map[y][x+1] != 1) {
					dp[y+1][x+1][2] = dp[y][x][0] + dp[y][x][1] + dp[y][x][2];
				}
				//System.out.println(dp[y+1][x+1][2]);
			}
		}
		
		System.out.println(dp[n-1][n-1][0] + dp[n-1][n-1][1] + dp[n-1][n-1][2]);
		
		
	}
}

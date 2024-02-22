import java.util.*;
import java.io.*;

public class Main {
	
	static int testCase;
	static int n;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			int[][] arr = new int[2][n+1];
			int[][] dp = new int[2][n+1];
			
			StringTokenizer st;
			
			for(int y = 0; y < 2; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < n; x++) {
					arr[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];
			
			dp[0][1] = arr[1][0] + arr[0][1];
			dp[1][1] = arr[0][0] + arr[1][1];
			
			for(int x = 2; x < n; x++) {
				dp[1][x] = Math.max( (dp[0][x-1] + arr[1][x]), Math.max(dp[0][x-2], dp[1][x-2]) + arr[1][x] );
				dp[0][x] = Math.max( (dp[1][x-1] + arr[0][x]), Math.max(dp[0][x-2], dp[1][x-2]) + arr[0][x] );
				
			}
			System.out.println(Math.max(dp[0][n-1], dp[1][n-1]));
		}
	}
}
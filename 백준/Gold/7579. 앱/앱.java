import java.util.*;
import java.io.*;


public class Main {

	static int n, m;
	static int[] weight = new int[101];
	static int[] value = new int[101];
	static int[][] dp = new int[101][10001];
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int y = 1; y <= n; y++  ) {
			
			for(int x = 0; x <= 10000; x++) {
				
				// 넣는 경우 안넣는 경우
				if(x - value[y] >= 0) {
					dp[y][x] = Math.max(dp[y-1][x] , dp[y-1][x-value[y]] + weight[y]);
				}
				else {
					dp[y][x] = dp[y-1][x];                                                                                                                                               
				}
			}
		}
		
		
		for(int x = 0; x <= 10000; x++) {
			for(int y = 1; y <= n; y++) {
				if(dp[y][x] >= m) {
					System.out.println(x);
					return;
				}
				
			}
		}
	}

}

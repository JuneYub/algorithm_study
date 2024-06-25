
import java.util.*;
import java.io.*;

public class Main {
	
	static int[] weight;
	static boolean[][] dp;
	public static void main(String[] args) throws Exception {
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		dp = new boolean[n+1][40001];
		
		// dp[i][j] i번째 추를 사용해서 j를 만들 수 있는지 여부
		dp[0][0] = true;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		weight = new int[n];
		for(int i = 0; i < n; i++) {
			weight[i] = Integer.parseInt(st.nextToken());
		}
		
		// dp 계산
		for(int i = 1; i <= n; i++) {
			// 현재 무게
			int curWeight = weight[i-1];
			
			// j는 만들 수 있는 무게를 의미
			for(int j = 0; j <= 40000; j++) {
				// 이전 추로 해당 무게를 구성했다면
				if(dp[i-1][j]) {
					dp[i][j] = true;
					if(j+curWeight <= 40000) {
						dp[i][j+curWeight] = true;
					}
					
					if(Math.abs(j-curWeight) <= 40000 ) {
						dp[i][Math.abs(j-curWeight)] = true;
					}
				}
			}
		}
		
		
		int m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < m; i++) {
			int num = Integer.parseInt(st.nextToken());
			if(dp[n][num]) {
                result.append("Y ");
            } else {
                result.append("N ");
            }
		}
		System.out.println(result.toString());
	
	}
	
	
}

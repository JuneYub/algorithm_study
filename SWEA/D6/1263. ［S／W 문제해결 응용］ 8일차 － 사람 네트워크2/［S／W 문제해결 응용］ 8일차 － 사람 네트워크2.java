
import java.util.*;
import java.io.*;

public class Solution
{
    static int testCase = 0;
    static int[][] dp;
    static int n;
    static int INF = (int) 1e9;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc<= testCase; tc++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    int n = Integer.parseInt(st.nextToken());
		    dp = new int[n][n];
		    for(int y = 0; y < n; y++) {
		        for(int x = 0; x < n; x++) {
		            dp[y][x] = Integer.parseInt(st.nextToken()); 
		            
		            // 출발 지점과 도착지점이 같은게 아닌데 0을 받으면 단절되어 있는 거임
		            if(y!=x && dp[y][x] == 0) {
		                dp[y][x] = INF;
		            }
		        }
		    }
		    
		    
		    
		    
		    // 플로이드 워셜 시작
		    for(int k = 0; k < n; k++) {
		        for(int i = 0; i < n; i++) {
		            for(int j = 0; j < n; j++) {
		                dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
		            }
		        }
		    }
		    
		    int minCC = Integer.MAX_VALUE;
		    for(int y = 0; y<n; y++) {
		        int cc = 0;
		        for(int x= 0; x< n; x++) {
		            if(y != x) {
		                cc += dp[y][x];
		            }
		        }
		        
		        minCC = Math.min(minCC, cc);
		    }
		    
		    System.out.println("#"+tc +" " +minCC);
		    
		}
		
		
	}
}

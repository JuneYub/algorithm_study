import java.util.*;
import java.io.*;


public class Solution {
	
	static int n;
	static int[] 연산자;
	static int[] nums;
	static int minAns = Integer.MAX_VALUE;
	static int maxAns = Integer.MIN_VALUE;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			nums = new int[n];
			연산자 = new int[4]; 
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				int num = Integer.parseInt(st.nextToken());
				연산자[i] = num;
				
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			minAns = Integer.MAX_VALUE;
			maxAns = Integer.MIN_VALUE;
			dfs(0, new int[n-1]);
			System.out.println("#" + tc + " "+ (maxAns-minAns));
		}
	}
	
	public static void dfs(int depth, int[] result) {
		if(depth == n-1) {
			int ans = nums[0];
			for(int i = 0; i < n-1; i++) {
				ans = calc(ans, nums[i+1], result[i]);
			}
			minAns = Math.min(minAns, ans);
			maxAns = Math.max(maxAns, ans);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(연산자[i] == 0) {
				continue;
			}
			연산자[i]--;
			result[depth] = i;
			dfs(depth+1, result);
			연산자[i]++;
			
		}
		
		
	}

	public static int calc(int a, int b, int k) {
		
		switch(k) {
		case 0:
			return a+b;
		case 1:
			return a-b;
		case 2:
			return a*b;
		default :
			return a/b;
		}
		
	}

}

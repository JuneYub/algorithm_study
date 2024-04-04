import java.util.*;
import java.io.*;


public class Solution {
	
	static int[] peopleHeight;
	static int peopleCnt;
	static int targetHeight;
	static boolean[] visited;
	static int ans;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int textCase = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= textCase;  tc++) {
			
			st = new StringTokenizer(br.readLine());
			peopleCnt = Integer.parseInt(st.nextToken());
			targetHeight = Integer.parseInt(st.nextToken());
			peopleHeight = new int[peopleCnt];
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < peopleCnt; i++) {
				peopleHeight[i] = Integer.parseInt(st.nextToken());
			}
			ans = Integer.MAX_VALUE;
			visited = new boolean[peopleCnt];
			
			dfs(0, 0, 0);

			System.out.println("#"+tc+" "+(ans-targetHeight));
		}
	}
	
	public static void dfs(int idx, int startIdx, int currentHeight) {
		if(idx == peopleCnt) {
			if(currentHeight >= targetHeight) ans = Math.min(ans, currentHeight);
			return;
		}
		
		if(currentHeight >= targetHeight) {
			ans = Math.min(ans, currentHeight);
			return;
		}
		

		visited[idx] = true;
		dfs(idx+1, startIdx+1, currentHeight + peopleHeight[idx]);
		visited[idx] = false; 
		dfs(idx+1, startIdx+1, currentHeight);

	}
	
}

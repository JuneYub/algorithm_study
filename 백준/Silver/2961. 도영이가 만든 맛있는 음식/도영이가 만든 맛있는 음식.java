
import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static long result; 
	static long[] sArr, bArr;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		sArr = new long[N];
		bArr = new long[N];
		
		visited = new boolean[N];
		result = Long.MAX_VALUE;
		
		StringTokenizer st;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			sArr[i] = Integer.parseInt(st.nextToken());
			bArr[i] = Integer.parseInt(st.nextToken());
		}
		
		recursion(0, 0);
		System.out.println(result);
	}
	
	public static void recursion(int cnt, int useGredient) {
		if(cnt == N) {
			if(useGredient == 0) return;
			
//			for(int i = 0; i < N; i++) {
//				System.out.print(visited[i] + " ");
//			}
//			System.out.println();
			int sResult = 1; 
			int bResult = 0;
			for(int i = 0; i < N; i++) {
				if(visited[i]) {
					sResult *= sArr[i];
					bResult += bArr[i];
				}
			}
			result = Math.min(result, Math.abs(sResult-bResult));
			return;
		}
		
		visited[cnt] = true;
		recursion(cnt+1, useGredient+1);
		visited[cnt] = false;
		recursion(cnt+1, useGredient);
	}
	
}

import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int[] boki;
	static int[] result;
	static boolean[] visited;
	static BufferedWriter bw;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		boki = new int[n];
		visited = new boolean[n];
		result = new int[m];
	
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			boki[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(boki);
		recursion(0);
		bw.flush();
	}
	
	public static void recursion(int cnt) throws Exception {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				bw.write(result[i]+" ");
			}
			bw.newLine();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				result[cnt] = boki[i];
				recursion(cnt+1);
				visited[i] = false;
			}
		}
	}
}

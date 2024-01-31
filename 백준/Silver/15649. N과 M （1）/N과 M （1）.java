
import java.io.*;
import java.util.*;

public class Main {

	static int[] arr;
	static int n, m;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		visited = new boolean[n];
		permutation(0);
	}
	
	public static void permutation(int cnt) {
		if(cnt == m) {
			for(int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				arr[cnt] = (i+1);
				permutation(cnt+1);
				arr[cnt] = 0;
				visited[i] = false;
			}
		}
	}

}

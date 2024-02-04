
import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static Integer sortArr[];
	static int[] result;
	static boolean[] visited;
	static BufferedWriter bw;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		sortArr = new Integer[n];
		visited = new boolean[n];
		result = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			sortArr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(sortArr, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			}
		});
		
		recursion(0, 0);
		bw.flush();
	}
	
	static void recursion(int depth, int startIdx) throws Exception {
		if(depth == m) {
			for(int i = 0; i < m; i++) {
				bw.write(result[i] +" ");
			}
			bw.newLine();
			return;
		}
		
		int beforeNum = 0;
		for(int i = startIdx; i < n; i++) {
			if(beforeNum != sortArr[i] && !visited[i]) {
				visited[i] = true;
				beforeNum = sortArr[i];
				result[depth] = sortArr[i];
				recursion(depth+1, i+1);
				visited[i] = false;
			}
		}
	}
}

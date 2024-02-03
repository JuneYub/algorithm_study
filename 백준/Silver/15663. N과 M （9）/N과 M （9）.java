import java.util.*;
import java.io.*;

public class Main {

	static int n,m;
	static BufferedWriter bw;
	
	static Integer[] arr;
	static boolean[] visited;
	static int[] result;
	static List<int[]> arrayList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new Integer[n];
		visited = new boolean[n];
		result = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			};
		});
		
		recursion(0);
		bw.flush();
	}
	
	static void recursion(int depth) throws Exception {
		if(depth == m) {
			
			for(int i : result) {
				bw.write(i + " ");
			}
			bw.newLine();
			return;
		}
		
		int beforeNum = 0;
		for(int i = 0; i < n; i++) {
			if(beforeNum != arr[i] && !visited[i]) {
				beforeNum = arr[i];
				visited[i] = true;
				result[depth] = arr[i];
				recursion(depth + 1);
				visited[i] = false;
			}

		}
	}

}

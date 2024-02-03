
import java.util.*;
import java.io.*;

public class Main {

	static int n,m;
	static BufferedWriter bw;
	static Integer[] arr;
	static int[] result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new Integer[n];
		result = new int[m];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, new Comparator<Integer>() {

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
				bw.write(result[i] + " ");
			}
			bw.newLine();
			return;
		}
		
		for(int i = startIdx; i < n; i++) {
			result[depth] = arr[i];
			recursion(depth + 1, i);
		}
	}
}

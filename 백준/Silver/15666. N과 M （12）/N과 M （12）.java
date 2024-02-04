import java.util.*;
import java.io.*;

public class Main {
	static int n, m;
	static Integer sortArr[];
	static Integer arr[];
	static int[] result;
	static BufferedWriter bw;
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
		
		Set<Integer> set = new HashSet<Integer>(Arrays.asList(arr));
		sortArr = set.toArray(new Integer[0]);
		
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
		
		for(int i = startIdx; i < sortArr.length; i++) {
			result[depth] = sortArr[i];
			recursion(depth+1, i);
		}
	}
}

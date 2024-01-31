
import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] arr;
	static int[] cumulative;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n];
		cumulative = new int[n];
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if(i == 0) {
				cumulative[i] = arr[i];
			} else {
				cumulative[i] = arr[i] + cumulative[i-1];
			}
		}
		
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int startIdx = Integer.parseInt(st.nextToken())-1;
			int endIdx = Integer.parseInt(st.nextToken())-1;
			
			if(startIdx == 0) {
				sb.append(cumulative[endIdx] + "\n");
			} else {
				sb.append((cumulative[endIdx] - cumulative[startIdx-1] ) + "\n");
			}
		}
		System.out.println(sb.toString());
		
	}
}

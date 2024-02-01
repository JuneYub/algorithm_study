import java.io.*;
import java.util.*;

public class Main {

	static int n, m;
	static int[] result;
	static BufferedWriter bw;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		result = new int[m];
		recursion(0, 0);
		bw.flush();
	}
	

	public static void recursion(int cnt, int startIdx) throws IOException {
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				bw.write(result[i]+" ");
			}
			bw.newLine();
			return;
		}
		
		for(int i = startIdx; i < n; i++) {
			result[cnt] = i+1;
			recursion(cnt+1, i);
		}
	}
}

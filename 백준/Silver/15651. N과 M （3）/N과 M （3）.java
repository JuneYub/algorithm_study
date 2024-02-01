import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void permutation(int depth, int[] arr) throws IOException {
		if(depth == m) {
			for(int i = 0; i < arr.length; i++) {
				bw.write(arr[i] + " ");
			}
			bw.newLine();
			return;
		} // end of if
		
		for(int i = 0; i < n; i++) {

			arr[depth] = i+1;
			permutation(depth + 1, arr);

		}
		
	}
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		

		int[] arr = new int[m];

		permutation(0, arr);
		bw.flush();

		
	}// end of main(String args[]) -----------------------------
		
	
}      
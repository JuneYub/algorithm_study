
import java.util.*;
import java.io.*;


public class Main {
	
	static int n, l;
	static int[] arr;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		for(int i = 0; i < n; i++) {
			if(arr[i] <= l) {
				l++;
			}
		}
		System.out.println(l);
		
	}

	
}
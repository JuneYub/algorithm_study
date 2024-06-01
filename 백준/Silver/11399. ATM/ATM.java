
import java.util.*;
import java.io.*;

public class Main
{
    static int n;
    static int[] arr;
    static int[] cumalative;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		cumalative = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n ; i++) {
		    arr[i] = Integer.parseInt(st.nextToken());
		    
		}
		
		Arrays.sort(arr);
		
		
		cumalative[0] = arr[0];
		int sum = cumalative[0];
		for(int i = 1; i < n ; i++) {
            cumalative[i] = arr[i] + cumalative[i-1];
		    sum += cumalative[i];
		}
		System.out.println(sum);
		
	}
}

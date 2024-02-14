
import java.util.*;
import java.io.*;

public class Main {

	static int n;
	
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(br.readLine());
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    Integer[] arr = new Integer[n];
	    
	    for(int i = 0; i < n; i++) {
	    	arr[i] = Integer.parseInt(st.nextToken());
	    }
	    
	    int i = n-1;
	    while(i > 0 && arr[i] > arr[i-1]) {
	    	i--;
	    }
	    if(i == 0) {
	    	System.out.println(-1);
	    	return;
	    }
	    
	    int j = n-1;
	    while(j > i-1 && arr[i-1] < arr[j]) {
	    	j--;
	    }
	    
	    swap(i-1, j, arr);
	    
	    Arrays.sort(arr, i, n, Collections.reverseOrder());
	    
	    for(int num : arr) {
	    	System.out.print(num+" ");
	    }
	}
	
	public static void swap(int i, int j, Integer[] arr) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
    
}
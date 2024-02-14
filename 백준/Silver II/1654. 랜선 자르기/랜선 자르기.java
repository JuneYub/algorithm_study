
import java.util.*;
import java.io.*;

public class Main {

	static int n, k;
	static long[] arr;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    k = Integer.parseInt(st.nextToken());
	    n = Integer.parseInt(st.nextToken());
	    arr = new long[k];
	    
	    for(int i = 0; i < k; i++) {
	    	arr[i] = Long.parseLong(br.readLine());
	    } 
	    
	    Arrays.sort(arr);
	    long top = arr[k-1]+1;
	    long mid = 0;
	    long bottom = 0;
	    
	    while(bottom < top) {
	    	long cnt = 0;
	    	mid = (bottom+top)/2;
	    	
	    	for(int i = 0; i < k; i++) {
	    		cnt += arr[i]/mid;
	    	}
	    	if(cnt < n) {
	    		top = mid;
	    	} else {
	    		bottom = mid+1;
	    	}
	    }

	    System.out.println(bottom-1);

	}

	
}
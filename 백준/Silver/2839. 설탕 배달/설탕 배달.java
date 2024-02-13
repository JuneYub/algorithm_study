
import java.util.*;
import java.io.*;


public class Main {

	static int n;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
    	int[] memo = new int[5001];
    	for(int i = 1; i <= 5; i++) {
    		memo[i] = -1;
    	}
    	memo[3] = 1;
    	memo[5] = 1;
    	memo[0] = 1;
    	if(n > 5) {
	    	for(int i = 6; i <= n; i++) {
	    		int a = memo[i-3] + 1;
	    		int b = memo[i-5] + 1;
	    		
	    		if(a == 0 && b == 0) {
	    			memo[i] = -1;
	    		}
	    		
	    		else if(a > 0 && b > 0) {
	        		memo[i] =  a < b ? a : b;
	        		
	        	} else {
	        		memo[i] =  a >= b ? a : b;
	        		
	        	}
	    	}
    	}
    	System.out.println(memo[n]);
    	
    }
}
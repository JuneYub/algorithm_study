
import java.util.*;
import java.io.*;

public class Main {

	static int n, map[][], dp[][][];
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
    	PriorityQueue<Integer> heap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				
				return o1- o2;
			}
    	});
    	StringBuilder sb = new StringBuilder();
    	for(int i = 0; i < n; i++) {
    		int num = Integer.parseInt(br.readLine());
    		if(num != 0) {
    			heap.add(num);
    		} else {
    			if(heap.isEmpty()) {
    				sb.append(0+"\n");
    			} else {
    				sb.append(heap.poll()+"\n");
    			}
    			
    		}
    	}
    	System.out.println(sb.toString());
    	
    }
    
}
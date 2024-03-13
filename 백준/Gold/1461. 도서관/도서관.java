
import java.util.*;
import java.io.*;


public class Main {
	static int n,m;
	static PriorityQueue<Integer> q1 = new PriorityQueue<>((a,b)->b-a);
	static PriorityQueue<Integer> q2 = new PriorityQueue<>();
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		int num = Integer.parseInt(st.nextToken());
    		
    		if(num > 0) q1.add(num);
    		if(num < 0) q2.add(num);
    	}
    	
    	int ans = 0;
    	int maxNum = 0;
    	if(q1.isEmpty()) {
    		maxNum = Math.abs(q2.peek());
    	}
    	else if(q2.isEmpty()) {
    		maxNum = Math.abs(q1.peek());
    	}
    	else {
    		maxNum = Math.max(Math.abs(q1.peek()), Math.abs(q2.peek()));
    	}
    	
    	while(!q1.isEmpty()) {
    		int distance = q1.peek();
    		ans += distance*2;
    		for(int i = 0; i < m; i++) {
    			q1.poll();
    		}
    		
    	}
    	
    	while(!q2.isEmpty()) {
    		int distance = q2.peek();
    		ans += -distance*2;
    		for(int i = 0; i < m; i++) {
    			q2.poll();
    		}
    		
    	}
    	
    	ans -= maxNum;
    	
    	System.out.println(ans);
    }

}
    	
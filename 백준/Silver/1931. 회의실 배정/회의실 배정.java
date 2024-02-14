
import java.util.*;
import java.io.*;

class Schedule implements Comparable<Schedule>{
	int startTime;
	int endTime;
	
	Schedule(int t1, int t2) {
		this.startTime = t1;
		this.endTime = t2;
	}

	@Override
	public int compareTo(Schedule o) {
		
		if(this.endTime == o.endTime) {
			return this.startTime - o.startTime;
		}
		
		return this.endTime - o.endTime;
	}
}

public class Main {

	static int n;
	static int result = 0;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(br.readLine());
	    PriorityQueue<Schedule> pq = new PriorityQueue<>();
	    StringTokenizer st;
	    for(int i = 0; i < n; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int t1 = Integer.parseInt(st.nextToken());
	    	int t2 = Integer.parseInt(st.nextToken());
	    	
	    	pq.add(new Schedule(t1, t2));
	    }
	    
	    result = 1;
	    int endTime = pq.poll().endTime;
	    
	    while(!pq.isEmpty()) {
	    	Schedule sc = pq.poll();
	    	if(endTime <= sc.startTime) {
	    		endTime = sc.endTime;
	    		result++;
	    	}
	    }
	    System.out.println(result);
	    
	}

	
}
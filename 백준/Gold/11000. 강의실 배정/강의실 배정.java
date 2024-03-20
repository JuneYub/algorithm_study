
import java.util.*;
import java.io.*;

class Study implements Comparable<Study> {
	int start, end;
	
	Study(int start, int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public int compareTo(Study o) {
		if(this.end == o.end) {
			return this.start - o.start;
		}
		return this.end - o.end;
	}
}

public class Main {

	static int n;

    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	PriorityQueue<Study> pq = new PriorityQueue<>();
    	
    	n = Integer.parseInt(br.readLine());
    	Study[] arr = new Study[n];
    	StringTokenizer st;
    	for(int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		arr[i] = new Study(start, end);
    	}
    	
    	Arrays.sort(arr, new Comparator<Study>() {
    		@Override
    		public int compare(Study o1, Study o2) {
    			return o1.start - o2.start;
    		}
    	});
    	
    	for(int i = 0; i < n; i++) {
    		if(pq.isEmpty()) {
    			pq.add(new Study(arr[i].start, arr[i].end));
    		} else {
        		if(pq.peek().end <= arr[i].start) {
        			pq.poll();
        		}
        		pq.add(new Study(arr[i].start, arr[i].end));
    		}
    	}
    	System.out.println(pq.size());

    	
    } // end of main
}
    	
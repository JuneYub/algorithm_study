import java.util.*;
import java.io.*;

public class Main {
	
	static class Position {
		int time, x;
		
		Position(int x, int time) {
			this.x= x;
			this.time =time;
		}
	}
	
	static int maxPosition = 100_000; 
	static int n, k;
	static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	Queue<Position> q = new ArrayDeque<>();
    	int visited[] = new int[maxPosition+1];
    	
    	Arrays.fill(visited, Integer.MAX_VALUE);
    	q.add(new Position(n, 0));
    	
    	while(!q.isEmpty()) {
    		
    		Position p = q.poll();
    		//System.out.println(p.x + " " + p.time);
    		
    		if(p.x == k) {
    			ans = Math.min(ans, p.time);
    			
    		}
    		
    		if(p.x+1 <= maxPosition && visited[p.x+1] > p.time +1) {
    			q.add(new Position(p.x+1, p.time+1));
    			visited[p.x+1] = p.time+1;
    		}
    		
    		if(p.x-1 >= 0 && visited[p.x-1] > p.time + 1) {
    			q.add(new Position(p.x-1, p.time+1));
    			visited[p.x-1] = p.time+1;
    		}
    		
    		if(p.x*2 <= maxPosition && visited[p.x*2] > p.time) {
    			q.add(new Position(p.x*2, p.time));
    			visited[p.x*2] = p.time;
    		}

    	}
    	System.out.print(ans);
    	
    	
    	
    	
    }
}
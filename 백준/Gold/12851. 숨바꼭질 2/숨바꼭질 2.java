
import java.util.*;
import java.io.*;

class Position {
	int x,t;
	Position(int x, int t) {
		this.x = x;
		this.t = t;
	}
}

public class Main {

	static int ansNum = 0;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	int n = Integer.parseInt(st.nextToken());
    	int k = Integer.parseInt(st.nextToken());
    	
    	Queue<Position> q = new ArrayDeque<>();
    	q.add(new Position(n, 0));
    	
    	boolean visited[] = new boolean[100001];
    	visited[n] = true;
    
    	int time[] = new int[100001];
    	Arrays.fill(time, Integer.MAX_VALUE);
    	time[n] = 0;
    	
    	while(!q.isEmpty()) {
    		Position p = q.poll();
    		if(p.x == k) {
    			
    			if(time[k] == p.t) {
    				ansNum++;
    				continue;
    			}
    			else {
    				if(time[k] > p.t) {
    					ansNum = 1;
    					time[k] = p.t;
    					continue;
    				}
    			}
    		}
    		if(p.t+1 > time[k]) continue;
    		
    		if(isRange(p.x + 1) && visited[p.x+1] && p.t+1 <= time[p.x+1]) {
    			q.add(new Position(p.x+1, p.t+1));
    			time[p.x+1] = p.t+1;
    		}
    		if(isRange(p.x + 1) && !visited[p.x+1]) {
    			q.add(new Position(p.x+1, p.t+1));
    			time[p.x+1] = p.t+1;
    			if(p.x+1 != k)visited[p.x+1] = true;
    		}
    		

    		if(isRange(p.x - 1) && visited[p.x-1] && p.t+1 <= time[p.x-1]) {
    			q.add(new Position(p.x-1, p.t+1));
    			time[p.x-1] = p.t+1;
    			
    		}
    		if(isRange(p.x - 1) && !visited[p.x-1]) {
    			q.add(new Position(p.x-1, p.t+1));
    			time[p.x-1] = p.t+1;
    			if(p.x-1 != k)visited[p.x-1] = true;
    		}
    		

    		if(isRange(p.x *2) && visited[p.x*2] && p.t+1 <= time[p.x*2]) {
    			q.add(new Position(p.x*2, p.t+1));
    			time[p.x*2] = p.t+1;
    		}
    		if(isRange(p.x *2) && !visited[p.x*2]) {
    			q.add(new Position(p.x*2, p.t+1));
    			time[p.x*2] = p.t+1;
    			if(p.x*2 != k)visited[p.x*2] = true;
    		}
    		

    			
    		
    	}
    	
    	System.out.println(time[k]);
    	System.out.println(ansNum);
    	
    } // end of main
    
    public static boolean isRange(int x) {
    	if(x < 0 || x > 100000) {
    		return false;
    	}
    	return true;
    }
    

}
    	
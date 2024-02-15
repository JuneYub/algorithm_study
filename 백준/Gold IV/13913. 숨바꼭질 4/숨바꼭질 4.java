import java.util.*;
import java.io.*;


class Position {
	int x;
	int time;
	
	Position(int x, int time) {
		this.x = x;
		this.time = time;
	}
	
}

public class Main {

	static int n, k;
	static int[] arr;
	static int[] result;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    n = Integer.parseInt(st.nextToken());
	    k = Integer.parseInt(st.nextToken());
	    
	    arr = new int[100001];
	    Arrays.fill(arr, Integer.MAX_VALUE);
	    
	    Queue<Position> q = new ArrayDeque<>();
	    q.add(new Position(n, 0));
	    
	    while(!q.isEmpty()) {
	    	Position p = q.poll();
	    	int newX = p.x;
	    	int curTime = p.time;
	    	
	    	
	    	
	    	if(arr[newX] > curTime && newX <= 100000) {
	    		arr[newX] = curTime;
	    		if(newX *2 <= 100000) {
		    		// 텔포
		    		q.add(new Position(newX*2, curTime+1));
	    		}
	    		if(newX-1 >= 0) {
		    		// 후진
		    		q.add(new Position(newX-1, curTime+1));
	    		}
	    		if(newX +1 <= 100000) {
		    		// 전진
		    		q.add(new Position(newX+1, curTime+1));
	    		}
	    	}
	    }
	    // k는 17, arr[17]은 4
	    result = new int[arr[k]+1];
	    
	    printDfs(k);
	    System.out.println(arr[k]);
	    for(int i = 0; i <= arr[k]; i++) {
	    	System.out.print(result[i] + " ");
	    }
	    
	}
	
	public static void printDfs(int idx) {

		if(result[0] > 0) {
			return;
		}
		result[arr[idx]] = idx; 
		int nextNum = arr[idx]-1;
		if(idx%2 == 0 && arr[idx/2] == nextNum) {
			printDfs(idx/2);
		}
		if(idx +1 < 100001) {
			if(arr[idx+1] == nextNum) printDfs(idx+1);
		}
		
		if(idx-1 >= 0) {
			if(arr[idx-1] == nextNum) printDfs(idx-1);
		}
		
	}
}


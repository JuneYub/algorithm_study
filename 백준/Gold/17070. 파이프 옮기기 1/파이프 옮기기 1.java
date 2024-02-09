
import java.util.*;
import java.io.*;

class Position {
	int x;
	int y;
	
	Position(int y, int x) {
		this.x = x;
		this.y = y;
	}
	
}

class Pipe {
	Position p1;
	Position p2;
	// 0 이면 가로, 1이면 세로, 2이면 대각선
	int direction;
	
	Pipe(Position p1, Position p2, int direction) {
		this.p1 = p1;
		this.p2 = p2;
		this.direction = direction;
	}

}

public class Main {

	static int n, result;
	static int[][] map;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	map = new int[n][n];
    	
    	StringTokenizer st;
    	
    	for(int y = 0; y < n; y++) {
    		st = new StringTokenizer(br.readLine());
    		for(int x = 0; x < n; x++) {
    			map[y][x] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	result = 0;
    	Queue<Pipe> q = new ArrayDeque<>();
    	q.add(new Pipe(new Position(0, 0), new Position(0, 1), 0));
    	
    	while(!q.isEmpty()) {
    		
    		if(map[n-1][n-1] == 1 ) {
    			break;
    		}
    		
    		Pipe pipe = q.poll();
    		int curX = pipe.p2.x;
    		int curY = pipe.p2.y;  		
    		int direction = pipe.direction;
    		if(curX == n-1 && curY == n-1) {
    			result++;
    			continue;
    		}
    		
    		if(direction == 0) {
    			if(가로한칸체크(curY, curX)) {
    				q.add(new Pipe(new Position(curY, curX), new Position(curY, curX+1), 0));
    			}
    			
    			if(대각선체크(curY, curX)) {
    				q.add(new Pipe(new Position(curY, curX), new Position(curY+1,curX+1), 2));
    			}
    		} 
    		
    		if(direction == 1) {
    			if(세로한칸체크(curY, curX)) {
    				q.add(new Pipe(new Position(curY, curX), new Position(curY+1, curX), 1));
    			}
    			
    			if(대각선체크(curY, curX)) {
    				q.add(new Pipe(new Position(curY, curX), new Position(curY+1,curX+1), 2));
    			}
    		} 
    		
    		if(direction == 2) {
    			if(가로한칸체크(curY, curX)) {
    				q.add(new Pipe(new Position(curY, curX), new Position(curY, curX+1), 0));
    			}
    			
    			if(세로한칸체크(curY, curX)) {
    				q.add(new Pipe(new Position(curY, curX), new Position(curY+1, curX), 1));
    			}
    			
    			if(대각선체크(curY, curX)) {
    				q.add(new Pipe(new Position(curY, curX), new Position(curY+1,curX+1), 2));
    			}
    		} 
    		
    	} // end of while
    	
    	System.out.println(result);
    	
    	
    }
    
    public static boolean isRange(int y, int x) {
    	if( y < 0 || y >= n || x < 0 || x >= n) return false;
    	return true;
    }
    
    public static boolean 가로한칸체크(int y, int x) {
    	if(isRange(y, x+1) && map[y][x+1] == 0) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean 대각선체크(int y, int x) {
    	int newY = y+1;
    	int newX = x+1;
    	
    	if(isRange(newY, newX) && isRange(y, x+1) && isRange(y+1, x)) {
    		if(map[y][x+1] == 0 && map[newY][newX] == 0 && map[y+1][x] == 0) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public static boolean 세로한칸체크(int y, int x) {
    	if(isRange(y+1, x) && map[y+1][x] == 0) {
    		return true;
    	}
    	return false;
    }
    



}
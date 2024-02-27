
import java.util.*;
import java.io.*;
class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

	static int r, c;
	static char[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] swans = new int[2][2];
	static Queue<Position> meltQueue = new ArrayDeque<>();
	static Queue<Position> swanQueue = new ArrayDeque<>();
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    int answer = 0;
	    
	    r = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    
	    map = new char[r][c];
	    boolean[][] swanVisited = new boolean[r][c];
	    
	    int idx = 0; 
	    for(int y = 0; y < r; y++) {
	        String str = br.readLine();
	        for(int x = 0; x < c; x++) {
	            map[y][x] = str.charAt(x);
	            
	            if(map[y][x] == 'L') {
	                swans[idx][0] = y;
	                swans[idx][1] = x;
	                idx++;
	            }
	            if(map[y][x] == '.' || map[y][x] == 'L' ) {
	                meltQueue.add(new Position(x, y));
	            }
	        }
	    }
	    swanQueue.add(new Position(swans[0][1], swans[0][0]));
	    swanVisited[swans[0][0]][swans[0][1]] = true;
	    
	    while(!swansCanMeet(swanVisited)) {
	        melt();
	        answer++;
	    }
	    
	    System.out.println(answer);
	
	} // end of main

	public static boolean swansCanMeet(boolean[][] swanVisited) {
	    Queue<Position> nextQueue = new ArrayDeque<>();
	    
	    while(!swanQueue.isEmpty()) {
	        int y = swanQueue.peek().y;
	        int x = swanQueue.peek().x;
	        swanQueue.poll();
	        
	        for(int i = 0; i < 4; i++) {
	            int nx = x + dx[i];
	            int ny = y + dy[i];
	            
	            if(isRange(nx, ny) && !swanVisited[ny][nx]) {
	                swanVisited[ny][nx] = true;               
	                if(ny == swans[1][0] && nx == swans[1][1]) {
	                    return true;
	                }
	                
	                else if(map[ny][nx] == 'X') {
	                    nextQueue.add(new Position(nx, ny));
	                } else {
	                    swanQueue.add(new Position(nx, ny));
	                }
	            }
	        }
	    }
	    swanQueue = nextQueue;
	    return false;
	}

	private static void melt() {
	    
	    int size = meltQueue.size();
	    
	    for(int i = 0; i < size; i++) {
	        int x = meltQueue.peek().x;
	        int y = meltQueue.peek().y;
	        meltQueue.poll();

	        
	        for(int k = 0; k < 4; k++) {
	            int newX = x+ dx[k];
	            int newY = y+ dy[k];
	            
	            if(isRange(newX, newY)) {
	                if(map[newY][newX] == 'X') {
	                	map[newY][newX] = '.';
	                    meltQueue.add(new Position(newX, newY));
	                }
	            }
	        }
	    }
	    
	}

	public static boolean isRange(int x, int y) {
	    if( x< 0 || x >= c || y < 0 || y>= r) {
	        return false;
	    }
	    return true;
	}
   
}


import java.util.*;
import java.io.*;

class Position {
	int x, y;
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int m, n;
	static int[][] map;
	static int changeTomato;
	static int totalTomato;
	static boolean[][] visited;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {-1,0,1,0};
	static Queue<Position> q = new ArrayDeque<>();
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	m = Integer.parseInt(st.nextToken());
    	n = Integer.parseInt(st.nextToken());
    	map = new int[n][m];
    	visited = new boolean[n][m];
    	for(int y = 0; y < n; y++) {
    		st = new StringTokenizer(br.readLine());
    		for(int x = 0; x< m; x++) {
    			map[y][x] = Integer.parseInt(st.nextToken());
    			if(map[y][x] == 0) {
    				totalTomato++;
    			}
    			if(map[y][x] == 1) {
    				q.add(new Position(x, y));
    			}
    		}
    	}
    	int day = 0;
    	
    	while(true) {
    		
    		int tomatoNum = q.size();
    		for(int idx = 0; idx < tomatoNum; idx++) {
    			Position p = q.poll();
    			
    			for(int i = 0; i < 4; i++) {
    				int newX = p.x + dx[i];
    				int newY = p.y + dy[i];
    				
    				if(isRange(newX, newY) && !visited[newY][newX] && map[newY][newX] == 0) {
    					q.add(new Position(newX, newY));
    					visited[newY][newX] = true;
    					map[newY][newX] = 1;
    				}
    			}
    		}
    		totalTomato -= q.size();
    		if(tomatoNum == 0) {
    			if(totalTomato > 0) {
    				System.out.println(-1);
    			} else {
    				System.out.println(day-1);
    			}
    			break;
    		}
    		day++;
    	}
    	

    }
    
    
    public static boolean isRange(int x, int y) {
    	if(x < 0 || x >= m || y < 0 || y >= n) {
    		return false;
    	}
    	return true;
    }
}
    	
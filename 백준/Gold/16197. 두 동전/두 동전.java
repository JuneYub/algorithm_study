
import java.util.*;
import java.io.*;

class Position {
	int x, y, chance;
	int x2, y2;
	
	Position(int x, int y, int x2, int y2, int chance) {
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.chance = chance;
	}
}

class Coin {
	int x, y;
	
	Coin(int x, int y) {
		this.x = x;
		this.y = y;
	}
}


public class Main {

	static int n, m;
	static char[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int minAns = Integer.MAX_VALUE;
	
	
	static Coin[] coins = new Coin[2];
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	m = Integer.parseInt(st.nextToken());
    	map = new char[n][m];
    	
    	int idx = 0;
    	for(int y = 0; y < n; y++) {
    		String str = br.readLine();
    		for(int x = 0; x < m; x++) {
    			map[y][x] = str.charAt(x);
    			
    			if(map[y][x] == 'o') {
    				coins[idx++] = new Coin(x, y);
    			}
    			
    		}
    	}

    	bfs();
    	
    	if(minAns == Integer.MAX_VALUE) minAns = -1;
    	System.out.println(minAns);
    	
    } // end of main
    
    public static void bfs() {
    	
    	Queue<Position> q = new ArrayDeque<>();
    	q.add(new Position(coins[0].x, coins[0].y, coins[1].x, coins[1].y, 0));
    	
    	boolean visited[][][][] = new boolean[n][m][n][m];
    	visited[coins[0].y][coins[0].x][coins[1].y][coins[1].x] = true;
    	
    	
    	while(!q.isEmpty()) {
    		Position p = q.poll();
    		if(p.chance >= 10) continue;
    	
    		for(int i = 0; i < 4; i++) {
    			int newX = p.x + dx[i];
    			int newY = p.y + dy[i];
    			
    			int newX2 = p.x2 + dx[i];
    			int newY2 = p.y2 + dy[i];
    			
        		if((isRange(newX, newY) && !isRange(newX2, newY2)) || (!isRange(newX, newY) && isRange(newX2, newY2))  ) {
        			minAns = Math.min(minAns, p.chance+1);
        		}
    			
        		if(isRange(newX, newY) && isRange(newX2, newY2)) {
        			if(map[newY][newX] == '#') {
        				newY = p.y;
        				newX = p.x;
        			}
        			if(map[newY2][newX2] == '#') {
        				newY2 = p.y;
        				newX2 = p.x;
        			}
        			if(!visited[newY][newX][newY2][newX2]) {
        				visited[newY][newX][newY2][newX2] = true;
        				q.add(new Position(newX, newY, newX2, newY2, p.chance+1));
        			}
        		}
        	
    			
    		}
    		
    		
    		
    	}
    	
    	
    }

    public static boolean isRange(int x, int y) {
    	if(x < 0 || x >= m || y < 0 || y >= n) {
    		return false;
    	}
    	return true;
    }

}
    	
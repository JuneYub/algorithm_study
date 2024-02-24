
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

	static int n, l, r;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static boolean[][] visited;
	static boolean dayContinue = true;
	
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        
        map = new int[n][n];
        for(int y = 0; y < n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x = 0; x < n; x++) {
        		map[y][x] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int day = 0;
        while(dayContinue) {
        	dayContinue = false;
        	visited = new boolean[n][n];
            for(int y = 0; y < n; y++) {
            	for(int x = 0; x < n; x++) {
            		if(!visited[y][x]) {
            			bfs(x, y);
            		}
            	}
            }
        	
        	if(dayContinue) {
        		++day;
        	}	
        }
        
        System.out.println(day);
	} // end of main

    public static boolean isRange(int x, int y) {
    	if(x < 0 || x >= n || y < 0 || y >= n) {
    		return false;
    	}
    	return true;
    }
    
    public static void bfs(int x, int y) {
    	int isContinue = 0;
    	for(int i = 0; i < 4; i++) {
    		int newX = x + dx[i];
    		int newY = y + dy[i];

    		if(isRange(newX, newY) && !visited[newY][newX] && Math.abs(map[y][x] - map[newY][newX]) >= l && Math.abs(map[y][x] - map[newY][newX]) <= r) {
    			
    			isContinue = 1;
    			break;
    		}
    	}
    	
    	// 주변에 인구수 차이가 나는 경우에만 bfs 진행
    	if(isContinue == 1) {
    		
    		// 일단 인구이동을 한번이라도 하면 하루가 지난거
    		dayContinue = true;
    		
    		Queue<Position> q = new ArrayDeque<>();
    		List<Position> positions = new ArrayList<>();
    		int toatalPopulation = 0;
    		q.add(new Position(x, y));
    		visited[y][x] = true;
    		
    		while(!q.isEmpty()) {
    			Position p = q.poll();
    			positions.add(p);
    			toatalPopulation += map[p.y][p.x];
    			
    			for(int i = 0; i < 4; i++) {
    				int newX = p.x+dx[i];
    				int newY = p.y+dy[i];
    				
    	    		if(isRange(newX, newY) && !visited[newY][newX] && Math.abs(map[p.y][p.x] - map[newY][newX]) >= l && Math.abs(map[p.y][p.x] - map[newY][newX]) <= r) {
    	    			visited[newY][newX] = true;
    	    			q.add(new Position(newX, newY));
    	    		}
    			}
    		}
    		if(positions.size() > 1) {
        		int avgPopulation = toatalPopulation / positions.size(); 
        		
        		for(int i = 0; i < positions.size(); i++) {
        			map[positions.get(i).y][positions.get(i).x] = avgPopulation; 
        		}
    		}

    		
    	}
    }
}
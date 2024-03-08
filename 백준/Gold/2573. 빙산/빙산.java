import java.io.*;
import java.util.*;

class Position {
	
	int x, y, meltCnt;
	Position(int x, int y, int meltCnt) {
		this.x = x;
		this.y = y;
		this.meltCnt = meltCnt;
	}
}
public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int meltBing;
    static Queue<Position> q = new ArrayDeque<>(); 
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        
        for(int y = 0; y < n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x = 0; x < m; x++) {
        		map[y][x] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int day = 0;
        while(true) {
        	meltBing = 0;
        	int bingCnt = 0;
        	visited = new boolean[n][m];
            for(int y = 0; y < n; y++) {
            	for(int x = 0; x< m; x++) {
            		if(map[y][x] > 0 && !visited[y][x]) {
            			
            			dfs(x, y);
            			bingCnt++;
            			
            			while(!q.isEmpty()) {
            				int delX = q.peek().x;
            				int delY = q.peek().y;
            				int meltCnt = q.peek().meltCnt;
            				q.poll();
            				map[delY][delX] = map[delY][delX]-meltCnt < 0 ? 0 : map[delY][delX]-meltCnt;
            				//System.out.println(map[delY][delX]);
            			}
            			
            		}
            	}
            }
            
            
            if(bingCnt >= 2) {
            	System.out.println(day);
            	break;
            }
            
            if(meltBing == 0) {
            	System.out.println(0);
            	break;
            }
            day++;
        }
    }
    
    public static void dfs(int x, int y) {
    	int meltCnt = 0;
    	meltBing++;
    	visited[y][x] = true;
    	
    	for(int i = 0; i < 4; i++) {
    		int newX = x + dx[i];
    		int newY = y + dy[i];
    		
    		if(isRange(newX, newY) && map[newY][newX] > 0 && !visited[newY][newX]) {
    			dfs(newX, newY);
    		}
    		
    		if(isRange(newX, newY) && map[newY][newX] == 0) {
    			meltCnt++;
    		}
    		
    	}
    	q.add(new Position(x, y, meltCnt));
    }
    
    public static boolean isRange(int x, int y) {
    	if(x < 0 || x >= m || y < 0 || y >= n) {
    		return false;
    	}
    	return true;
    }
}

import java.util.*;
import java.io.*;
class Position {
	int x, y;
	Position (int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int r, c, result;
	static char[][] map;
	static boolean flag;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    r = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    
	    map = new char[r][c];
	    
	    for(int y = 0; y < r; y++) {
	    	String str = br.readLine();
	    	for(int x = 0 ; x < c; x++) {
	    		map[y][x] = str.charAt(x);
	    	}
	    }
	    
	    for(int y = 0; y < r; y++) {
	    	flag = true;
	    	dfs(new Position(0, y));
	    }
	    
	    
	    System.out.println(result);
    
	} // end of public static void main(String args[]) throws Exception
	
	public static void dfs(Position p) {
		map[p.y][p.x] = 'x';
		if(p.x == c-1) {
			flag = false;
			result++;
			return;
		}
		
		int[] dy = {-1, 0, 1};
		
		for(int i = 0; i < 3; i++) {
			int newY = p.y+dy[i];
			int newX = p.x + 1;
			if(moveAbl(newX, newY)) {
				dfs(new Position(newX, newY));
			}
		}
	}
	
	public static boolean moveAbl(int x, int y) {
		if( isRange(x, y) && flag && map[y][x] != 'x') {
			return true;
		}
		return false;
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= c || y < 0 || y >= r) {
			return false;
		}
		return true;
	}

}
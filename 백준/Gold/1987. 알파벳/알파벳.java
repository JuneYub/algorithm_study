
import java.util.*;
import java.io.*;

class Position {
	int y;
	int x;
	
	Position(int y, int x ){
		this.y = y;
		this.x = x;
	}
}

public class Main {

	static int r, c;
	static int resultCnt = 0;
	static char[][] map;
	static int[] dx = { 0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    r = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    
	    map = new char[r][c];
	    
	    for(int y = 0; y < r; y++) {
	    	String str = br.readLine();
	    	for(int x = 0; x < c; x++) {
	    		map[y][x] = str.charAt(x);
	    	}
	    }
	    
	    List<Character> list = new ArrayList<>();
	    list.add(map[0][0]);
	    dfs(0, 0, 1, new ArrayList<>(list));
	    System.out.println(resultCnt);
	    
	} // end of main
	
	public static void dfs(int y, int x, int cnt, List<Character> list) {
		
		if(resultCnt < cnt) {
			resultCnt = cnt;
		}
		
		for(int i = 0; i < 4; i++) {
			int newX = dx[i] + x;
			int newY = dy[i] + y;
			
			if(isRange(newY, newX)) {
				if(!list.contains(map[newY][newX])) {
					list.add(map[newY][newX]);
					dfs(newY, newX, cnt+1, new ArrayList<>(list));
					list.remove(list.size()-1);
				}
			}
		}
		
	}

	
		
	public static boolean isRange(int y, int x) {
		if(y < 0 || y >= r || x < 0 || x >= c) {
			return false;
		}
		return true;
	}
}






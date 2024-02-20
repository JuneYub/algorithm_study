
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
	static int[][] map;
	static boolean[] visited = new boolean[26]; // 알파벳 중복체크 배열
	static int[] dx = { 0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    r = Integer.parseInt(st.nextToken());
	    c = Integer.parseInt(st.nextToken());
	    
	    map = new int[r][c];
	    
	    for(int y = 0; y < r; y++) {
	    	String str = br.readLine();
	    	for(int x = 0; x < c; x++) {
	    		map[y][x] = str.charAt(x) - 'A'; // A 가 들어오면 0 B가 들어오면 1
	    	}
	    }
	    
	    visited[map[0][0]] = true;
	    dfs(0, 0, 1);
	    
	    System.out.println(resultCnt);
	    
	} // end of main
	
	
	/**
	 * 
	 * @param y 좌표
	 * @param x
	 * @param cnt 지나온 횟수
	 * @param list // 중복체크하는 list를 파라미터로 넘겨줌
	 */
	public static void dfs(int y, int x, int cnt) {
		
		if(resultCnt < cnt) {
			resultCnt = cnt;
		}
		
		for(int i = 0; i < 4; i++) {
			int newX = dx[i] + x;
			int newY = dy[i] + y;
			
			if(isRange(newY, newX)) {
				if(!visited[map[newY][newX]]) {
					visited[map[newY][newX]] = true;
					dfs(newY, newX, cnt+1);
					visited[map[newY][newX]] = false;
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






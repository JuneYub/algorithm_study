
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

	static int r,c;
	static int[][] map;
	static boolean[][] visited;
	static int beforeResult;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new int[r][c];
		
		for(int y = 0; y < r; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < c; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		while(true) {
			
			int result = 0;
			
			visited = new boolean[r][c];
			
			Queue<Position> q = new ArrayDeque<Position>();
			q.add(new Position(0, 0));
			visited[0][0] = true;
			
			while(!q.isEmpty()) {
				Position p = q.poll();
				
				if(map[p.y][p.x] == 0) {
					for(int i = 0; i < 4; i++) {
						int newX = p.x + dx[i];
						int newY = p.y + dy[i];
						
						if(isRange(newX, newY) && !visited[newY][newX]) {
							visited[newY][newX] = true;
							q.add(new Position(newX, newY));
						}
					}
				}
				
				if(map[p.y][p.x] == 1) {
					map[p.y][p.x] = 0;
					result++;
				}
				
			} // end of while
			
			if(result == 0) {
				System.out.println(time);
				System.out.println(beforeResult);
				return;
			} else {
				time++;
				beforeResult = result;
			}
		} // end of while
		
		
		
	} // end of main
	
	public static boolean isRange(int x, int y) {
		if( x < 0 || x >= c || y < 0 || y >= r) {
			return false;
		}
		return true;
	}
}
import java.util.*;
import java.io.*;

class Position implements Comparable<Position> {
	int x, y, time;
	
	Position(int x, int y, int time) {
		this.x = x;
		this.y = y;
		this.time = time;
	}
	
	public int compareTo(Position o) {
		return this.time - o.time;
	}
}

public class Solution {
	
	static int n;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int textCase = Integer.parseInt(st.nextToken());
		for(int tc = 1; tc <= textCase;  tc++) {
			
			st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			map = new int[n][n];
			PriorityQueue<Position> q = new PriorityQueue<>();
			boolean[][] visited = new boolean[n][n];
			
			for(int y = 0; y < n; y++) {
				String str = br.readLine();
				for(int x = 0; x< n; x++) {
					map[y][x] = str.charAt(x) -'0';
				}
			}
			
			q.add(new Position(0,0,map[0][0]));
			visited[0][0] = true;
			while(!q.isEmpty()) {
				Position p = q.poll();
				
				if(p.x == n-1 && p.y == n-1) {
					System.out.println("#" + tc +" "+ p.time);
					break;
				}
				
				for(int i = 0; i < 4; i++) {
					int newX = p.x + dx[i];
					int newY = p.y + dy[i];
					
					if(isRange(newX, newY) && !visited[newY][newX]) {
						visited[newY][newX] = true;
						q.add(new Position(newX, newY, p.time+map[newY][newX]));
					}
				}
			}

		}
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
	

	
}

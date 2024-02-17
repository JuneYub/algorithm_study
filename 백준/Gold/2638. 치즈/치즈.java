
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

	static int n, m;
	static int[][] map;
	static int[][] orgMap;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		orgMap = new int[n][m];
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < m; x++) {
				orgMap[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(true) {
			initMap();
			visited = new boolean[n][m];
			Queue<Position> q = new ArrayDeque<>();
			
			q.add(new Position(0, 0));
			visited[0][0] = true;
			
			List<Position> cheeseList = new ArrayList<>();
			
			while(!q.isEmpty()) {
				Position p = q.poll();
				
				if(map[p.y][p.x] == 1) {
					int tmpCnt = 0;
					for(int i = 0; i < 4; i++) {
						int newX = dx[i] + p.x;
						int newY = dy[i] + p.y;
						
						if(isRange(newX, newY) && map[newY][newX] == -1) {
							tmpCnt++;
						}
					}
					if(tmpCnt >= 2) {
						cheeseList.add(new Position(p.x, p.y));
					}
					continue;
				}
				
				
				for(int i = 0; i < 4; i++) {
					int newX = dx[i] + p.x;
					int newY = dy[i] + p.y;
					
					if(isRange(newX, newY) && map[newY][newX] != 0 && !visited[newY][newX]) {
						
						visited[newY][newX] = true;
						q.add(new Position(newX, newY));
						
					}
				}
			} // end of while
			
			if(cheeseList.size() == 0) {
				System.out.println(time);
				break;
			} else {
				for(int i = 0; i < cheeseList.size(); i++) {
					orgMap[cheeseList.get(i).y][cheeseList.get(i).x] = 0;
				}
				time++;
			}
			
			
		}
	}
	
	public static void initMap() {
		boolean[][] tmpVisited = new boolean[n][m];
		Queue<Position> q = new ArrayDeque<>();
		map = new int[n][m];
		
		for(int y = 0; y < n; y++) {
			map[y] = orgMap[y].clone();
		}
		
		q.add(new Position(0, 0));
		tmpVisited[0][0] = true;
		
		while(!q.isEmpty()) {
			Position p = q.poll();
			
			for(int i = 0; i < 4; i++) {
				int newX = dx[i] + p.x;
				int newY = dy[i] + p.y;
				
				if(isRange(newX, newY) && map[newY][newX] == 0 && !tmpVisited[newY][newX]) {
					map[newY][newX] = -1;
					tmpVisited[newY][newX] = true;
					q.add(new Position(newX, newY));
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
import java.util.*;
import java.io.*;



class Position implements Comparable<Position> {
	int x, y;
	int cnt;
	int isBroken;
	
	Position(int x, int y, int cnt, int isBroken) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.isBroken = isBroken;
	}
	
	public int compareTo(Position p) {
		return this.cnt - p.cnt;
	}
}

public class Main {
	
	static int n,m;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		int[][] copyMap = new int[n][m];
		
		for(int y = 0; y < n; y++) {
			String str = br.readLine();
			for(int x = 0; x < m; x++) {
				map[y][x] = str.charAt(x) - '0';
			}
		}
		
		int result = bfs();
		if(result == Integer.MAX_VALUE) result = -1;
		System.out.println(result);
		
	}
	
	public static int bfs() {
		boolean[][][] visited = new boolean[n][m][2];
		int cnt = Integer.MAX_VALUE;
		
		Queue<Position> pq = new ArrayDeque<>();
		visited[0][0][0] = true;
		pq.add(new Position(0,0,1,0));
		
		
		while(!pq.isEmpty()) {
			Position p = pq.poll();
			
			// 경로 도착
			if(p.y == n-1 && p.x == m - 1) {
				cnt = p.cnt;
				break;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				// 벽을 부순 상태이거나 안부순 상태이거나 => 벽을 안부술 예정
				if(isRange(nx, ny) && !visited[ny][nx][p.isBroken] && map[ny][nx] == 0) {
			
					pq.add(new Position(nx, ny, p.cnt + 1, p.isBroken));
					visited[ny][nx][p.isBroken] = true;
				}
				
				// 벽을 안부순 상태일 경우 = >벽을 부수고 이동 예정
				if(isRange(nx, ny) && !visited[ny][nx][p.isBroken] && map[ny][nx] == 1 && p.isBroken == 0) {
					
					pq.add(new Position(nx, ny, p.cnt + 1, 1));
					visited[ny][nx][1] = true;
				}
				
			}
			
		}
		return cnt;
	}
	
	public static boolean isRange(int x, int y) {
		if(y < 0 || y >= n || x < 0 || x >= m) return false;
		return true;
	}
}

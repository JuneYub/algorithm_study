import java.io.*;
import java.util.*;


class Position {
	int x, y, cnt, wall;
	Position(int x, int y, int cnt, int wall) {
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.wall = wall;
	}
}

public class Main {
	
	static int n, m, k;
	static int[][] map;
	static int visited[][][];
	static Queue<Position> q = new ArrayDeque<>();
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		visited = new int[n][m][k+1];
		
		for(int y = 0; y < n; y++) {
			String str = br.readLine();
			for(int x = 0; x < m; x++) {
				map[y][x] = str.charAt(x) - '0';
			}
		}
		
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				Arrays.fill(visited[y][x], Integer.MAX_VALUE);
			}
		}
		
		
		
		int ans = Integer.MAX_VALUE;
		q.add(new Position(0, 0, 1, 0));
		visited[0][0][0] = 1;
		
		while(!q.isEmpty()) {
			Position p = q.poll();
			// 도착
			if(p.y == n-1 && p.x == m-1) {
				ans = Math.min(ans, p.cnt);
				continue;
			}
			
			for(int i = 0; i < 4; i++) {
				int newX = p.x + dx[i];
				int newY = p.y + dy[i];
				if(!isRange(newX, newY)) continue;
				
				if(map[newY][newX] == 1) {
					// 벽 부수는 횟수가 남아있으며 벽을 부수고 이동했을 때 이동한 거리가 기록된 거리보다 짧아야한다.
					if(p.wall < k && p.cnt+1 < visited[newY][newX][p.wall+1]) {
						q.add(new Position(newX, newY, p.cnt+1, p.wall + 1));
						visited[newY][newX][p.wall+1] = p.cnt+1;
					}
				} else {
					if(p.cnt+1 < visited[newY][newX][p.wall]) {
						q.add(new Position(newX, newY, p.cnt+1, p.wall));
						visited[newY][newX][p.wall] = p.cnt+1;
					}
				}
			}			
		}
		
		if(ans == Integer.MAX_VALUE) {
			System.out.print(-1);
		} else {
			System.out.print(ans);
		}
		
		
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= m || y < 0 || y>= n) return false;
		return true;
	}
	

}

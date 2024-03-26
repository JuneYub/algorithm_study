import java.util.*;
import java.io.*;

class Position implements Comparable<Position>{
	int x, y, money;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	Position(int x, int y, int money) {
		this.x = x;
		this.y = y;
		this.money = money;
	}
	
	// 우선순위 큐에서 소모된 비용의 오름차순으로 정렬될 수 있게 한다.
	@Override
	public int compareTo(Position o) {
		return money -o.money;
	}
}

public class Main {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int problemCnt = 1;
		while(true) {
			n = Integer.parseInt(br.readLine());
			
			if(n == 0) break;
			
			map = new int[n][n];
			visited = new boolean[n][n];
			
			for(int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < n; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			
			PriorityQueue<Position> q = new PriorityQueue<>();
			q.add(new Position(0, 0, map[0][0]));
			visited[0][0] = true;
			
			while(!q.isEmpty()) {
				
				Position p = q.poll();
				
				if(p.x == n-1 && p.y == n-1) {
					System.out.println("Problem "+ problemCnt+": " + p.money);
					break;
				}
				
				for(int i = 0; i < 4; i++) {
					int newX = p.x+dx[i];
					int newY = p.y+dy[i];
					
					if(isRange(newX, newY) && !visited[newY][newX]) {
						visited[newY][newX] = true;
						q.add(new Position(newX, newY, p.money+ map[newY][newX]));
					}
				}
			}
			
			problemCnt++;
		}
		
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= n) return false;
		return true;
	}
	
}

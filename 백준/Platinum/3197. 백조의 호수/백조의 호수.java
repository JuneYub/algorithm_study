import java.util.*;
import java.io.*;

class Position {
    int x;
    int y;

    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

	static int r, c;
	static char[][] map;
	static int[][] swans;
	static Queue<Position> meltQueue = new ArrayDeque<>();
	static Queue<Position> swanRoadQueue = new ArrayDeque<>();
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		map = new char[r][c];
		visited = new boolean[r][c];
		swans = new int[2][2];
		
		int swanCnt = 0;
		for(int y = 0; y < r; y++) {
			String str = br.readLine();
			for(int x = 0; x< c; x++) {
				map[y][x] = str.charAt(x);
				
				if(map[y][x] == 'L') {
					swans[swanCnt][0] = y;
					swans[swanCnt++][1] = x;
				}
				
				if(map[y][x] == '.' || map[y][x] == 'L') {
					meltQueue.add(new Position(x, y));
				}
			}
		}
		
		swanRoadQueue.add(new Position(swans[0][1], swans[0][0]));
		visited[swans[0][0]][swans[0][1]] = true;
		
		int ans = 0;
		while(!swanMeet()) {
			melt();
			ans++;
		}
		System.out.println(ans);
		
		
		
		
	}
	
	public static void melt() {
		
		int size = meltQueue.size();
		
		for(int i = 0; i < size; i++) {
			
			int x = meltQueue.peek().x;
			int y = meltQueue.peek().y;
			meltQueue.poll();
			
			for(int k = 0; k < 4; k++) {
				int newX = x + dx[k];
				int newY = y + dy[k];
				
				if(isRange(newX, newY)) {
					
					if(map[newY][newX] == 'X') {
						map[newY][newX] = '.';
						meltQueue.add(new Position(newX, newY));
					}
				}
			}
		}
	}
	
	public static boolean swanMeet() {
		
		Queue<Position> nextQueue = new ArrayDeque<>();
		
		while(!swanRoadQueue.isEmpty()) {
			int x = swanRoadQueue.peek().x;
			int y = swanRoadQueue.peek().y;
			swanRoadQueue.poll();
			
			for(int i = 0; i < 4; i++) {
				int newX = dx[i] + x;
				int newY = dy[i] + y;
				
				if(isRange(newX, newY) && !visited[newY][newX] ) {
					visited[newY][newX] = true;
					
					if(newX == swans[1][1] && newY == swans[1][0]) {
						return true;
					}
					
					else if(map[newY][newX] == 'X') {
						nextQueue.add(new Position(newX, newY));
					}
					
					else {
						swanRoadQueue.add(new Position(newX, newY));
					}
				}
			}
		}
		swanRoadQueue = nextQueue;
		return false;
	}
	
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= c || y < 0 || y >= r) {
			return false;
		}
		return true;
	}
}

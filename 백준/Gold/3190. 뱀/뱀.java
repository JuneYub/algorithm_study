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

class Turn {
	int time;
	int dir;
	
	Turn(int time, int dir) {
		this.time = time;
		this.dir = dir;
	}
}

public class Main {

	static int n,k,l;
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int snakeDir = 1;
	static int snakeX = 0;
	static int snakeY = 0;
	static Queue<Turn> q = new ArrayDeque<>();
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		map = new int[n][n];
		
		StringTokenizer st;
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			map[y][x] = 1;
		}
		
		l = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			char c = st.nextToken().charAt(0);
			
			int dir = 0;
			
			if(c == 'L') {
				// 왼쪽
				dir = 0;
			} else if( c == 'D') {
				// 오른쪽
				dir = 1;
			}
			
			q.add(new Turn(x, dir));
			
			
		}
		
		LinkedList<Position> snake = new LinkedList<>();
		snake.add(new Position(0, 0));
		map[0][0] = 2;
		
		int turnIdx = 1;
		
		int time = 0;
		while(true) {
			time++;
			
			snakeX = snakeX + dx[snakeDir];
			snakeY = snakeY + dy[snakeDir];
			Position position = new Position(snakeX, snakeY);
			
			boolean isFinish = false;
			// 자기 몸통이랑 부딫치는지 체크
			if(isRange(position.x, position.y) && map[position.y][position.x] == 2) {
				isFinish = true;
			}
			
			// 맵 바깥으로 나가는지 체크
			if(!isRange(position.x, position.y)) {
				isFinish = true;
			}
			
			if(isFinish) {
				System.out.println(time);
				break;
			}
			
			snake.add(position);
			
			if(map[position.y][position.x] != 1) {
				Position tail = snake.peek();
				map[tail.y][tail.x] = 0;
				snake.poll();
			} else if(map[position.y][position.x] == 1) {
				map[position.y][position.x] = 0;
			}
			map[position.y][position.x] = 2;
			
			while(!q.isEmpty()) {
				if(q.peek().time == time) {
					Turn turn  = q.poll();
					if(turn.dir == 0) {
						snakeDir = (4 + snakeDir - 1) % 4; 
					} else if(turn.dir == 1) {
						snakeDir = (snakeDir + 1) % 4;
					}
				} else {
					break;
				}
			}
	
		}

	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= n) return false;
		return true;
	}

}

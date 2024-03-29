
import java.util.*;
import java.io.*;

class Position {
	int x,y,boomWidht;
	
	Position(int x, int y, int boomWidth) {
		this.x = x;
		this.y = y;
		this.boomWidht = boomWidth;
	}
}
public class Solution
{
	static int[][] map;
	static int[][] copyMap;
	static int n, w, h;
	static int[] widthShoot;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	static int ans;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			ans = Integer.MAX_VALUE;
			
			map = new int[h][w];
			for(int y = 0; y < h; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < w; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			widthShoot = new int[n];
			shoot(0);
			
			System.out.println("#"+tc + " " + ans);
		}
		
		
	}
	
	// 쏘는 자리를 정하는 순열
	public static void shoot(int depth) {
		
		// n번 쏠 자리가 정해지면 점수를 체크한다.
		if(depth == n) {
			
			copyMap = new int[h][w];
			// 카피맵 초기화
			for(int y = 0; y < h; y++) {
				copyMap[y] = map[y].clone();
			}
			
			checkPoint();
			return;
		}
		
		
		// 맨 왼쪽부터 오른쪽까지 다 쏴본다.
		for(int i = 0; i < w; i++) {
			widthShoot[depth] = i;
			shoot(depth+1);
		}
	}
	
	// 쏘는 위치가 정해진 후에 점수를 구하는 메서드
	public static void checkPoint() {
		
		int point = 0;
		
		for(int i = 0; i < n; i++) {
			// 쏘는 x 위치 
			int firstBoomX = widthShoot[i];
			int firstBoomY = findFirstBoomY(firstBoomX);
			
			if(firstBoomY == -1) continue;
			
			Queue<Position> q = new ArrayDeque<>();			
			q.add(new Position(firstBoomX, firstBoomY, copyMap[firstBoomY][firstBoomX]));
			
			// ==============================================================================
			while(!q.isEmpty()) {
				Position p = q.poll();
				
				copyMap[p.y][p.x] = 0;
				//System.out.println(p.y + " " + p.x);
				
				// 가로 범위 큐에 넣기
				for(int newX = p.x - (p.boomWidht-1); newX <= p.x+(p.boomWidht-1); newX++) {
					if(isRange(newX, p.y) && copyMap[p.y][newX] != 0) {
						if(copyMap[p.y][newX] != 1) {
							q.add(new Position(newX, p.y, copyMap[p.y][newX]));
						}
						
						copyMap[p.y][newX] = 0;
					}
				}
				
				// 세로 범위 큐에 넣기
				for(int newY = p.y - (p.boomWidht-1);  newY <= p.y + (p.boomWidht-1); newY++) {
					if(isRange(p.x, newY) && copyMap[newY][p.x] != 0) {
						if(copyMap[newY][p.x] != 1) {
							q.add(new Position(p.x, newY, copyMap[newY][p.x]));
						}
						
						copyMap[newY][p.x] = 0;
					}
				}
				

			}
			
			for(int y= 1; y < h; y++) {
				for(int x = 0; x < w; x++) {
					if(copyMap[y][x] == 0 && copyMap[y-1][x] != 0) {
						// 해당 위치 상단에 있는 블록 한칸씩 내리기
						downBlock(x, y);
					}
				}
			}

			
		}
		

		
		// 점수 계산
		for(int y = 0; y < h; y++) {
			for(int x = 0; x< w; x++) {
				if(copyMap[y][x] != 0) {
					point++;
				}
			}
		}
		ans = Math.min(ans, point);
	}
	
	// 해당 지점부터 상단 블록 한칸씩 내리기
	private static void downBlock(int newX, int y) {
		for(int dy = y; dy > 0; dy--) {
			copyMap[dy][newX] = copyMap[dy-1][newX];
		}
		copyMap[0][newX] = 0;
		
	}

	// 첫번 째 터지는 위치의 y를 반환하는 메서드 만약 터지지 않고 맵을 벗어나면 -1을 리턴한다.
	public static int findFirstBoomY(int firstBoomX) {
		int y = 0;
		while(true) {
			if(!isRange(firstBoomX, y)) {
				return -1;
			}
			if(copyMap[y][firstBoomX] != 0)  {
				return y;
			}
			y++;
		}
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= w || y < 0 || y >= h) {
			return false;
		}
		return true;
	}
	
}

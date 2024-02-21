import java.util.*;
import java.io.*;


class Cctv {
	int x, y, type, dir;
	
	Cctv (int x, int y, int type) {
		
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	Cctv (int x, int y, int type, int dir) {
		
		this.x = x;
		this.y = y;
		this.type = type;
		this.dir = dir;
	}
}
public class Main {

	static int n, m;
	static int[][] map;
	static int[][] copyMap;
	
	static List<Cctv> list;
	static List<Cctv> resultList;
	
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	
	static int result = Integer.MAX_VALUE;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new ArrayList<Cctv>();
		resultList = new ArrayList<Cctv>();
		map = new int[n][m];
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x< m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				
				if(map[y][x] != 0 && map[y][x] != 6) {
					list.add(new Cctv(x, y, map[y][x]));
				}
			}
		}
		
		dfs(0);
		System.out.println(result);
		
	}
	
	public static void dfs(int depth) {
		// 깊이가 리스트 길이만큼 도착했을 때 맵에서 감시구역을 체크한다.
		if(depth == list.size()) {
			
			copyMap = new int[n][m];
			for(int y = 0; y < n; y++) {
				copyMap[y] = map[y].clone();
			}
			
			// 맵 복사완료 cctv type 별로 감시 시작
			for(int i = 0; i < resultList.size(); i++) {
				
				Cctv c = resultList.get(i);
				if(c.type == 1) {
					type1(new Cctv(c.x, c.y, c.type, c.dir));
				}
				if(c.type == 2) {
					type2(new Cctv(c.x, c.y, c.type, c.dir));
				}
				if(c.type == 3) {
					type3(new Cctv(c.x, c.y, c.type, c.dir));
				}
				if(c.type == 4) {
					type4(new Cctv(c.x, c.y, c.type, c.dir));
				}
				if(c.type == 5) {
					type5(new Cctv(c.x, c.y, c.type, c.dir));
				}
			}
			
			result = Math.min(result, doCctv());
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			// depth cctv를 꺼내서 direction을 설정한 다음에 다시 재귀한다.
			Cctv c = list.get(depth);
			c.dir = i;
			resultList.add(c);
			dfs(depth+1);
			resultList.remove(resultList.size()-1);
		}
	}
	
	public static int doCctv() {
		// 복사한 맵에 cctv 감시구역을 카운트합니다.
		int cnt = 0;
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				if(copyMap[y][x] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	// 일직선으로 감시
	public static void type1(Cctv c) {
		int newX = dx[c.dir] + c.x;
		int newY = dy[c.dir] + c.y;
		while(true) {
			if(isRange(newX, newY) && map[newY][newX] != 6) {
				copyMap[newY][newX] = -1;
				newX += dx[c.dir];
				newY += dy[c.dir];
			} else {
				break;
			}
		}
	}
	
	// 양방향으로 감시, 주어진 방향에 반대편 ex) 0 1 2 3인 경우 2 3 0 1 도 감시함
	public static void type2(Cctv c) {
		type1(c);
		
		if(c.dir == 0) {
			c.dir = 2;
		}
		else if(c.dir == 1) {
			c.dir = 3;
		}
		else if(c.dir == 2) {
			c.dir = 0;
		}
		else if(c.dir == 3) {
			c.dir = 1;
		}
		type1(c);
	}
	
	// 직각으로 감시, 주어진 각도가 0 1 2 3인 경우 1 2 3 0을 추가로 감시한다.
	public static void type3(Cctv c) {
		type1(c);
		c.dir = (c.dir+1) % 4;
		type1(c);
	}
	
	// ㅗ 방향으로 감시, 주어진 각도가 0 1 2 3인 경우에 (3, 1), (0, 2), (1,3), (2, 0) 을 감시한다.
	public static void type4(Cctv c) {
		type1(c);
		
		// 첫번째
		int dir = c.dir -1;
		if(dir < 0) dir = 3;
		c.dir = dir;
		type1(c);
		
		c.dir = (c.dir+2) % 4;
		type1(c);
	}
	
	// 사방향 감시
	public static void type5(Cctv c) {
		for(int i = 0; i < 4; i++) {
			c.dir = i;
			type1(c);
		}
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= m || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
}






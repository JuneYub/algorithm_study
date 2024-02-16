
import java.io.*;
import java.util.*;

class Target {
	int x, y, len;
	
	Target(int x, int y) {
		this.x = x;
		this.y = y;	
	}
	
	Target(int x, int y, int len) {
		this.x = x;
		this.y = y;
		this.len = len;
	}
}
public class Main {
	
	static int n, m, d;
	static int[][] map;
	static int[][] orgMap;
	static int result = 0;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n =Integer.parseInt(st.nextToken());
		m =Integer.parseInt(st.nextToken());
		d =Integer.parseInt(st.nextToken());
		
		orgMap = new int[n][m];
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < m; x++) {
				orgMap[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 궁수를 배치 하는 조합
		int[] arr = new int[3];
		dfs(0, arr, 0);
		System.out.println(result);
		
	} // end of public static void main(String[] args) throws Exception
	
	public static void game(int[] 궁수위치) {
		
		int delCnt = 0;
		
//		for(int n = 0; n <3; n++) {
//			System.out.print(궁수위치[n] +" ");
//		}
//		System.out.println();
		
		// 맵초기화
		map = new int[n][m];
		for(int y = 0; y < n; y++) {
			map[y] = orgMap[y].clone();
		}
		
		// 행의 개수만큼 반복한다.
		for(int i = 0; i < n; i++) {
			
			// 궁수 목표
			List<Target> 타겟리스트 = new ArrayList<>();
			//boolean[] chance = new boolean[3];
			//Arrays.fill(chance, true);
			
			for(int 궁수발사순서 = 0; 궁수발사순서 < 3; 궁수발사순서++) {
				Queue<Target> q = new ArrayDeque<>();
				q.add(new Target(궁수위치[궁수발사순서], n, 0));
				
				boolean[][] visited = new boolean[n][m];
				while(!q.isEmpty()) {
					
					Target 방문위치 = q.poll();
					if(isRange(방문위치.x, 방문위치.y) && map[방문위치.y][방문위치.x] == 1 && 방문위치.len <= d && 방문위치.len > 0) {
						타겟리스트.add(new Target(방문위치.x, 방문위치.y, 방문위치.len));
						break;
					}
					// 왼쪽, 위, 오른쪽
					int[] dx = {-1, 0 , 1};
					int[] dy = {0, -1, 0};
					for(int 방향 = 0; 방향 < 3; 방향++) {
						int newX = 방문위치.x + dx[방향];
						int newY = 방문위치.y + dy[방향];
						
						if(isRange(newX, newY) && !visited[newY][newX]) {
							visited[newY][newX] = true;
							// 사정거리 안에까지만 방문하자
							if(방문위치.len+1 <= d) {
								q.add( new Target(newX, newY, 방문위치.len+1) );
							}
						}
					}
				}
			}

			for(int k = 0; k < 타겟리스트.size(); k++) {
				if(map[타겟리스트.get(k).y][타겟리스트.get(k).x] == 1) {
					map[타겟리스트.get(k).y][타겟리스트.get(k).x] = 0;
					delCnt++;
				} 
				
			}
			//System.out.println(result);
			map = moveEnemy();
		}
		result = Math.max(result, delCnt);
	}
	
	public static void dfs(int depth, int[] arr, int startIdx) {
		if(depth == 3) {
			// 궁수 위치 세팅 후 게임 시작
			game(arr);
			return;
		}
		
		for(int i = startIdx; i < m; i++) {
			arr[depth] = i;
			dfs(depth+1, arr, i+1);
			
		}
		
	}
	
	
	public static int[][] moveEnemy() {
		int[][] tmpMap = new int[n][m];
		
		for(int x = 0; x < m; x++) {
			tmpMap[0][x] = 0;
		}
		
		for(int y = 1; y < n; y++) {
			for(int x = 0; x < m; x++) {
				tmpMap[y] = map[y-1].clone();
			}
		}
		
		return tmpMap;
	}
	
	public  static boolean isRange(int x, int y) {
		if(x < 0 || x >= m || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
	
}

import java.util.*;
import java.io.*;


class Position {
	int x, y, dir, cnt;
	
	Position(int x, int y , int dir, int cnt) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.cnt = cnt;
	}
}

public class Main {
	
	static int n, m;
	static int[][] map;
	static int[][] distanceMap;
	static boolean[][] visited;
	static int sX, sY, sD, eX, eY, eD;
	static int dx[] = {0, 1, 0, -1}; // 북0 동1  남2  서3 
	static int dy[] = {-1, 0, 1, 0};
	static int ans = Integer.MAX_VALUE;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		distanceMap = new int[n][m];
		visited = new boolean[n][m];
		
		for(int y = 0; y < n; y++) {
			Arrays.fill(distanceMap[y], Integer.MAX_VALUE);
		}
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0;x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		sY = Integer.parseInt(st.nextToken())-1;
		sX = Integer.parseInt(st.nextToken())-1;
		sD = Integer.parseInt(st.nextToken())-1;
		// 동 서 남 북 -> 북 동 남 서로 치환
		if(sD == 0) sD = 1; 
		else if(sD == 1) sD = 3; 
		else if(sD == 2) sD = 2; 
		else if(sD == 3) sD = 0; 
		
		st = new StringTokenizer(br.readLine());
		eY = Integer.parseInt(st.nextToken())-1;
		eX = Integer.parseInt(st.nextToken())-1;
		eD = Integer.parseInt(st.nextToken())-1;
		if(eD == 0) eD = 1; 
		else if(eD == 1) eD = 3; 
		else if(eD == 2) eD = 2; 
		else if(eD == 3) eD = 0;
		
		Queue<Position> q = new ArrayDeque<>();
		q.add(new Position(sX, sY, sD, 0));
		visited[sY][sX] = true;
		
		
		while(!q.isEmpty()) {
			
			Position p = q.poll();
			//System.out.println("Y : " + p.y + " X : " + p.x + " 방향 : " + p.dir+ " 누적작동횟수 : " + p.cnt);
			// 현재 누적된 작동 횟수가 이미 새롭게 갱신되었을 경우에는 넘어간다.
			if(p.cnt > distanceMap[p.y][p.x]) continue;
			
			// 도착지에 온 경우 방향이 같은 경우 다른 경우에 따라 새롭게 갱신해준다.
			if(p.y == eY && p.x == eX) {
				if(p.dir == eD) {
					if(ans > p.cnt) {
						ans = p.cnt;
					}
						
				}
				else {
					// 방향을 돌리는 횟수도 포함시켜 갱신해준다.
					int turnCnt = 2;
					if(eD == (p.dir+1)%4 || eD == (p.dir+3)%4) {
						turnCnt = 1;
					}
					
					if(ans > p.cnt+turnCnt) {
						ans = p.cnt+turnCnt;
					}
				}
			}
			
			
			// 4방향 중 하나를 정하고
			for(int i = 0; i < 4; i++) {
				
				// 새로운 방향
				int newDir = (p.dir + i) % 4;
				// 작동횟수를 정하고
				int addCnt = 1;
				if(i == 1 || i == 3) addCnt = 2;
				if(i == 2 ) addCnt = 3;
				
				// 1~3 스텝을 정한 다음에
				step:for(int k = 1; k <= 3; k++) {
					
					int newX = p.x + dx[newDir]*k;
					int newY = p.y + dy[newDir]*k;
					
					
					if(isRange(newX, newY)) {
						if(map[newY][newX] == 1) {
							// 지도 범위안이고 궤도가 깔려있는지 확인한다. 궤도가 깔려있지않다면 방향을 틀어준다.
							break step;
						}
						else {
							// 방문한 적이 없는 경우
							if(!visited[newY][newX]) {
								distanceMap[newY][newX] = p.cnt + addCnt;
								visited[newY][newX] = true;
								q.add(new Position(newX, newY, newDir, p.cnt + addCnt));
							}
							
							// 방문했지만, 해당 부분을 내가 더 빠른 회수안에 갈 수 있을  때만 갱신하면서 큐에 넣어준다.
							if(distanceMap[newY][newX] > p.cnt+addCnt) {
								distanceMap[newY][newX] = p.cnt + addCnt;
								q.add(new Position(newX, newY, newDir, p.cnt + addCnt));
							}
                            
                            if(newY == eY && eX == newX && distanceMap[newY][newX] >= p.cnt+addCnt) {
								distanceMap[newY][newX] = p.cnt + addCnt;
								q.add(new Position(newX, newY, newDir, p.cnt + addCnt));
							}
						}
					}
				}
			}
		}
		
		System.out.print(ans);
	}
	
	public static boolean isRange(int x, int y ) {
		if( x < 0 || x >= m || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
	
}
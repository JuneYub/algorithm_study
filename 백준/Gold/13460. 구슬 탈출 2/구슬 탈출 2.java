import java.util.*;
import java.io.*;

class Position {
	int rx,ry,bx,by,cnt;
	
	Position(int rx, int ry, int bx, int by, int cnt) {
		this.rx = rx;
		this.ry = ry;
		this.bx = bx;
		this.by = by;
		this.cnt = cnt;
	}
}

public class Main {

	static int n, m;
	static char[][] map;
	static boolean[][][][] visited;
	static int rX, rY, bX, bY;
	static int oX, oY;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int result = -1;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][m];
		// bY,bX,rY,rX
		visited = new boolean[n][m][n][m];
		
		for(int y = 0; y < n; y++) {
			String str = br.readLine();
			for(int x = 0; x < m; x++) {
				map[y][x] = str.charAt(x); 
				if(map[y][x] == 'O') {
					oX = x;
					oY = y;
				}
				
				else if(map[y][x] == 'R') {
					rX = x;
					rY = y;
					map[y][x] = '.';
				}
				
				else if(map[y][x] == 'B') {
					bX = x;
					bY = y;
					map[y][x] = '.';
				}
			}
		}
		
		visited[bY][bX][rY][rX] = true;
		Queue<Position> q = new ArrayDeque<>();
		q.add(new Position(rX, rY, bX, bY, 0));
		
		while(!q.isEmpty()) {
			
			Position p = q.poll();
			
			// 10회가 넘어가면 안됨
			if(p.cnt > 10) continue;
			
			if(map[p.by][p.bx] == 'O') {
				continue;
			}
			if(map[p.ry][p.rx] == 'O') {
				if(result == -1) result = p.cnt;
				else {
					result = Math.min(result, p.cnt);
				}
				
				continue;
			}
			
			
			for(int i = 0; i < 4; i++) {
				
				// 파란공 처리하기
				int nbx = p.bx + dx[i];
				int nby = p.by + dy[i];
				while(true) {
					// 지도 범위 내이고, .을 만난 경우
					if(isRange(nbx, nby) && map[nby][nbx] == '.') {
						nbx += dx[i];
						nby += dy[i];
					} 
					// 지도 범위 내이고 출구를 만난경우 그대로 종료
					else if(isRange(nbx, nby) && map[nby][nbx] == 'O') {
						break;
					}
					// 지도 범위를 벗어났거나 벽을 만난 경우 한칸 뒤로 돌아간다.
					else if(!isRange(nbx, nby) || map[nby][nbx] == '#'){
						nbx -= dx[i];
						nby -= dy[i];
						break;
					}
				}
				
				// 빨간공 처리하기
				int nrx = p.rx + dx[i];
				int nry = p.ry + dy[i];
				while(true) {
					// 지도 범위 내이고, .을 만난 경우
					if(isRange(nrx, nry) && map[nry][nrx] == '.') {
						nrx += dx[i];
						nry += dy[i];
					} 
					// 지도 범위 내이고 출구를 만난경우 그대로 종료
					else if(isRange(nrx, nry) && map[nry][nrx] == 'O') {
						break;
					}
					// 지도 범위를 벗어났거나 벽을 만난 경우 한칸 뒤로 돌아간다.
					else if(!isRange(nrx, nry) || map[nry][nrx] == '#'){
						nrx -= dx[i];
						nry -= dy[i];
						break;
					}
				}
				
				// 두 공이 모두 같은 곳에 있는 경우
				if(nrx == nbx && nry == nby) {
					// 두 공이 같은 곳에 있는데 그곳이 출구라면? 실패한것이므로 진행하지 않는다.
					if(map[nry][nrx] == 'O') {
						continue;
					}
					
					switch(i) {
					
					// 상단으로 향한 경우
					case 0:
						// 큰쪽이 더 아래에 있음
						if(p.by > p.ry) {
							nby += 1;
						} 
						else if(p.ry > p.by) {
							nry += 1;
						}
						
						break;
						
					// 우측으로 향한 경우
					case 1:
						// 큰 쪽이 더 우측에 있음
						if(p.bx > p.rx) {
							nrx -= 1;
						} else if(p.rx > p.bx) {
							nbx -= 1;
						}
						
						break;
					
					// 하단으로 향한 경우
					case 2:
						
						// 큰쪽이 더 아래에 있음
						if(p.by > p.ry) {
							nry -= 1;
						} 
						else if(p.ry > p.by) {
							nby -= 1;
						}
						
						break;
						
					// 좌측으로 향한 경우
					case 3:
						// 큰 쪽이 더 우측에 있음
						if(p.bx > p.rx) {
							nbx += 1;
						} else if(p.rx > p.bx) {
							nrx += 1;
						}
						break;
					
					}
				}
				
				// 방문배열 확인
				if(!visited[nby][nbx][nry][nrx]) {
					visited[nby][nbx][nry][nrx] = true;
					q.add(new Position(nrx, nry, nbx, nby, p.cnt+1));
				}
				
			}
		}
		
		System.out.println(result);
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= m || y < 0 || y >= n) return false;
		return true;
	}
}

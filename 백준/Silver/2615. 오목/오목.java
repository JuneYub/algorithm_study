import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map = new int[19][19];
	static int resultY = 0;
	static int resultX = 0;
	static int resultColor = 0;
	static boolean victory = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int y = 0; y < 19; y++) {
			st = new StringTokenizer(br.readLine());
			
			for(int x = 0; x < 19; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int y = 0; y < 19; y++) {
			for(int x = 0; x < 19; x++) {
				if(map[y][x] != 0 && !victory) {
					search(x, y);
				}
			}
		}
		
		System.out.println(resultColor);
		if(resultColor != 0) {
			System.out.print(resultY + " " +resultX);
		}
	}
	
	// 지도 밖을 벗어나는지 체크
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= 19 || y < 0 || y >= 19) {
			return false;
		}
		return true;
	}
	
	// 팔방탐색
	public static void search(int x, int y) {
		int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
		
		int currentNum = map[y][x];
		
		// 12시부터 시계방향으로 탐색
		for(int i = 0; i < 8; i++) {
			int cnt = 1;
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			

			while(true) {
				if(isRange(nextX, nextY)) {
					// 다음 숫자가 시작 숫자와 다른 경우에는 break
					if(currentNum != map[nextY][nextX]) {
						cnt = 1;
						break;
					}
					
					if(currentNum == map[nextY][nextX]) {
						cnt++;
						nextX += dx[i];
						nextY += dy[i];
						
						if(cnt == 5) {
							// 5개의 바둑알이 연속인데 다음 바둑알도 같은 경우
							if(isRange(nextX, nextY) && currentNum == map[nextY][nextX]) {
								cnt = 1;
								break;
							}
							
							// 5개의 바둑알이 연속인데 반대편 바둑알도 같은 경우
							int beforeX = 0;
							int beforeY = 0;
							
							// 12시, 1시, 3시, 5시 방향의 반대는 6시, 7시, 9시, 11시
							if(i < 4) {
								beforeX = (x + dx[i+4]);
								beforeY = (y + dy[i+4]);
							}
							
							// 6시, 7시, 9시, 11시 방향의 반대는 12시, 1시, 3시, 5시
							if(i >= 4) {
								beforeX = (x + dx[i-4]);
								beforeY = (y + dy[i-4]);
							}
							if(isRange(beforeX, beforeY) && currentNum == map[beforeY][beforeX]) {
								cnt = 1;
								break;
							}
							
							// 그렇지 않다면 승리
							else {
								resultColor = currentNum;
								if(i == 5 || i == 6 || i == 7) {
									nextX -= dx[i];
									nextY -= dy[i];
									
									resultY = ++nextY;
									resultX = ++nextX;
								} else {
									resultY = ++y;
									resultX = ++x;
								}
								victory = true;
								break;
							}
							
						}
					}
				}
				else {
					break;
				}
			}				

		}
	}
}


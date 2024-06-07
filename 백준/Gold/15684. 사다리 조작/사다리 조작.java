
import java.util.*;
import java.io.*;

class Position {
	int x, y;
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	static int n, m, h;
	static int[][] map;
	static int[] dx = {-1, 1};
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		map = new int[h][n];
		
		// 2차원 배열을 활용한 사다리 만들기
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int startCol = Integer.parseInt(st.nextToken());
			
			map[row-1][startCol-1] = 1;
		}
		
		for(int size = 0; size <= 3; size++) {

			makeBridge(0, 0, size);
		}
		System.out.println(-1);
		
	}
	
	// 다리를 만드는 조합
	public static void makeBridge(int startY, int bridgeSize, int size) {
		// 현재 놓은 다리개수와 놓아야 하는 다리 개수가 같을 때 체크한다.
		if(bridgeSize == size) {
			if(isCorrect()) {
				System.out.println(size);
				System.exit(0);
			}
		}
		
		else {
			for(int y = startY; y < h; y++) {
				for(int x = 0; x < n; x++) {
					
					if(isRange(x-1, y) && map[y][x-1] == 1) continue;
					if(isRange(x+1, y) && map[y][x+1] == 1) continue;
					
					if(map[y][x] == 0) {
						map[y][x] = 1;
						// 현재 지점과 우측 지점으로 연결하고 다시 조합으로 들어간다.
						makeBridge(y, bridgeSize+1, size);
						map[y][x] = 0;
					}
				}
			}
		}
		

	}
	
	// 시작컬럼과 도착컬럼이 일치하는지 확인하는 메서드
	public static boolean isCorrect() {
		for(int startX = 0; startX < n; startX++) {
			int x = startX;
			
			// 한칸씩 내려간다.
			for(int y = 0; y < h; y++) {
				// 왼쪽으로 가는 경우
				if(isRange(x-1, y) && map[y][x-1] == 1) {
					x = x-1;
					continue;
					
				}
				
				// 오른쪽으로 가는 경우
				if(isRange(x+1, y) && map[y][x] == 1) {
					x = x+1;
					continue;
				}
			
				// 다시 한칸 내려감
			}
			
			// 끝까지 내려갔을 때 도착한 x지점 체크
			if(x != startX) return false;
		}
		return true;
	}
	
	public static boolean isRange(int x, int y) {
		if( y < 0 || y >= h || x < 0 || x >= n) return false;
		return true;
	}

}


import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static boolean[] visitedX;
	static boolean[] visitedY;
	static int result = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visitedX = new boolean[n];
		visitedY = new boolean[n];
		
		permutation(0);
		System.out.println(result);
		
	}

	public static void permutation(int cnt) {
		if(cnt == n) {
			// 체크하기
			//System.out.println("---------- 찾음");
			result++;
			return;
		}
		

		if(!visitedY[cnt]) {
			for(int x = 0; x < n; x++) {
				if(!visitedX[x]) {
					
					if(!crossCheck(x, cnt)) continue;
					
					visitedY[cnt] = true;
					visitedX[x] = true;
					map[cnt][x] = 1;
					//System.out.println("cnt" + cnt + " " + "y : "+ cnt+ " " + "x : " + x);
					permutation(cnt+1);
					map[cnt][x] = 0;
					visitedY[cnt] = false;
					visitedX[x] = false;
				}
			}
		}

	}
	
	public static boolean isRange(int x, int y ) {
		if(x < 0 || x >= n || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
	
	public static boolean crossCheck(int x, int y) {
		// 해당 점의 대각선을 탐색해서 이미 퀸이 있다면 false를 리턴한다.
		// / 우측 대각선 탐색 x+1, y-1 또는 x-1 y+1 두 개
		// \ 좌측 대각선 탐색 x-1 y-1 또는 x+1 y+1 두 개
		
		int newX = x;
		int newY = y;
		while(true) {
			int cnt = 1;
			// 범위 내에 있는 경우 우측 대각선 탐색
			if(isRange(newX, newY)) {
				if(map[newY][newX] == 1) {
					cnt++;
				}
				if(cnt >= 2) return false;
				newX++;
				newY--;
				continue;
			}
			break;
		}
		
		newX = x;
		newY = y;
		while(true) {
			int cnt = 1;
			// 범위 내에 있는 경우 우측 대각선 탐색
			if(isRange(newX, newY)) {
				if(map[newY][newX] == 1) {
					cnt++;
				}
				if(cnt >= 2) return false;
				newX--;
				newY++;
				continue;
			}
			break;
		}
		
		newX = x;
		newY = y;
		while(true) {
			int cnt = 1;
			// 범위 내에 있는 경우 좌측 대각선 탐색
			if(isRange(newX, newY)) {
				if(map[newY][newX] == 1) {
					cnt++;
				}
				if(cnt >= 2) return false;
				newX--;
				newY--;
				continue;
			}
			break;
		}
		
		newX = x;
		newY = y;
		while(true) {
			int cnt = 1;
			// 범위 내에 있는 경우 좌측 대각선 탐색
			if(isRange(newX, newY)) {
				if(map[newY][newX] == 1) {
					cnt++;
				}
				if(cnt >= 2) return false;
				newX++;
				newY++;
				continue;
			}
			break;
		}
		
		return true;
	}
}

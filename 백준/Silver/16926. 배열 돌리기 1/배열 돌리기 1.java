
import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] map;
	static int[][] result;
	static int n, m, r;
	static int[] dx = {-1, 0, 1, 0}; // 9시, 6시, 3시, 12시 방향으로 이동
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		result = new int[n][m];
		
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int startHeight = 0;
		int height = n;
		int startWidth = 0;
		int width = m;
		
		while(height > startHeight && width > startWidth) {
			
			for(int x = startWidth; x < width; x++) {
				rotation(startHeight, x, startWidth, width, startHeight, height);
			}
			
			for(int x = startWidth; x < width; x++) {
				rotation(height-1, x, startWidth, width, startHeight, height);
			}
			
			for(int y = startHeight; y < height; y++) {
				rotation(y, startWidth, startWidth, width, startHeight, height);
			}
			
			for(int y = startHeight; y < height; y++) {
				rotation(y, width-1, startWidth, width, startHeight, height);
			}
			
			
			startHeight++;
			height--;
			startWidth++;
			width--;
		}
		
		
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				System.out.print(result[y][x] + " ");
			}
			System.out.println();
		}
		
		
	}
	
	private static void rotation(int y, int x, int minX, int maxX, int minY, int maxY) {
		int cnt = 0; 
		int tmp = map[y][x];
		while(cnt != r) {
			// 최상단
			if(y == minY) {
				while(cnt != r && x > minX) {
					x--;
					cnt++;
				}
			}
			// 최좌측
			if(x == minX) {
				while(cnt != r && y < maxY-1) {
					y++;
					cnt++;
				}
			}
			
			// 최하단
			if(y == maxY-1) {
				while(cnt != r && x < maxX-1) {
					x++;
					cnt++;
				}
			}
			
			// 최우측
			if(x == maxX-1) {
				while(cnt != r && y > minY) {
					y--;
					cnt++;
				}
			}
		}
		result[y][x] = tmp;
	}
	
	
	
	
	private boolean isRange(int y, int x) {
		if(y < 0 || y >= n || x < 0 || x >= m) return false;
		return true;
	}
	    
}
    

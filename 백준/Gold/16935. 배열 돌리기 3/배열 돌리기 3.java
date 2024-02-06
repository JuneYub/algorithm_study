import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m, r;
	static int[][] map;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		map = new int[n][m];
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < r; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			switch(num) {
			case 1: fun1();
					break;
			case 2: fun2();
					break;
			case 3: fun3();
					break;
			case 4: fun4();
					break;
			case 5: fun5();
					break;
			case 6: fun6();
					break;
			}
			
			
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int y = 0; y < map.length; y++) {
			for(int x = 0; x < map[0].length; x++) {
				bw.write(map[y][x] + " ");
			}
			bw.newLine();
		}
		bw.flush();
	}
	
	// 상하 반전
	public static void fun1() {
		int n = map.length;
		int m = map[0].length;
		int[][] tmp = new int[n][m];
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				tmp[n-1-y][x] = map[y][x];
			}
		}
		map = tmp;
	}
	
	// 좌우 반전
	public static void fun2() {
		int n = map.length;
		int m = map[0].length;
		int[][] tmp = new int[n][m];
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				tmp[y][m-1-x] =  map[y][x];
			}
		}
		map = tmp;
	}
	
	// 오른쪽  90도
	public static void fun3() {
		int n = map.length;
		int m = map[0].length;
		int[][] tmp = new int[m][n];
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				tmp[x][n-1-y] = map[y][x]; 
			}
		}
		map = tmp;
	}
	
	// 왼쪽  90도
	public static void fun4() {
		int n = map.length;
		int m = map[0].length;
		int[][] tmp = new int[m][n];

		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				tmp[m-1-x][y] = map[y][x]; 
			}
		}
		map = tmp;
	}
	
	// 1=>2, 2=>3, 3=>4, 4=>1
	public static void fun5() {
		int n = map.length;
		int m = map[0].length;
		int[][] tmp = new int[n][m];
		// n/2, m/2
		for(int y = 0; y < n/2; y++) {
			for(int x = 0; x<m/2; x++) {
				tmp[y][x+m/2] = map[y][x];
			}
		}
		for(int y = 0; y < n/2; y++) {
			for(int x = m/2; x<m; x++) {
				tmp[y+n/2][x] = map[y][x];
			}
		}
		
		for(int y = n/2; y < n; y++) {
			for(int x = m/2; x<m; x++) {
				tmp[y][x-m/2] = map[y][x];
			}
		}
		
		for(int y = n/2; y < n; y++) {
			for(int x = 0; x<m/2; x++) {
				tmp[y-n/2][x] = map[y][x];
			}
		}
		map = tmp;
	}
	
	// 1=>4, 2=>1, 3=>2, 4=>3
	public static void fun6() {
		int n = map.length;
		int m = map[0].length;
		int[][] tmp = new int[n][m];
		// n/2, m/2
		for(int y = 0; y < n/2; y++) {
			for(int x = 0; x<m/2; x++) {
				tmp[y+n/2][x] = map[y][x];
			}
		}
		for(int y = 0; y < n/2; y++) {
			for(int x = m/2; x<m; x++) {
				tmp[y][x-m/2] = map[y][x];
			}
		}
		
		for(int y = n/2; y < n; y++) {
			for(int x = m/2; x<m; x++) {
				tmp[y-n/2][x] = map[y][x];
			}
		}
		
		for(int y = n/2; y < n; y++) {
			for(int x = 0; x<m/2; x++) {
				tmp[y][x+m/2] = map[y][x];
			}
		}
		map = tmp;
	}
}
    
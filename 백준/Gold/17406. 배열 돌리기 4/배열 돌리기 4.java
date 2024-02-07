
import java.util.*;
import java.io.*;



class Oper {
	int r;
	int c;
	int s;
	
	Oper(int r, int c,int s) {
		this.r = r;
		this.c = c;
		this.s = s;
	}
}

public class Main {
	
	static int n, m, k;
	static int[][] map;
	static int[][] result;
	static Oper[] operArr;
	static Oper[] resultOperArr;
	static int[][] rotationMap;
	static boolean[] recursionVisited;
	static int min = Integer.MAX_VALUE;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		// 원본
		map = new int[n][m];
		rotationMap = new int[n][m];
		result = new int[n][m];
		operArr = new Oper[k];
		resultOperArr = new Oper[k];
		recursionVisited = new boolean[k];
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < m; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				rotationMap[y][x] = map[y][x];
			}
		}
		
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			operArr[i] = new Oper(r, c, s);
		}
		
		recursion(0);
		
		System.out.println(min);
		
		
	} // end of main =============================
	
	public static void rotation(int r, int c, int s) {
		int leftTopX = c-s-1;
		int leftTopY = r-s-1;
		int rightBottomX = c+s-1;
		int rightBottomY = r+s-1;
		int width = m;
		int height = n;
		
		while(rightBottomY > leftTopY && rightBottomX > leftTopX) {
			for(int x = leftTopX; x <= rightBottomX; x++) {
				move(x, leftTopY, leftTopX, rightBottomX, leftTopY , rightBottomY); 
			}
			
			for(int x = leftTopX; x <= rightBottomX; x++) {
				move(x, rightBottomY, leftTopX, rightBottomX, leftTopY , rightBottomY);
			}
			
			for(int y = leftTopY; y <= rightBottomY; y++) {
				move(leftTopX, y, leftTopX, rightBottomX, leftTopY , rightBottomY);
			}
			
			for(int y = leftTopY; y <= rightBottomY; y++) {
				move(rightBottomX, y, leftTopX, rightBottomX, leftTopY , rightBottomY);
			}
			
			leftTopX++;
			leftTopY++;
			rightBottomX--;
			rightBottomY--;
		}
		
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < m; x++) {
				rotationMap[y][x] = result[y][x];
			}
		}
	}
	
	public static void move(int x, int y, int startX, int endX, int startY, int endY) {
		int r = 1;
		int cnt = 0;
		int tmp = rotationMap[y][x];
		while(cnt != r) {
			while(y == startY && x != endX && cnt != r) {
				x++;
				cnt++;
			}
			
			while(y != endY && x == endX && cnt != r) {
				y++;
				cnt++;
			}
			
			while(y == endY && x != startX && cnt != r) {
				x--;
				cnt++;
			}
			
			while(y != startY && x == startX && cnt != r) {
				y--;
				cnt++;
			}
			result[y][x] = tmp;
		}
	}
	
	public static void recursion(int depth) {
		if(depth == k) {
			
			
			for(int y = 0; y < n; y++) {
				for(int x = 0; x< m; x++) {
					result[y][x] = rotationMap[y][x];
				}
			}
			
			for(int i = 0; i < k; i++) {
				Oper oper = resultOperArr[i];
				rotation(oper.r, oper.c, oper.s);
			}
			
			for(int y = 0; y < n; y++) {
				int tmp = 0;
				for(int x = 0; x < m; x++) {
					tmp += result[y][x];
				}
				//System.out.println(tmp);
				min = Math.min(tmp, min);
			}
			
			for(int y = 0; y<n; y++) {
				for(int x =0; x<m;x++) {
					rotationMap[y][x] = map[y][x];
				}
			}
			
			return;
		}
		else {
			for(int i = 0; i < k; i++) {
				if(!recursionVisited[i]) {
					recursionVisited[i] = true;
					resultOperArr[depth] = operArr[i];
					recursion(depth+1);
					recursionVisited[i] = false;
				}
			}
		}
		
		
	}
	
}
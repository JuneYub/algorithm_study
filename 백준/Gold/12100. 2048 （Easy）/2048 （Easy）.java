
import java.util.*;
import java.io.*;


class Block {
	int x,y,size;
	
	Block(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
}

public class Main {

	static int n;
	static int[][] orgMap;
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		
		orgMap = new int[n][n];
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				orgMap[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] dirArr = new int[5];
		for(int i = 0; i < 4; i++) {
			
			dirArr[0] = i;
			dfs(deepCopy(orgMap), 0, i, dirArr);
		}

		System.out.println(result);
	}
	
	public static void dfs(int[][] map, int cnt, int dir, int[] dirArr) {
		if(cnt == 5) {
			// 5번을 돌았으므로 최대값을 갱신한다.
			
			for(int y = 0; y < n ; y++) {
				//System.out.println();
				for(int x= 0; x< n; x++) {
					//System.out.print(map[y][x] + " ");
					result = Math.max(map[y][x], result);
				}
			}

//			System.out.println();
//			for(int i = 0; i < 5; i++) {
//				System.out.print(dirArr[i] + " ");
//			}
			
			//System.out.println("==================================");
			
			return;
		}
		
		// 파라미터로 받은 방향을 기준으로 옮겨본다.
		switch(dir) {
		
		// 상단으로 움직이는 경우
		case 0:
			
			for(int x = 0; x < n; x++) {
				int[] copyMap1 = new int[n];
				
				for(int y = 0; y < n; y++) {
					copyMap1[y] = map[y][x];
				}
				
				makeCube(copyMap1);
				
				for(int y = 0; y < n; y++) {
					map[y][x] = copyMap1[y];
				}
			}
			
			break;
			
		// 우측으로 이동하는 경우
		case 1:
			
			for(int y = 0; y< n; y++) {
				int[] copyMap1 = new int[n];
				
				int idx = 0;
				for(int x = n-1; x >= 0; x--) {
					copyMap1[idx++] = map[y][x];
				}
				
				makeCube(copyMap1);
				
				idx = 0;
				for(int x = n-1; x >= 0; x--) {
					map[y][x] = copyMap1[idx++];
				}
				
			}
			
			break;
			
		// 하단으로 이동하는 경우
		case 2:
			
			for(int x = 0; x < n; x++) {
				int[] copyMap1 = new int[n];
				
				int idx = 0;
				for(int y = n-1; y >= 0; y--) {
					copyMap1[idx++] = map[y][x];
				}
				
				makeCube(copyMap1);
				
				idx = 0;
				for(int y = n-1; y >= 0; y--) {
					map[y][x] = copyMap1[idx++];
				}
			}
			
			break;
		
		// 좌측으로 이동하는 경우
		case 3:
			
			for(int y = 0; y < n; y++) {
				makeCube(map[y]);
			}
			break;
		}
		
		// 12, 3, 6, 9 시 방향으로 옮겨본다.
		if(cnt + 1 == 5) {
			dfs(deepCopy(map), cnt+1, dir, dirArr);
		} else {
			for(int i = 0; i < 4; i++) {
				if(cnt + 1 < 5) {
					dirArr[cnt+1] = i;
				}
				dfs(deepCopy(map), cnt+1, i, dirArr);
			}
		}	
	}
	
	// 2차원 배열 깊은 복사
	public static int[][] deepCopy(int[][] map) {
		int[][] copyMap = new int[n][n];
		for(int y= 0;y<n;y++) {
			copyMap[y] = map[y].clone();
		}
		return copyMap;
	}
	
	// 1차원 배열을 받아 수정한 후에 돌려준다.
	// 이때 우측으로 움직여야 한다면 우측이 시작점이다. 상단으로 움직인다면 상단이 기준이다.
	public static int[] makeCube(int[] map) {


			
			int p1 = 0;
			int p2 = 0;
			while(p2 < n) {
				
				if(p1 == p2) {
					p2++;
					continue;
				}
				
				// p1은 숫자를 가리키는데 p2는 빈칸인 경우 p2를 한칸 옮긴다.
				if(map[p1] > 0 && map[p2] == 0) {
					p2++;
					continue;
				}
				
				// p1과 p2가 가리키는 값이 같은 경우 p1의 값에 2를 곱하고 p2는 0으로 만든 다음 p1과 p2를 옮긴다.
				if(map[p1] > 0 && map[p2] > 0 && map[p1] == map[p2]) {
					map[p1] *= 2;
					map[p2] = 0;
					p1++; p2++;
					continue;
				}	
				
				// p1과 p2가 가리키는 값이 다른 경우 p1을 한칸 옮긴다.
				if(map[p1] > 0 && map[p2] > 0 && map[p1] != map[p2]) {
					p1++;
					continue;
				}				
				
				// p1이 빈칸이고 p2가 숫자를 가리키는 경우 p1에 p2의 값을 넣고 p2는 0으로 만든다. 그리고 p2를 옮긴다.
				if(map[p1] == 0 && map[p2] > 0) {
					map[p1] = map[p2];
					map[p2] = 0;
					p2++;
					continue;
				}
				
				// p1이 빈칸이고 p2도 빈칸인 경우 p2를 한칸 옮긴다.
				if(map[p1] == 0 && map[p2] == 0) {
					p2++;
					continue;
				}
			}
		
		return map;	
	}
}

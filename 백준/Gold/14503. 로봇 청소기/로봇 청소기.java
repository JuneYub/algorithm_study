
import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, 1, 0, -1}; // 북0, 동1, 남2, 서3   후진 (d+2)%4
	static int[] dy = {-1, 0, 1, 0};
	
	static int n, m;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        int[][] map = new int[n][m];
        boolean[][] isClean = new boolean[n][m];
        
        st = new StringTokenizer(br.readLine());
        int nowY = Integer.parseInt(st.nextToken());
        int nowX = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        
        for(int y = 0; y < n; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x = 0; x < m; x++) {
        		map[y][x] = Integer.parseInt(st.nextToken());
        		// 0인 경우 청소되지 않은 빈칸
        		// 1인 경우 벽에 있는 것
        		if(map[y][x] == 1) {
        			isClean[y][x] = true;
        		}
        	}
        }
        
        int ans = 0;
        boolean moving = true;
        while(moving) {
        
        	
        	// 현재 칸이 아직 청소되지 않은 상태이면 현재 칸을 청소한다. 1 = > 0
        	if(!isClean[nowY][nowX]) {
        		isClean[nowY][nowX] = true;
        		ans++;
        	}
        	
        	boolean needClean = false;
        	// 현재 칸의 주변 4칸 탐색
        	for(int i = 0; i < 4; i++) {
        		int newX = nowX + dx[i];
        		int newY = nowY + dy[i];
        		// 청소안되어있고 빈칸이어야 함
        		if(isRange(newX, newY) && !isClean[newY][newX] && map[newY][newX] == 0) {
        			needClean = true; 
        			break;
        		}
        	}
        	
        	// 현재 칸의 주변 4칸 중 청소되지 않은 빈칸이 없는 경우
        	if(!needClean) {
        		// 한칸 후진 가능한지 체크
        		int newD = (d+2)%4;
        		int beforeX = nowX + dx[newD];
        		int beforeY = nowY + dy[newD];
        		
        		// 후진 가능하면 뒤로 후진
        		if(isRange(beforeX, beforeY) && map[beforeY][beforeX] == 0) {
        			nowX = beforeX;
        			nowY = beforeY;
        		}
        		else {
        			// 기계 멈춤
        			moving = false;
        		}
        	} 
        	// 청소되지 않은 빈칸이 있는 경우
        	else {
        		// 최대 3번 돈다.
        		for(int i = 0; i < 4; i++) {
            		// 좌측 90도 회전 
            		d = (d+3)%4;
            		int newX = nowX + dx[d];
            		int newY = nowY + dy[d];
            		
            		if(isRange(newX, newY) && map[newY][newX] == 0&& !isClean[newY][newX]) {
            			nowX = newX;
            			nowY = newY;
            			break;
            		}
        		}
        		
        	}
        }
        System.out.print(ans);
        
        
    }
    
    public static boolean isRange(int x, int y) {
    	if(x < 0 || x >= m || y < 0 || y >= n) return false;
    	return true;
    }
}

import java.util.*;
import java.io.*;

public class Main {
	static int r,c,t; //행, 열, 시간 
	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int airX = 0;
	static int airY1 = -2;
	static int airY2 = -2;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        
        map = new int[r][c];
        
        for(int y = 0; y < r; y++) {
        	st = new StringTokenizer(br.readLine());
        	for(int x = 0; x < c; x++) {
        		map[y][x] = Integer.parseInt(st.nextToken());
        		if(map[y][x] == -1) {
        			airX = x;
        			if(airY1 == -2) {
        				airY1 = y;
        			} else {
        				airY2 = y;
        			}
        		}
        	}
        }
        
        int time  = 0;
        while(++time <= t) {
        	diffuseDust();
//        	for(int y = 0; y < r; y++) {
//        		for(int x = 0; x < c; x++) {
//        			System.out.print(map[y][x]+ " ");
//        		}
//        		System.out.println();
//        	}
        	doTopAirCleaner();
        	doBottomAirCleaner();
        	
        }
        int dust = 0;
        for(int y = 0; y < r; y++) {
        	for(int x = 0; x < c; x++) {
        		if(map[y][x] != -1) {
        			dust+= map[y][x];
        		}
        	}
        }
        System.out.println(dust);
        

	} // end of main

    public static void diffuseDust() {
    	int[][] cloneMap = new int[r][c];
    	// 사방으로 미세먼지를 원본맵의 /5 해서 뿌린다. 뿌린만큼 원본맵에서 뺀다. 마지막에는 둘을 합친다.
    	
    	for(int y = 0; y < r; y++) {
    		//System.out.println();
    		for(int x = 0; x < c; x++) {
    			//System.out.print(map[y][x] + " ");
    			// 원본맵의 특정 지점에 먼지가 있다면 사방 탐색 후 복사맵에다가 가능한 부분에 먼지를 뿌리고 원본맵의 해당 지점에서 먼지를 빼준다.
    			if(map[y][x] > 0) {
    				int dust = map[y][x]/5;
    				int cnt = 0;
    				for(int i = 0; i < 4; i++) {
    					int newX = dx[i] + x;
    					int newY = dy[i] + y;
    					if(isRange(newX, newY) && map[newY][newX] != -1) {
    						cloneMap[newY][newX] += dust;
    						cnt++;
    					}
    				}
					map[y][x] -= (cnt*dust);
    			}
    		}
    	}
    	// 확산된 먼지를 원본 맵에 더한다. 공기청정기의 위치는 -1로 그대로이다.
    	for(int y = 0; y < r; y++) {
    		for(int x = 0; x< c; x++) {
    			map[y][x] += cloneMap[y][x];
    		}
    	}
    }
    
    public static void doTopAirCleaner() {
    	// 상단 부분 공기청정기 가동
    	int[] topDx = {0, 1, 0, -1};
    	int[] topDy = {-1, 0, 1, 0};
    	
    	int dir = 0;
    	// 시작은 일단 공기청정기 바로 위
    	int orgX = airX;
    	int orgY = airY1;
    	
    	int newX = airX + topDx[dir];
    	int newY = airY1 + topDy[dir];
    	
    	while(true) {
    		orgX = newX;
    		orgY = newY;
    		
    		newX = orgX + topDx[dir];
    		newY = orgY + topDy[dir];
    		
    		// 다음 지점이 공기청정기 위치에 도달하면 break;
    		if(isRange(newX, newY) && map[newY][newX] == -1) {
    			map[orgY][orgX] = 0;
    			break;
    		}
    		
    		// 다음 지점이 맵 범위를 벗어나거나 공기청정기 상단 위치보다 아래로 내려간 경우 dir+1 하고 방향 수정
    		if(!isRange(newX, newY) || newY > airY1) {
    			dir++;
    			
        		newX = orgX + topDx[dir];
        		newY = orgY + topDy[dir];
    		}
    		// 다음칸에 있는 먼지를 한칸씩 땡긴다.
    		map[orgY][orgX] = map[newY][newX];
    		
    	}
    }
    
    public static void doBottomAirCleaner() {
    	// 상단 부분 공기청정기 가동
    	int[] bottomDx = {0, 1, 0, -1};
    	int[] bottomDy = {1, 0, -1, 0};
    	
    	int dir = 0;
    	// 시작은 일단 공기청정기 바로 위
    	int orgX = airX;
    	int orgY = airY2;
    	
    	int newX = airX + bottomDx[dir];
    	int newY = airY2 + bottomDy[dir];
    	
    	while(true) {
    		orgX = newX;
    		orgY = newY;
    		
    		newX = orgX + bottomDx[dir];
    		newY = orgY + bottomDy[dir];
    		
    		// 다음 지점이 공기청정기 위치에 도달하면 break;
    		if(isRange(newX, newY) && map[newY][newX] == -1) {
    			map[orgY][orgX] = 0;
    			break;
    		}
    		
    		// 다음 지점이 맵 범위를 벗어나거나 공기청정기 하단 위치보다 높아진 경우 dir+1 하고 방향 수정
    		if(!isRange(newX, newY) || newY < airY2) {
    			dir++;
    			
        		newX = orgX + bottomDx[dir];
        		newY = orgY + bottomDy[dir];
    		}
    		// 다음칸에 있는 먼지를 한칸씩 땡긴다.
    		map[orgY][orgX] = map[newY][newX];
    		
    	}
    }
    
    public static boolean isRange(int x, int y) {
    	if(x < 0 || x >= c || y < 0 || y >= r) {
    		return false;
    	}
    	return true;
    }
}
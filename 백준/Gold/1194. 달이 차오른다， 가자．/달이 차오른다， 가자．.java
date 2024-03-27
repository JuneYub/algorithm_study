import java.util.*;
import java.io.*;


class Position {
    int x, y, cnt;
    int key;
    
    Position(int x, int y, int cnt, int key) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.key = key;
    }
}

public class Main
{
    // a는 97 A는 65  32차이, 0은 48, #은 35, .은 46
    static int n, m;
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int ans = Integer.MAX_VALUE;
    static boolean[][][] visited;
    
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    map = new int[n][m];
	    
	    int startX = 0;
	    int startY = 0;
	    
	    for(int y = 0; y < n; y++) {
	        String str = br.readLine();
	        for(int x = 0; x < m; x++) {
	            map[y][x] = (str.charAt(x) - 0);
	            if(map[y][x] == 48) {
	                startX = x;
	                startY = y;
	            }
	        }
	    }
	    
	    visited = new boolean[n][m][64];
	    
	    Queue<Position> q = new ArrayDeque<>();
	    map[startY][startX] = 46;
	    q.add(new Position(startX, startY, 0, 0));
	    visited[startY][startX][0] = true;
	    
	    while(!q.isEmpty()) {
	        Position p = q.poll();
	        
	        if(map[p.y][p.x] == 49) {
	            ans = Math.min(ans, p.cnt);
	            break;
	        }
	        
	        // 현재 지점이 key라면 key 값을 갱신한다.
	        if(isKey(map[p.y][p.x])) {
	            int keyNum = map[p.y][p.x] - 97;
	            p.key = p.key | 1 << keyNum;
	        }
	        
	        // 사방탐색 진행
	        for(int i = 0; i < 4; i++) {
	            int newX = p.x + dx[i];
	            int newY = p.y + dy[i];
	            
	            // 범위 내이고 현재 들고 있는 key로 방문하지 않았다면 그리고 벽이 아니고 움직일 수 있다면
	            if(isRange(newX, newY) && !visited[newY][newX][p.key] && map[newY][newX] != 35 && canWalk(map[newY][newX])) {
	            	
	            	// door를 만난경우
	            	if(isDoor(map[newY][newX])) {
	            		// 현재 문
	            		int doorNum = map[newY][newX] - 'A';
	            		// 현재 문을 비트마스킹으로 key가 있는지 체크
	            		if( (p.key & 1 << doorNum) > 0) {
	    	                visited[newY][newX][p.key] = true;
	    	                q.add(new Position(newX, newY, p.cnt+1, p.key));
	            		}
	            		
	            	// door가 아닌 경우에는 그냥 진행	
	            	} else {
		                visited[newY][newX][p.key] = true;
		                q.add(new Position(newX, newY, p.cnt+1, p.key));
	            	}
	            }
	            
	        }
	    }
	    System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
	
	public static boolean isRange(int x, int y) {
	    if(x < 0 || x >= m || y < 0 || y >= n) {
	        return false;
	    }
	    return true;
	}
	
	public static void deleteDoor(int key) {
	    int door = key - 32;
	    
	    for(int y = 0; y < n; y++) {
	        for(int x = 0; x < m; x++) {
	            if(map[y][x] == door) {
	                map[y][x] = 46; // .으로 변경
	                return;
	            }
	        }
	    }
	}
	
	public static boolean canWalk(int nextPosition) {
		if(nextPosition == 49 || nextPosition == 46 || isDoor(nextPosition) || isKey(nextPosition)) return true;
		return false;
	}
	
	public static boolean isDoor(int nextPosition) {
		if(nextPosition >= 65 && nextPosition <= 70) return true;
		return false;
	}
	
	public static boolean isKey(int nextPosition) {
		if(nextPosition >= 97 && nextPosition <= 102) return true;
		return false;
	}
}

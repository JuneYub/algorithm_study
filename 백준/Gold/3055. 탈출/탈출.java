import java.util.*;
import java.io.*;


class Position {
    int x, y;
    int time;
    // 0 이면 물, 1이면 고슴도치
    int type;
    
    Position(int x, int y, int time, int type) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.type = type;
    } 
}

public class Main
{

    static char[][] map;
    static int n, m;
    static int gX, gY, endX, endY;
    
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
        
        map = new char[n][m];
        
        boolean[][] waterVisited = new boolean[n][m];
        Queue<Position> q = new ArrayDeque<>();
        
        for(int y = 0; y < n; y++) {
            String str = br.readLine();
            for(int x = 0; x < m; x++) {
                map[y][x] = str.charAt(x);
                
                if(map[y][x] == 'S') {
                    gX = x;
                    gY = y;
                }
                
                // 두더지 위치
                if(map[y][x] == 'D') {
                    endX = x;
                    endY = y;
                }
                
                // 초기 물 넣기
                if(map[y][x] == '*') {
                    q.add(new Position(x, y, 0, 0));
                    waterVisited[y][x] = true;
                }
                
            }
        }
        
        // 고슴도치 넣기
        q.add(new Position(gX, gY, 0, 1));
        
        while(!q.isEmpty()) {
            Position p = q.poll();
            
            // 내가 고슴도치인데 두더지굴에 도착한 경우
            if(p.type == 1 && endX == p.x && endY == p.y) {
                System.out.println(p.time);
                System.exit(0);
            }
            
            // 물인 경우
            if(p.type == 0) {
                for(int i = 0; i < 4; i++) {
                    int newX = p.x + dx[i];
                    int newY = p.y + dy[i];
                    
                    if(isRange(newX, newY) && (map[newY][newX] == '.' || map[newY][newX] == 'S')) {
                        q.add(new Position(newX, newY, p.time+1, 0));
                        map[newY][newX] = '*';
                    }
                }
            } else {
                for(int i = 0; i < 4; i++) {
                    int newX = p.x + dx[i];
                    int newY = p.y + dy[i];
                    
                    if(isRange(newX, newY) && (map[newY][newX] == '.' || map[newY][newX] == 'D')) {
                        q.add(new Position(newX, newY, p.time+1, 1));
                        map[newY][newX] = 'S';
                    }
                }
            }
        }
        System.out.println("KAKTUS");
        
	}
	
	public static boolean isRange(int x, int y) {
	    if(x < 0 || x >= m || y < 0 || y >= n) {
	        return false;
	    }
	    return true;
	}
}

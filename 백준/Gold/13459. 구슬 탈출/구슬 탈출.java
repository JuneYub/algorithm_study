import java.util.*;
import java.io.*;

class Info {
    int redX;
    int redY;
    int blueX;
    int blueY;
    int cnt;
    int beforeDirection;

    Info() {}

    Info (int redX, int redY, int blueX, int blueY, int cnt) {
        this.redX = redX;
        this.redY = redY;
        this.blueX = blueX;
        this.blueY = blueY;
        this.cnt = cnt;
    }
}

public class Main {

	static int n, m;
	static char[][] map;
	static boolean[][][][] visited;
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    
	    map = new char[n][m];
	    Info info = new Info();
	    visited = new boolean[10][10][10][10];
	    
	    for(int y = 0; y < n; y++) {
	        String str = br.readLine();
	        for(int x = 0; x < m; x++) {
	            map[y][x] = str.charAt(x);
	            if(map[y][x] == 'B') {
	                info.blueX = x;
	                info.blueY = y;
	            }
	            if(map[y][x] == 'R') {
	                info.redX = x;
	                info.redY = y;
	            }
	        }
	    }
	
	    Queue<Info> q = new LinkedList<>();
	    q.add(info);
	    info.cnt = 0;
	    visited[info.redY][info.redX][info.blueY][info.blueX] = true;
	    boolean flag = false;
	    
	    while(!q.isEmpty()) {
	        Info curInfo = q.poll();
	        
	        //System.out.println("빨간공 " + curInfo.redY+" " +curInfo.redX);
	        
	        // 12시부터 시계방향
	        int[] dx = {0, 1, 0, -1};
	        int[] dy = {-1, 0, 1, 0};
	        //System.out.println(curInfo.redY + " " + curInfo.redX);
	        
	        if(curInfo.cnt > 10) {
	            break;
	        }
	        
	        if(map[curInfo.redY][curInfo.redX] == 'O' && map[curInfo.blueY][curInfo.blueX] != 'O') {
	            flag = true;
	            break;
	        } else if(map[curInfo.redY][curInfo.redX] != 'O' && map[curInfo.blueY][curInfo.blueX] == 'O') {
	        	continue;
	        } else if (map[curInfo.redY][curInfo.redX] == 'O' && map[curInfo.blueY][curInfo.blueX] == 'O') {
	        	continue;
	        }
	        
	        for(int i = 0; i < 4; i++) {
	            
	            int curRedX = curInfo.redX;
	            int curRedY = curInfo.redY;
	            int curBlueX = curInfo.blueX;
	            int curBlueY = curInfo.blueY;
	            
                curRedX += dx[i];
                curRedY += dy[i];
        		curBlueX += dx[i];
                curBlueY += dy[i];
	            
	            while(true) {
	            	if(map[curRedY][curRedX] != '#' && map[curRedY][curRedX] != 'O') {
	                    curRedX += dx[i];
	                    curRedY += dy[i];
	            	}
	            	// 빨간 공 벽에 부딫쳤나?
	            	else {
	            		if(map[curRedY][curRedX] == '#') {
	                    curRedX -= dx[i];
	                    curRedY -= dy[i];
	            		}
	                    break;
	            	}
	            	
	            }
	            
	            while(true) {
	            	if(map[curBlueY][curBlueX] != '#' && map[curBlueY][curBlueX] != 'O') {
	            		curBlueX += dx[i];
	                    curBlueY += dy[i];
	            	}
	            	
	            	// 파란 공 벽에 부딫쳤나?
	            	else { 
	            		// 부딫치면 후진
	            		if(map[curBlueY][curBlueX] == '#') {
	            		curBlueX -= dx[i];
	                    curBlueY -= dy[i];
	            		}
	            		// 멈추기
	            		break;
	            	}
	            }     
	            
	            // 두 개가 동시에 같은 장소에 있으면 더 먼거리에 있는 녀석이 뒤로 이동
	            if(curRedX == curBlueX && curRedY == curBlueY) {
	            	if(map[curRedY][curRedX] != 'O') {
		                int redDistance = Math.abs( curInfo.redY - curRedY) + Math.abs( curInfo.redX - curRedX);
		                int blueDistance = Math.abs( curInfo.blueY - curBlueY) + Math.abs( curInfo.blueX - curBlueX);
		
		                if(redDistance > blueDistance) {
		                    curRedX -= dx[i];
		                    curRedY -= dy[i];
		                } else {
		                    curBlueX -= dx[i];
		                    curBlueY -= dy[i];
		                }
	            	}
	            }
		
	            if(!(visited[curRedY][curRedX][curBlueY][curBlueX])) {
	                visited[curRedY][curRedX][curBlueY][curBlueX] = true;
	                q.add(new Info(curRedX, curRedY, curBlueX, curBlueY, curInfo.cnt+1));
	            }
	    	}
	    }
	
	    if(!flag) {
	        System.out.println(0);
	    } else {
	        System.out.println(1);
	    }
	
	}
}
    


import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;


class Position {
	int x, y;
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class RGB {
	int x, y;
	char c;
	RGB(int x, int y, char c) {
		this.x =x;
		this.y =y;
		this.c =c;
	}
}

public class Main {
	
	public static boolean visited[][];
	public static List<Position> boom = new ArrayList<>();
	public static char[][] map = new char[12][6];
	public static int[] dx = {0, 1, 0, -1};
	public static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for(int y = 0; y < 12; y++) {
        	map[y] = br.readLine().toCharArray();
        }
        
        // 터지지 않을때까지 지속
        boolean isBoom = true;
        int ans = 0;
        while(isBoom) {
        	boom = new ArrayList<>();
        	visited = new boolean[12][6];
        	// 바닥 왼쪽부터 확인한다.
        	
        	la:for(int y = 11; y >= 0; y--) {
        		for(int x = 0; x < 6; x++) {
        			
        			if(!visited[y][x] && map[y][x] != '.') {
        				bfs(x, y);
        				
        			}
        			
        		}
        	}
        	
        	if(boom.size() >= 4) {
        		for(Position p : boom) {
        			map[p.y][p.x] = '*';
        		}
        		
//                for(int i = 0; i < 12; i++) {
//                	for(int j = 0; j < 6; j++) {
//                		System.out.print(map[i][j] + " ");
//                	}
//                	System.out.println();
//                }
//                System.out.println();
        		
        		// 중력
        		gravity();
        		
        		
//                for(int i = 0; i < 12; i++) {
//                	for(int j = 0; j < 6; j++) {
//                		System.out.print(map[i][j] + " ");
//                	}
//                	System.out.println();
//                }
//                System.out.println();
                
                ans++;
        	}
        	else {
        		break;
        		
        	}
        	
        }
        

        
        System.out.println(ans);

    }
    
    public static void bfs(int x, int y) {
    	char findChar = map[y][x];
    	List<Position> tmp = new ArrayList<>();
    	Queue<Position> q = new ArrayDeque<>();
    	q.add(new Position(x, y));
    	visited[y][x] = true;
    	tmp.add(new Position(x, y));
    	
    	while(!q.isEmpty()) {
    		Position p = q.poll();
    		
    		for(int i = 0; i < 4; i++) {
    			int newX = p.x + dx[i];
    			int newY = p.y + dy[i];
    			
    			if(!isRange(newX, newY)) continue;
    			
    			if(!visited[newY][newX] && map[newY][newX] == findChar) {
    				visited[newY][newX] = true;
    				q.add(new Position(newX, newY));
    				tmp.add(new Position(newX, newY));
    			}
    		}
    	}
    	
    	if(tmp.size()>=4) {
    		for(Position p : tmp) {
    			boom.add(p);
    		}
    	}
    	


    }
    
    public static void gravity() {
    	
    	for(int x = 0; x < 6; x++) {
    		
    		List<RGB> move = new ArrayList<>();
    		int boomCnt = 0;
    		for(int y = 0; y <= 11; y++) {
    			// 옮길 놈들은 일단 다 기록한다.
    			if(map[y][x] == 'R' || map[y][x] == 'G' || map[y][x] == 'B' || map[y][x] == 'P' ||  map[y][x] == 'Y') {
    				move.add(new RGB(x, y, map[y][x]));
    			}
    			
    			// 만약 터져서 빈 공간을 만나게 된다면 옮겨하는 횟수를 증가시키고 y를 올린다.
    			if(map[y][x] == '*') {
    				while(y < 12 && map[y][x] == '*') {
    					
    					// 터진 흔적은 빈공간으로 바꿔준다.
    					map[y][x] = '.';
    					boomCnt++;
    					y++;
    					// 이렇게 되면 y의 최종위치는 범위를 벗어났거나? 또는 rgb 글자 위에 있게 된다.
    				}
    				
    				// 비어있는 공간만큼 move리스트 녀석들을 아래로 내려준다.
    				// 제일 위에서부터 추가되었기 때문에 boomCnt만큼 y를 더해준다.
    				
    				for(int i = move.size()-1; i>=0; i--) {
    					map[move.get(i).y][move.get(i).x] = '.';
    					move.get(i).y += boomCnt;
    					map[move.get(i).y][move.get(i).x] = move.get(i).c;
    				}
    				boomCnt = 0;
    				y--;
    			}
    		}
    	}
    	
    }
    
    public static boolean isRange(int x, int y) {
    	if( x < 0 || x >= 6 || y < 0 || y >= 12) return false;
    	return true;
    }
    
}
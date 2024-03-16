import java.util.*;
import java.io.*;

class Position {
	int x,y;
	int distance;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(int x, int y, int distance) {
		this.x = x;
		this.y = y;
		this.distance = distance;
	}
	
	public String toString() {
		return y + " " + x;
		
	}
}

public class Main {

	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int islandIdx = 0;
	static int bridgeSize = Integer.MAX_VALUE;
	static Queue<Position> searchList = new ArrayDeque<>();
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	
    	map = new int[n][n];
    	visited = new boolean[n][n];
    	
    	StringTokenizer st;
    	for(int y = 0; y <n; y++) {
    		st = new StringTokenizer(br.readLine());
    		for(int x = 0; x <n; x++) {
    			map[y][x] = Integer.parseInt(st.nextToken());
    		}
    	}
    	
    	for(int y = 0; y < n; y++) {
    		for(int x = 0; x < n; x++) {
    			if(map[y][x] != 0 && !visited[y][x]) {
    				++islandIdx;
    				visited[y][x] = true;
    				map[y][x] = islandIdx;
    				divideIsland(x, y);  				
    			}
    		}
    	}
    	
    	while(!searchList.isEmpty()) {
    		visited = new boolean[n][n];
    		bfs(searchList.poll());
    	}
    	
    	/* 탐색 큐에 잘 들어갔는지 확인용
    	System.out.println(searchList.size());
    	while(!searchList.isEmpty()) {
    		System.out.println(searchList.poll().toString());
    	}
    	*/
    	
    	// 중간 체크용 섬 출력
//    	for(int y = 0; y < n; y++) {
//    		for(int x = 0; x < n; x++) {
//    			System.out.print(map[y][x] + " ");
//    		}
//    		System.out.println();
//    	}
    	
    	
    	System.out.println(bridgeSize);
    } // end of main
    
    public static void bfs(Position startPosition) {
    	Queue<Position> q = new ArrayDeque<>();
    	startPosition.distance = 0;
    	q.add(startPosition);
    	visited[startPosition.y][startPosition.y] = true;
    	
    	int startIdx = map[startPosition.y][startPosition.x];
    	
    	while(!q.isEmpty()) {
    		Position p = q.poll();
    		int x = p.x;
    		int y = p.y;
    		
    		for(int i = 0; i < 4; i++) {
        		int newX = x + dx[i];
        		int newY = y + dy[i];
        		
        		if(isRange(newX, newY)) {
        			
        			if(!visited[newY][newX] && map[newY][newX] == 0) {
        				visited[newY][newX] = true;
        				q.add(new Position(newX, newY, p.distance+1));
        			}
        			// 바다가 아니고 시작한 땅이 아닌 경우에 다리가 완성된 경우라서 지나온 거리를 비교해서 정답 갱신
        			if(map[newY][newX] != 0 && map[newY][newX] != startIdx) {
        				//System.out.println( startIdx+" "+ map[newY][newX] + " " + p.distance);
        				bridgeSize = Math.min(bridgeSize, p.distance);
        			}
        		}
        	}
    	}
    	
    }
    
    public static void divideIsland(int x, int y) {
    	// 섬을 나눌 때는 땅을 기준으로 4방탐색 후 땅이면 해당 지점에 방문해서 현재 땅의 인데를 표시하는 식이다.
    	// 4방 탐색을 할 때 바다가 한 곳이라도 포함된다면 탐색 리스트에 추가한다.
    	int oceanCnt = 0;
    	for(int i = 0; i < 4; i++) {
    		int newX = x + dx[i];
    		int newY = y + dy[i];
    		
    		if(isRange(newX, newY)) {
    			if(map[newY][newX] == 0) oceanCnt++;
    			
    			if(!visited[newY][newX] && map[newY][newX] == 1) {
    				visited[newY][newX] = true;
        			map[newY][newX] = islandIdx;
        			divideIsland(newX, newY);
    			}
    		}
    	}
    	
    	if(oceanCnt >= 1) {
    		searchList.add(new Position(x, y));
    	}
    	
    }
    
    public static boolean isRange(int x, int y) {
    	if(x < 0 || x>= n|| y < 0 || y >= n) {
    		return false;
    	}
    	return true;
    }

}
    	
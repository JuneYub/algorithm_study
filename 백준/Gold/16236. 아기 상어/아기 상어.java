
import java.util.*;
import java.io.*;



class Fish {
    int x;
    int y;
    int size;

    Fish(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }
}

class Position {
    int x;
    int y;
    int cnt;

    Position(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main {

static int n;
static int[][] arr;
static int babySize = 2;
static int eatCnt;
static int startX, startY;
public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    arr = new int[n][n];
    StringTokenizer st;
    
    List<Fish> fishList = new ArrayList<>();
    
    for(int y = 0; y < n; y++) {
        st = new StringTokenizer(br.readLine());
        for(int x = 0; x < n; x++) {
            arr[y][x] = Integer.parseInt(st.nextToken());
            
            if(arr[y][x] == 9) {
                startX = x;
                startY = y;
                arr[y][x] = 0;
            }
            
            if(arr[y][x] != 0 && arr[y][x] != 9) {
                fishList.add(new Fish(x, y, arr[y][x]));
            }
        }
    }
    
    // 먹을 물고기 정렬
    Collections.sort(fishList, new Comparator<Fish>() {

        @Override
        public int compare(Fish o1, Fish o2) {
            if(o1.size == o2.size) {
                if(o1.y == o2.y) {
                    return o1.x - o2.x;
                }
                return o1.y - o2.y;
            }
            return o1.size - o2.size;
        }
    });
    
    int time = 0;
    
    // 반복
    while(true) {
    	// 물고기가 없으면 종료
        if(fishList.size() == 0) {
            break;
        }
        
        // 먼저 먹을 물고기 사이즈 가져오기
        int eatSize = fishList.get(0).size;
        
        // 아기 상어보다 크면 못먹음
        if(eatSize >= babySize) {
            break;
        }
        
        Position position = bfs();
        if(position != null) {
        	eatCnt++;
            if(eatCnt == babySize) {
                babySize++;
                eatCnt = 0;
            }
            
            for(int i = 0; i < fishList.size(); i++) {
                if(fishList.get(i).x == position.x && fishList.get(i).y == position.y) {
                    fishList.remove(i);
                    arr[position.y][position.x] = 0;
                    break;
                }
            }
            
            startX = position.x;
            startY = position.y;
            time += position.cnt;
            
        } else {
        	break;
        }
    }
    System.out.println(time);
    
}

public static Position bfs() {
    Queue<Position> queue = new ArrayDeque<>();
    queue.add(new Position(startX, startY, 0));
    
    int minDistance = Integer.MAX_VALUE;
    int x = 0;
    int y = 0;
    
    boolean[][] visited = new boolean[n][n];
    visited[startY][startX] = true;
    
    while(!queue.isEmpty()) {
        Position position = queue.poll();
        
        // 내가 먹어야 하는 물고기라면
        if(arr[position.y][position.x] != 0 && arr[position.y][position.x] < babySize && position.cnt <= minDistance) {
        	// 거리가 같다면
        	if(position.cnt == minDistance) {
        		// 새로운 물고기가 더 높은 곳에 있다면 갱신
        		if(position.y < y) {
                    x = position.x;
                    y = position.y;
        		}
        		// 새로운 물고기가 같은 y에 있다면
        		if(position.y == y) {
        			// 새로운 물고기가 더 왼쪽에 있는 경우에 갱신
        			if(position.x < x) {
                        x = position.x;
                        y = position.y;
        			}
        		}
        	} else {
                minDistance = position.cnt;
                x = position.x;
                y = position.y;
        	}
        	

        }
        
        
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int cnt = position.cnt;
        
        for(int i = 0; i < 4; i++) {
            int newX = position.x + dx[i];
            int newY = position.y + dy[i];
            if(isRange(newX, newY) && arr[newY][newX] <= babySize && !visited[newY][newX]) {
                visited[newY][newX] = true;
                queue.add(new Position(newX, newY, cnt+1));
            }
        }
    }
    
    if(minDistance == Integer.MAX_VALUE) {
    	return null;
    }
    
    return new Position(x, y, minDistance);
    
}

public static boolean isRange(int x, int y) {
    if(x < 0 || x >= n || y < 0 || y >= n ) return false;
    return true;
}
    
    
}
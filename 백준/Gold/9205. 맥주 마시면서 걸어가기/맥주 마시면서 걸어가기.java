import java.util.*;
import java.io.*;


class Position {
    int x, y;
    
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    } 
}

public class Main
{
    static int n;
    static Position startPoint;
    static boolean[] visited;
    static List<Position> list;
    static Queue<Position> q;
    static boolean isFinish;
    
    public static boolean canGo(Position p, int i) {
        // 절대값( x - x1 ) + 절대값(y - y1) = 거리
        int distance = Math.abs(list.get(i).x - p.x) + Math.abs(list.get(i).y - p.y);
        if(distance <= 1000) return true;
        return false;
        
    }
    
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
		    n = Integer.parseInt(br.readLine());    
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    
		    // 초기화
		    list = new ArrayList<>();
		    q = new ArrayDeque<>();
		    
		    // 시작 지점 설정
		    int x = Integer.parseInt(st.nextToken());
		    int y = Integer.parseInt(st.nextToken());
		    startPoint = new Position(x, y);
		    for(int i = 0; i <= n; i++) {
		        st = new StringTokenizer(br.readLine());
		        x = Integer.parseInt(st.nextToken());
		        y = Integer.parseInt(st.nextToken());
		        list.add(new Position(x, y));
		    }
		    
		    // 방문 배열 초기화
		    visited = new boolean[n+1];
		    isFinish = false;
		    
		    q.add(startPoint);
		    // bfs
		    while(!q.isEmpty()) {
		        Position p = q.poll();
		        
		        if(p.x == list.get(n).x && p.y == list.get(n).y ) {
		            isFinish = true;
		            break;
		        }
		        
		        for(int i = 0; i <= n; i++) {
		            int newX = list.get(i).x;
		            int newY = list.get(i).y;
		            
		            if(!visited[i] && canGo(p, i)) {
		                //System.out.println(i);
		                q.add(list.get(i));
		                visited[i] = true;
		            }
		        }
		        
		    }
		   
		   
		    
		    if(isFinish) {
		        System.out.println("happy");
		    } else {
		        System.out.println("sad");
		    }
		}
	}
}

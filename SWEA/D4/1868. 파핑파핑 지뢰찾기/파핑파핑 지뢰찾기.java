/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.*;
import java.io.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */

class Position {
	int y;
	int x;
	
	Position(int y, int x ){
		this.y = y;
		this.x = x;
	}
}

public class Solution {

	static int n;
	static char[][] map;
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static boolean[][] visited;
	static boolean[][] finalVisited;
	static int cnt =  0;
	static Queue<Position> q;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int testCase = Integer.parseInt(br.readLine());
	    
	    for(int tc = 1; tc <= testCase; tc++) {
	    	
	    	n = Integer.parseInt(br.readLine());
	    	q = new ArrayDeque<>();
	    	map = new char[n][n];
	    	visited = new boolean[n][n];
	    	finalVisited = new boolean[n][n];
	    	cnt = 0;
	    	
	    	for(int y = 0 ; y < n; y++) {
	    		String str = br.readLine();
	    		for(int x = 0; x < n; x++) {
	    			map[y][x] = str.charAt(x);
	    		}
	    	}
	    	
	    	for(int y = 0 ; y < n; y++) {
	    		for(int x = 0; x < n; x++) {
	    			if(map[y][x] != '*' && !visited[y][x]) {
	    					
	    				int mineCnt = 0;
	    	    		for(int i = 0; i < 8; i++) {
	    	    			int newX = dx[i] + x;
	    	    			int newY = dy[i] + y;
	    	    			
	    	    			if(isRange(newY, newX) && map[newY][newX] == '*') {
	    	    				mineCnt++;
	    	    				break;
	    	    			}
	    	    		}
	    	    		
	    	    		if(mineCnt == 0) {
	    	    			q.add(new Position(y, x));
	    	    			cnt++;
	    	    			bfs();
	    	    		}
	    			}
	    			
	    			if(map[y][x] == '*') {
	    				finalVisited[y][x] = true;
	    			}
	    		}
	    	}
	    	
	    	for(int y = 0; y < n; y++) {
	    		
	    		for(int x = 0; x< n; x++) {
	    			if(!finalVisited[y][x]) {
	    				cnt++;
	    			}
	    		}
	    	}
	    	
	    	System.out.println("#"+tc+" "+cnt);
	    }
	    
	    
	} // end of main
	
	public static void bfs() {
    	while(!q.isEmpty()) {
    		Position p = q.poll();

    		finalVisited[p.y][p.x] = true;
    		
    		int mineCnt = 0;
    		for(int i = 0; i < 8; i++) {
    			int newX = dx[i] + p.x;
    			int newY = dy[i] + p.y;
    			
    			if(isRange(newY, newX) && map[newY][newX] == '*') {
    				mineCnt++;
    				break;
    			}
    		}
    		
    		if(mineCnt == 0 ) {
    			map[p.y][p.x] = 0;
    			map[p.y][p.x] = (char) ('0');
    			
        		// 사방탐색
        		for(int i = 0; i < 8; i++) {
        			int newX = dx[i] + p.x;
        			int newY = dy[i] + p.y;
        			
        			// 범위 내인지 확인, 지뢰이면 안되고, 방문한거면 안됨
        			if(isRange(newY, newX) && map[newY][newX] != '*' && !visited[newY][newX]) {
        				visited[newY][newX] = true;
        				// 큐에 추가
        				q.add(new Position(newY, newX));
        			}
        		}
    		}
    		
			

    	}
	}
	
	public static boolean isRange(int y, int x) {
		if(y < 0 || y >= n || x < 0 || x >= n) {
			return false;
		}
		return true;
	}
}
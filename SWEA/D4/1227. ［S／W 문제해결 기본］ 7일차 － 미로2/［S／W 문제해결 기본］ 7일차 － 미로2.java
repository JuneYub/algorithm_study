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

	static int[][] map;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    for(int tc = 1; tc <= 10; tc++) {
	    	int testCase = Integer.parseInt(br.readLine());
	    	map = new int[100][100];
	    	
	    	int startX = 0;
	    	int startY = 0;
	    	int endX = 0;
	    	int endY = 0;
	    	
	    	
	    	for(int y = 0; y < 100; y++) {
	    		String str = br.readLine();
	    		for(int x = 0; x < 100; x++) {		
	    			map[y][x] = str.charAt(x) - '0';
	    			if(map[y][x] == 2) {
	    				startX = x;
	    				startY = y;
	    			}
	    			if(map[y][x] == 3) {
	    				endX = x;
	    				endY = y;
	    			}
	    		}
	    	}

	    	Queue<Position> q = new ArrayDeque<>();
	    	q.add(new Position(startY, startX));
	    	boolean[][] visited = new boolean[100][100];
	    	boolean flag = false;
	    	while(!q.isEmpty()) {
	    		Position p = q.poll();
	    		
	    		if(p.x == endX && p.y == endY) {
	    			flag = true;
	    			break;
	    		}
	    		
	    		for(int i = 0; i < 4; i++) {
	    			int newX = dx[i] + p.x;
	    			int newY = dy[i] + p.y;
	    			
	    			if(isRange(newY, newX) && map[newY][newX] != 1 && !visited[newY][newX]) {
	    				visited[newY][newX] = true;
	    				q.add(new Position(newY, newX));
	    			}
	    		}
	    	}
	    	
	    	if(flag) {
	    		System.out.println("#" + testCase +" "+ 1);
	    	} else {
	    		System.out.println("#" + testCase +" "+ 0);
	    	}
	    	
	    	
	    	
	    }
	    
	    
	}
	
	public static boolean isRange(int y, int x) {
		if(y < 0 || y >= 100 || x < 0 || x >= 100) {
			return false;
		}
		return true;
	}
}
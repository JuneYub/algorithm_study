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

/**
*
*	작성자 : 박준엽
*	문제 : [D5] 최적경로 - 1247
*	제출 : 2024년 2월 14일
*	메모리: 18360 KB, 시간: 388 ms
*	아이디어 : 
*/


public class Solution {

	static int n;
	static int h, w;
	static char[][] map;
	static char[] oper;
	static int tankX, tankY;
	static int direction; // 12시 - 0, 3시 - 1, 6시 - 2, 9시 - 3
	static int[] dx = {0 , 1, 0, -1};
	static int[] dy = {-1 , 0, 1, 0};
	static char[] tankD = {'^', '>', 'v', '<'};
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int t = Integer.parseInt(br.readLine());
	    
	    for(int tc = 1; tc <= t; tc++) {
		    StringTokenizer st = new StringTokenizer(br.readLine());
		    h = Integer.parseInt(st.nextToken());
		    w = Integer.parseInt(st.nextToken());
		    
		    map = new char[h][w];
		    for(int y = 0; y < h; y++) {
		    	String str = br.readLine();
		    	for(int x = 0; x < w; x++) {
		    		map[y][x] = str.charAt(x);
		    		if(map[y][x] == '^' || map[y][x] == 'v' || map[y][x] == '>' || map[y][x] == '<') {
		    			tankX = x;
		    			tankY = y;
		    			setDirection(map[y][x]);
		    		}
		    	}
		    }
		    
		    n = Integer.parseInt(br.readLine());
		    oper = new char[n];
		    String str = br.readLine();
		    
		    for(int i = 0; i < n; i++) {
		    	oper[i] = str.charAt(i);
		    }
		    
		    for(int i = 0; i < n; i++) {
		    	if(oper[i] == 'U') {
		    		direction = 0;
		    		updateTankPostion(direction);
		    	}
		    	if(oper[i] == 'R') {
		    		direction = 1;
		    		updateTankPostion(direction);
		    	}
		    	if(oper[i] == 'D') {
		    		direction = 2;
		    		updateTankPostion(direction);
		    	}
		    	if(oper[i] == 'L') {
		    		direction = 3;
		    		updateTankPostion(direction);
		    	}
		    	
		    	if(oper[i] =='S') {
		    		shooting(tankY, tankX);
		    	}
		    }
		    System.out.print("#"+tc +" ");
		    for(int y = 0; y< h; y++) {
		    	
		    	for(int x = 0; x<w; x++) {
		    		System.out.print(map[y][x]);
		    	}
		    	System.out.println();
		    }
		    
	    } // end of for
	    
	}
	public static void updateTankPostion(int directionInput) {
		int newX = tankX + dx[directionInput];
		int newY = tankY + dy[directionInput];
		if(moveAbl(newX, newY)) {
			map[tankY][tankX] = '.';
			tankX = newX;
			tankY = newY;
			map[tankY][tankX] = tankD[directionInput];
		} else {
			map[tankY][tankX] = tankD[directionInput];
		}
	}
	
	public static boolean moveAbl(int x, int y) {
		if( isRange(y, x) && map[y][x] == '.') {
			return true;
		}
		return false;
	}
	
	public static void setDirection(char dir) {
		if(dir == '^') direction = 0;
		if(dir == '>') direction = 1;
		if(dir == 'v') direction = 2;
		if(dir == '<') direction = 3;;
	}
	
	public static void shooting(int y, int x) {
		int newX = x;
		int newY = y;
		
		while(true) {
			newX += dx[direction];
			newY += dy[direction];
			
			if(!isRange(newY, newX)) {
				break;
			}
			
			if(map[newY][newX] == '*') {
				map[newY][newX] = '.';
				break;
			}
			
			if(map[newY][newX] == '#') {
				break;
			}
			
		}
	}
	
	public static boolean isRange(int y, int x) {
		if(y < 0 || y >= h || x < 0 || x>= w) {
			return false;
		}
		return true;
	}
}

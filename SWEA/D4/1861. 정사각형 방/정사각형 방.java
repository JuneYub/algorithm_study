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
	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Solution {
   /**
	* 작성자 : 박준엽
	* 문제 : [D4] 사칙연산유효성검사 - 1233
	* 제출 : 2024년 2월 6일
	* 메모리: 19,440 KB, 시간: 125 ms, 코드길이: 2,466 Bytes
	* 아이디어 : 자식노드가 있으면 현재노드는 무조건 연산자, 자식 노드가 없으면 현재 노드는 무조건 숫자
	*/
	static int n;
	static int[][] map;
	static int roomNum, maxCnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			
			for(int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < n; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			roomNum = 0;
			maxCnt = Integer.MIN_VALUE;
			
			for(int y = 0; y < n; y++) {
				for(int x = 0; x < n; x++) {
					bfs(y, x);
				}
			}
			
			System.out.println("#"+tc+" " + roomNum +" "+maxCnt);
			
		}
		
	}
	
	public static boolean isRange(int y, int x) {
		if(x < 0 || x >= n || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
	
	public static boolean isMoveAbl(int y, int x, int curNum) {
		if(map[y][x] -1 == curNum ) return true;
		return false;
	}
	
	public static void bfs(int y, int x) {
		int cnt = 1;
		boolean[][] visited = new boolean[n][n];
		Queue<Position> q = new ArrayDeque<Position>();
		q.add(new Position(x, y));
		
		while(!q.isEmpty()) {
			
			Position curPosition = q.poll();
			int curNum = map[curPosition.y][curPosition.x];
			
			int[] dx = {0, 1, 0, -1};
			int[] dy = {-1, 0, 1, 0};
			
			// 12시부터 시계방향으로 탐색
			for(int i = 0; i < 4; i++) {
				int newX = curPosition.x + dx[i];
				int newY = curPosition.y + dy[i];
				if(isRange(newY, newX)) {
					if(isMoveAbl(newY, newX, curNum) && !visited[newY][newX]) {
						visited[newY][newX] = true;
						q.add(new Position(newX, newY));
						cnt++;
					}
				}
			}
		}
		if(cnt > maxCnt) {
			roomNum = map[y][x];
			maxCnt = cnt;
		}
		else if(cnt == maxCnt) {
			roomNum = Math.min(roomNum, map[y][x]);
		}
	}
	
}



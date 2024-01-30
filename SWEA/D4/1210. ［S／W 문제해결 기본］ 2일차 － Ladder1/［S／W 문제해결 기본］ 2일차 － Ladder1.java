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
import java.io.*;
import java.util.*;

public class Solution {

	static int[][] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for(int tc = 1; tc <= 10; tc++) {
			int test = Integer.parseInt(br.readLine());
			arr = new int[100][100];
			
			StringTokenizer st;
			for(int y = 0; y < 100; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < 100; x++) {
					arr[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 시작점
			int startPoint = -1;
			
			// 첫행을 돈다.
			for(int x = 0; x < 100; x++) {
				// 1인 경우에만 사다리를 탄다.
				if(arr[0][x] != 0) {
					startPoint = x;
//					System.out.println(game(startPoint));
					if(game(startPoint)) {
						System.out.println("#"+test+" "+startPoint);
					}
				}
			}
			
		}
	}
	
	
	static boolean game(int startPoint) {
		int x = startPoint;
		int y = 0;
		
		while(true) {
			// 떨어지기
			y += 1;
			if(isRange(x, y)) {
				
				if(arr[y][x] == 2) {
					return true;
				}
				
				// 오른쪽으로 길이 있네
				
				if(isRight(x+1, y)) {
					while(true) {
						if(isRight(x+1, y)) {
							x++;
							continue;
						}
						break;
					}
					continue;
				}

				if(isLeft(x-1, y)) {
					// 왼쪽으로 길이 있네
					while(true) {
						if(isLeft(x-1, y)) {
							x--;
							continue;
						}
						break;
					}
					continue;
				}

			}
			// 끝까지 왔는데 결국 못찾은 경우
			else {
				return false;
			}
		}
	}
	
	static boolean isRange(int x, int y) {
		if(x < 0 || x >= 100 || y < 0 || y >= 100) {
			return false;
		}
		return true;
	}
	
	static boolean isRight(int x, int y) {
		if(isRange(x, y) && arr[y][x] == 1) {
			return true;
		}
		return false;
	}
	
	static boolean isLeft(int x, int y) {
		if(isRange(x, y) && arr[y][x] == 1) {
			return true;
		}
		return false;
	}
}

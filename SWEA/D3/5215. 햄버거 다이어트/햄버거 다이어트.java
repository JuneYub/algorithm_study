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
class Solution
{
static int 재료의수, 제한칼로리, 점수합;
	static int[] 재료배열, 칼로리배열, result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			재료의수 = Integer.parseInt(st.nextToken());
			제한칼로리 = Integer.parseInt(st.nextToken());
			
			점수합 = 0;
			재료배열 = new int[재료의수];
			칼로리배열 = new int[재료의수];
			result = new int[재료의수];
			
			for(int i = 0; i < 재료의수; i++) {
				st = new StringTokenizer(br.readLine());
				재료배열[i] = Integer.parseInt(st.nextToken());
				칼로리배열[i] = Integer.parseInt(st.nextToken());
			}
			
			recursion(0, 0, 0);
			System.out.println("#"+tc+" "+점수합);
		}
	}
	
	public static void recursion(int depth, int score, int nowTotalCal) {
		if(nowTotalCal > 제한칼로리) {
			return;
		}
		
		if(score > 점수합) {
			점수합 = score;
		}
		
		if(depth >= 재료의수) {
			return;
		}
		
		recursion(depth+1, score+재료배열[depth], nowTotalCal + 칼로리배열[depth]);
		recursion(depth+1, score, nowTotalCal);
		
	}
    
}
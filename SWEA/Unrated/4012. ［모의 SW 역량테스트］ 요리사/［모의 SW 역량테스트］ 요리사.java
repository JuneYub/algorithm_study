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
	static int 재료의수, 최소값;
	static int[][] 재료맵;
	static boolean[] A음식구분;
	static int[] A음식재료;
	static int[] B음식재료;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			재료의수 = Integer.parseInt(br.readLine());
			
			재료맵 = new int[재료의수][재료의수];
			A음식구분 = new boolean[재료의수];
			A음식재료 = new int[재료의수/2];
			B음식재료 = new int[재료의수/2];
			최소값 = Integer.MAX_VALUE;
			
			StringTokenizer st;
			for(int y = 0; y < 재료의수; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x < 재료의수; x++) {
					재료맵[y][x] = Integer.parseInt(st.nextToken());
				}
			}
			
			recursion(0, 0);
			System.out.println("#"+tc+" "+최소값);
		}
	}
	
	public static void recursion(int depth, int startIdx) {
		if(depth == 재료의수/2) {
			
			int idx = 0;
			for(int i = 0; i < 재료의수; i++) {
				if(!A음식구분[i]) {
					B음식재료[idx++] = i;
				}
			}
			
			int food1 = 0;
			int food2 = 0;
			
			for(int i = 0; i < A음식재료.length; i++) {
				for(int j = i+1; j < A음식재료.length; j++) {
					food1 += 재료맵[A음식재료[i]][A음식재료[j]] + 재료맵[A음식재료[j]][A음식재료[i]]; 
				}
				
				for(int j = i+1; j < B음식재료.length; j++) {
					food2 += 재료맵[B음식재료[i]][B음식재료[j]] + 재료맵[B음식재료[j]][B음식재료[i]]; 
				}
			}
			
			최소값 = Math.min(최소값, Math.abs(food1 - food2));
			return;
		}
		
		for(int i = startIdx; i < 재료의수; i++) {
			if(!A음식구분[i]) {
				A음식구분[i] = true;
				A음식재료[depth] = i;
				recursion(depth+1, i+1);
				A음식구분[i] = false;
			}
		}
	}
}
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
		static int[] arr = new int[9];
	static int win, defeat;
	static int[] result;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			Set<Integer> set = new HashSet<>();
			for(int i = 0; i < 9; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				set.add(arr[i]);
			}
			
			result = new int[9];
			int idx = 0;
			for(int i = 1; i <= 18; i++) {
				if(!set.contains(i)) {
					result[idx++] = i;
				}
			}
			
			visited = new boolean[9];
			
			win = 0;
			defeat = 0;
			permutation(0, 0, 0);
			System.out.println("#"+tc+" "+defeat + " " + win);
			
		}

	}
	
	public static void permutation(int depth, int winCnt, int defeatCnt) {
		if(depth == 9) {
			if(winCnt > defeatCnt) win++;
			if(winCnt < defeatCnt) defeat++;
			return;
 		}
		for(int i = 0; i < 9; i++) {
			if(!visited[i]) {
				if(arr[depth] < result[i]) {
					visited[i] = true;
					permutation(depth+1, winCnt+arr[depth]+result[i], defeatCnt);
				}
				if(arr[depth] > result[i]) {
					visited[i] = true;
					permutation(depth+1, winCnt, defeatCnt+arr[depth]+result[i]);
				}
				visited[i] = false;
			}

		}
	}
}
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

public class Solution {

	static int n, m;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= t; tc++) {
			
			sb.append("#" +tc+ " ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			parents = new int[n+1];
			for(int i = 0; i <= n; i++) {
				parents[i] = i;
			}
			
			// 연산 수행
			for(int i = 0 ; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int oper = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				if(oper == 0) {
					union(a, b);
				}
				if(oper == 1) {
					if(find(a) != find(b)) {
						sb.append("0");
					} else {
						sb.append("1");
					}
				}
			}
			sb.append("\n");
		}
		
		System.out.print(sb.toString());
		
	} // end of main
	
	// b의 부모노드를 a의 부모노드로 바꾼다.
	public static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA == pB) {
			return;
		}
		parents[pB] = pA;
	}
	
	// 부모노드를 찾으러 감
	public static int find(int a) {
		if(parents[a] == a) return a;
		else {
			return parents[a] = find(parents[a]);
		}	
	}
}
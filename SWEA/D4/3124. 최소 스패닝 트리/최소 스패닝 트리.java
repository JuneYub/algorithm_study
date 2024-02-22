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

class Edge implements Comparable<Edge> {
	int from, to, weight;
	
	Edge(int from, int to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}
	
	@Override
	public int compareTo (Edge e) {
		
		return Integer.compare(this.weight, e.weight);
	}
	
}

public class Solution {
	
	static int testCase;
	static int n;
	static int v, e;
	static int[] parents;
	static List<Edge> list;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int tc = 1; tc <= t; tc++) {
			list = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			v = Integer.parseInt(st.nextToken());
			e = Integer.parseInt(st.nextToken());
			
			for(int i = 0; i < e; i++) {
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				list.add(new Edge(from, to, weight));
			}
			Collections.sort(list);
			
			make();
			
			int depth = 0;
			long minWeight = 0;
			for(int i = 0; i < list.size(); i++) {
				int from = list.get(i).from;
				int to = list.get(i).to;
				int weightTmp = list.get(i).weight;
				
				if(union(from, to)) {
					minWeight += weightTmp;
					if(++depth == v-1) break;
				}
			}
			
			
			System.out.println("#" + tc+ " " + minWeight);
		}
		
	}
	
	public static void make() {
		parents = new int[v+1];
		for(int i = 1; i <= v; i++) {
			parents[i] = i;
		}
	}
	
	public static boolean union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA == pB) {
			return false;
		}
		
		parents[pB] = pA;
		return true;
	}
	
	public static int find(int a) {
		if(a == parents[a]) return a;
		return parents[a] = find(parents[a]);
	}
}
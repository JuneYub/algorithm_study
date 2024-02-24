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

class Island implements Comparable<Island>{
	int num;
	int x;
	int y;
	double weight;
	
	Island(int num, int x, int y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(Island o) {
		return Double.compare(this.weight, o.weight);
	}
	
}

public class Solution {

	static List<Island> islands = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= t; tc++) {
			int islandCnt = Integer.parseInt(br.readLine());
			islands = new ArrayList<>();
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < islandCnt; i++) {
				int x = Integer.parseInt(st.nextToken());
				islands.add(new Island(i, x, 0));
			}
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < islandCnt; j++) {
				int y = Integer.parseInt(st.nextToken());
				islands.get(j).y = y;
			}
			
			double rate = Double.parseDouble(br.readLine());
			
			double[] length = new double[islandCnt];
			Arrays.fill(length, Double.MAX_VALUE);
			boolean[] visited = new boolean[islandCnt];
			PriorityQueue<Island> q = new PriorityQueue<>();
			
			q.add(new Island(0, islands.get(0).x, islands.get(0).y));
			length[0] = 0; 
			
			double ans = 0.0;
			int depth = 0;
			
			while(!q.isEmpty()) {
				Island island = q.poll();
				int x = island.x;
				int y = island.y;
				
				if(visited[island.num]) continue;
				visited[island.num] = true; 
				ans += island.weight;
				
				if(++depth == islandCnt) break;
				
				for(int i = 0; i < islandCnt; i++) {
					Island targetIsland = islands.get(i);
					if(!visited[i]) {
						Double calculatedLen = (Double) (Math.pow(Math.abs(targetIsland.x - x),2) + Math.pow(Math.abs(targetIsland.y - y), 2));
						calculatedLen *= rate;
						
						if(calculatedLen < length[i]) {
							length[i] = calculatedLen;
							Island addIsland = new Island(targetIsland.num, targetIsland.x, targetIsland.y);
							addIsland.weight = calculatedLen;
							q.add(addIsland);
						}
					}
				}
			}
			
			System.out.println("#"+tc+" "+Math.round(ans));
			
			
		}
	}
	
}
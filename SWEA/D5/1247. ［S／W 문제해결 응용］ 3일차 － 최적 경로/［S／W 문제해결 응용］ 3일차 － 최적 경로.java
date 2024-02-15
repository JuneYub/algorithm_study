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

class Client {
	int x, y;
	//int distance;
	public Client (int y, int x){
		this.x = x;
		this.y = y;
	}
}
public class Solution {
	static boolean[] visited;
	static int n, companyX, companyY, homeX, homeY;
	static int minDistance;
	static List<Client> list;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int tc = Integer.parseInt(br.readLine());
	    
	    for(int t = 1; t <= tc; t++ ) {
	    	n = Integer.parseInt(br.readLine());
	    	
	    	StringTokenizer st = new StringTokenizer(br.readLine());
	    	list = new ArrayList<>();
	    	companyY = Integer.parseInt(st.nextToken());
	    	companyX = Integer.parseInt(st.nextToken());
	    	homeY = Integer.parseInt(st.nextToken());
	    	homeX = Integer.parseInt(st.nextToken());
	    	
	    	
	    	for(int i = 0; i < n; i++) {
	    		int y = Integer.parseInt(st.nextToken());
	    		int x = Integer.parseInt(st.nextToken());
	    		Client c = new Client(y, x);
	    		
//	    		int distance = Math.abs(companyX - x) + Math.abs(companyY - y);
//	    		c.distance = distance;
	    		list.add(c);
	    	}
	    	
	    	minDistance = Integer.MAX_VALUE;
	    	visited = new boolean[n];
	    	
	    	dfs(0, 0, new Client(companyY, companyX));
	    	
	    	System.out.println("#"+ t+" "+ minDistance);
	    }
	} // end of public static void main(String args[]) throws Exception
	
	public static void dfs(int depth, int result, Client prev) {
		if(result > minDistance) {
			return;
		}
		
        
		if(depth == n) {
			int distance = Math.abs(homeX - prev.x) + Math.abs(homeY - prev.y);
			result += distance;
			minDistance = Math.min(minDistance, result);
			return;
		}
		
		for(int i = 0; i < n; i++) {
			if(!visited[i]) {
				visited[i] = true;
				int distance = Math.abs(list.get(i).x - prev.x) + Math.abs(list.get(i).y - prev.y);
				dfs(depth+1, result+distance, list.get(i));
				visited[i] = false;
			}
		}
	}
}


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

class Person {
	int name;
	int cnt;
	
	Person(int name, int cnt) {
		this.name = name;
		this.cnt = cnt;
	}
}

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t = 1; t <= 10; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int startNode = Integer.parseInt(st.nextToken());
			
			List<List<Integer>> graph = new ArrayList<>();
			
			for(int i = 0; i <= 100; i++) {
				graph.add(new ArrayList<>());
			}
			
			st = new StringTokenizer(br.readLine());
			
			for(int i = 0; i < n/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				graph.get(from).add(to);
				
			}
			
			List<Person> people = new ArrayList<>(); 
			Queue<Person> q = new ArrayDeque<>();
			boolean[] visited = new boolean[101];
			q.add(new Person(startNode, 0));
			
			while(!q.isEmpty()) {
				Person p = q.poll();
				
				// people 리스트의 사이즈와 비교하여 현재 person의 cnt에 해당하는 부분이 없으면 생성하고 추가한다.
				if(people.size() == p.cnt) {
					people.add(p);
				} else {
					if(people.get(p.cnt).name < p.name) {
						people.get(p.cnt).name = p.name;
					}
				}
				
				for(int i = 0; i < graph.get(p.name).size(); i++) {
					int nextPerson = graph.get(p.name).get(i);
					if(!visited[nextPerson]) {
						visited[nextPerson] = true;
						q.add(new Person(nextPerson, p.cnt+1));
					}
				}
			}
			System.out.println("#"+t+" "+people.get(people.size()-1).name);
			
		}
	}
	
}


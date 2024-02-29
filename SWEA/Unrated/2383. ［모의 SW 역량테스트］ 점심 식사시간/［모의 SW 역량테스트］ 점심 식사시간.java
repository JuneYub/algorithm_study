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

class Person {
	int x, y, distance;
	int moveDownTime = 1;
	int targetStair;
	
	Person (int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class Stair {
	int x, y;
	int downTime;
	Stair(int x, int y, int downTime) {
		this.x = x;
		this.y = y;
		this.downTime = downTime;
	}
}

public class Solution {

	static int[][] map;
	static boolean visited[];
	static int n;
	static List<Person> people;
	static List<Stair> stairs;
	static int ans;
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			map = new int[n][n];
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			ans = Integer.MAX_VALUE;
			
			StringTokenizer st;
			for(int y = 0; y < n; y++) {
				st = new StringTokenizer(br.readLine());
				for(int x = 0; x< n; x++) {
					map[y][x] = Integer.parseInt(st.nextToken());
					if(map[y][x] == 1) {
						people.add(new Person(x, y));
					}
					if(map[y][x] != 0 && map[y][x] != 1) {
						stairs.add(new Stair(x, y, map[y][x]));
					}
				}
			}
			
			visited = new boolean[people.size()];
			dfs(0);	
			System.out.println("#" +tc +" "+ ans);
		}
	}
	
	// 사람들을 계단 A와 계단 B로 분산시키기 10C5
	public static void dfs(int depth) {
		if(depth == people.size()) {
			// true인 경우 A계단, false인 경우 B계단
			simulation();
			
			return;
		}
		
		visited[depth] = true;
		dfs(depth+1);
		visited[depth] = false;
		dfs(depth+1);
	}
	
	// 계단을 내려가는데 총 걸리는 시간을 계산
	public static void simulation() {

		// 계단까지의 거리 계산
		for(int i = 0; i < people.size(); i++) {
			// A계단
			if(visited[i]) {
				people.get(i).distance = Math.abs( (people.get(i).x - stairs.get(0).x)) +  Math.abs( (people.get(i).y - stairs.get(0).y));
				people.get(i).targetStair = 0;
			}
			
			// B계단
			if(!visited[i]) {
				people.get(i).distance = Math.abs( (people.get(i).x - stairs.get(1).x)) +  Math.abs( (people.get(i).y - stairs.get(1).y));
				people.get(i).targetStair = 1;
			}
		}
		
		// 사람 목록을 복사한 후에 오름차순으로 정렬
		List<Person> copyPeople =  new ArrayList<>();
		for(int i = 0; i < people.size(); i++) {
			Person p = new Person(people.get(i).x, people.get(i).y);
			p.distance = people.get(i).distance;
			p.targetStair = people.get(i).targetStair;
			copyPeople.add(p);
		}
		
		
		Collections.sort(copyPeople, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.distance - o2.distance;
			}
		});
		
		// 계단 대기 큐
		Queue<Person> stairA = new ArrayDeque<>();
		Queue<Person> stairB = new ArrayDeque<>();
		
		// 계단 내려가는 리스트
		List<Person> moveDownA = new ArrayList<>();
		List<Person> moveDownB = new ArrayList<>();
		
		// while문으로 시간을 진행하면서 계단 내려가기 진행 
		int time = 0;
		while(true) {
			if(copyPeople.isEmpty() && stairA.isEmpty() && stairB.isEmpty() && moveDownA.isEmpty() && moveDownB.isEmpty()) {
				ans = Math.min(ans, time);
				break;
			}
			
			time++;
			// 계단을 내려간 시간이 계단 사이즈와 같다면 빼도록 한다.

			for(int i = 0; i < moveDownA.size(); i++) {
				if(moveDownA.get(i).moveDownTime == stairs.get(0).downTime) {
					moveDownA.remove(i);
					i--;
				}
			}
			for(int i = 0; i < moveDownA.size(); i++) {
				moveDownA.get(i).moveDownTime++;
			}
			
			for(int i = 0; i < moveDownB.size(); i++) {
				if(moveDownB.get(i).moveDownTime == stairs.get(1).downTime) {
					moveDownB.remove(i);
					i--;
				}
			}
			for(int i = 0; i < moveDownB.size(); i++) {
				moveDownB.get(i).moveDownTime++;
			}
			
			// 계단 대기줄에 있던 사람들이 계단으로 내려간다.
			while(true) {
				if(moveDownA.size() < 3 && !stairA.isEmpty()) {
					Person p = stairA.poll();
					moveDownA.add(p);
				} else {
					break;
				}
			}
			
			while(true) {
				if(moveDownB.size() < 3 && !stairB.isEmpty()) {
					Person p = stairB.poll();
					moveDownB.add(p);
				} else {
					break;
				}
			}
			
			// 사람들이 계단 대기줄까지 왔는지 체크한다.
			while(copyPeople.size() != 0 && copyPeople.get(0).distance == time) {
				// 타겟 계단 큐에다가 넣어주고 사람을 지워준다.
				if(copyPeople.get(0).targetStair == 0) {
					stairA.add(copyPeople.get(0));
					copyPeople.remove(0);
				}
				else if(copyPeople.get(0).targetStair == 1) {
					stairB.add(copyPeople.get(0));
					copyPeople.remove(0);
				}
			}
		}
		
	}
	


}


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

class AP {
	int x,y,c,p;
	
	AP(int x, int y, int c, int p) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.p = p;
	}
}

class Position {
	int x, y;
	int len;
	
	Position(int x, int y, int len) {
		this.x = x;
		this.y = y;
		this.len = len;
	}
}

public class Solution {

	static int m, a;
	static int[] aRoad;
	static int[] bRoad;
	static List<int[][]> mapList;
	static int totalCharge;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			m = Integer.parseInt(st.nextToken());
			a = Integer.parseInt(st.nextToken());
			totalCharge = 0;
			
			aRoad = new int[m];
			bRoad = new int[m];
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for(int i = 0; i < m; i++) {
				// 사용자의 이동 정보
				aRoad[i] = Integer.parseInt(st1.nextToken());
				bRoad[i] = Integer.parseInt(st2.nextToken());
			}
			
			// 배터리 충전소 저장
			List<AP> apList = new ArrayList<>();
			mapList = new ArrayList<>();
			for(int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				apList.add(new AP(x-1, y-1, c, p));
				
				
				int[][] outputMap = makeMap(apList.get(i));
				mapList.add(outputMap);
			}
			
			
			// 걷는다
			int userX = 0;
			int userY = 0;
			
			int user2X = 9;
			int user2Y = 9;
			
			// 처음 자리에서도 한번 해준다.
			compareBattery(userX, userY, user2X, user2Y);
			
			int[] dx = {0, 0, 1, 0, -1};
			int[] dy = {0, -1, 0, 1, 0};
			
			for(int i = 0; i < m; i++) {
				userX = userX + dx[ aRoad[i] ];
				userY = userY + dy[ aRoad[i] ];
				user2X = user2X + dx[ bRoad[i] ];
				user2Y = user2Y + dy[ bRoad[i] ];
				
				compareBattery(userX, userY, user2X, user2Y);
				
			}
			
			System.out.println("#"+tc+ " " + totalCharge);
			
		}
	} // end of main ======================

	// 유저의 배터리 비교
	public static void compareBattery(int userX, int userY, int user2X, int user2Y) {
		Set<Integer> aset = new HashSet<>();
		Set<Integer> bset = new HashSet<>();
		int[] batteryPower = new int[mapList.size()];
		
		// 유저 1과 유저2의 현재위치를 통해 배터리 위에 있으면 배터리의 종류를 set에 넣는다.
		for(int i = 0; i < mapList.size(); i++) {
			int[][] ac = mapList.get(i);
			
			if(ac[userY][userX] != 0) {
				batteryPower[i] = ac[userY][userX];
				aset.add(i);
			}
			if(ac[user2Y][user2X] != 0) {
				batteryPower[i] = ac[user2Y][user2X];
				bset.add(i);
			}
		}
		
		// 1. 둘 다 배터리를 선택할 수 있는 경우
		// 파워를 고르는 횟수가 더 적은 사람이 선택권을 갖는다.
		if(aset.size() != 0 && bset.size() != 0) {
			
			boolean 포함여부 = false;
			for(int i : aset) {
				if(bset.contains(i)) {
					포함여부 = true;
				}
			}
			
			if(포함여부) {
				// 포함되어있는 경우 가장 큰 갑과 가장 작은 값을 각각 구해서 모든 경우의 수를 비교해본다.
				int aset1stCharge = 0;
				int aset2ndCharge = 0;
				
				int bset1stCharge = 0;
				int bset2ndCharge = 0;
				
				int bigIdx = 0;
				Iterator<Integer> iter = aset.iterator();
				while(iter.hasNext()) {
					int tmpIdx = iter.next();
					if(aset1stCharge < batteryPower[tmpIdx]) {
						aset1stCharge = batteryPower[tmpIdx];
						bigIdx = tmpIdx;
					}
				}
				aset.remove(bigIdx);
				iter = aset.iterator();
				while(iter.hasNext()) {
					int tmpIdx = iter.next();
					if(aset2ndCharge < batteryPower[tmpIdx]) {
						aset2ndCharge = batteryPower[tmpIdx];
					}
				}
				
				bigIdx = 0;
				iter = bset.iterator();
				while(iter.hasNext()) {
					int tmpIdx = iter.next();
					if(bset1stCharge < batteryPower[tmpIdx]) {
						bset1stCharge = batteryPower[tmpIdx];
						bigIdx = tmpIdx;
					}
				}
				bset.remove(bigIdx);
				iter = bset.iterator();
				while(iter.hasNext()) {
					int tmpIdx = iter.next();
					if(bset2ndCharge < batteryPower[tmpIdx]) {
						bset2ndCharge = batteryPower[tmpIdx];
					}
				}
				
				if(aset1stCharge == bset1stCharge) {
					totalCharge += Math.max(aset1stCharge + bset2ndCharge,  bset1stCharge + aset2ndCharge);
				} else {
					totalCharge += (aset1stCharge + bset1stCharge);
				}
				
				
			} else {
				int aCharge = 0;
				int bCharge = 0;
				Iterator<Integer> iter = aset.iterator();
				while(iter.hasNext()) {
					aCharge = Math.max(aCharge, batteryPower[iter.next()]);
				}
				iter = bset.iterator();
				while(iter.hasNext()) {
					bCharge = Math.max(bCharge, batteryPower[iter.next()]);
				}
				
				totalCharge += (aCharge+bCharge);
			}
			
			
			
			
		} // end 둘다 선택권이 있는 경우
		
		// 2. A만 배터리를 선택할 수 있는 경우
		else if(aset.size() != 0) {
			int maxCharge = 0;
			Iterator<Integer> iter = aset.iterator();
			while(iter.hasNext()) {
				maxCharge = Math.max(maxCharge, batteryPower[iter.next()]);
			}
			totalCharge += maxCharge;
			//System.out.println("A만 배터리 충전 : " + maxCharge);
		}
		
		// 2. B만 배터리를 선택할 수 있는 경우
		else if(bset.size() != 0) {
			int maxCharge = 0;
			Iterator<Integer> iter = bset.iterator();
			while(iter.hasNext()) {
				maxCharge = Math.max(maxCharge, batteryPower[iter.next()]);
			}
			totalCharge += maxCharge;
			//System.out.println("B만 배터리 충전 : " + maxCharge);
		}
		else {
			//System.out.println("충전못함 =====");
		}
		
		//System.out.println("유저 1 포지션 : " + userY + " " + userX+ "유저 2 포지션 : " + user2Y + " " + user2X +  "총 충전량 : " + totalCharge);
	}
	
	public static int[][] makeMap(AP apInfo) {
		int[][] map = new int[10][10];
		
		Queue<Position> q = new ArrayDeque<>();
		int maxLen = apInfo.c;
		int power = apInfo.p;
		
		q.add(new Position(apInfo.x, apInfo.y, 0));

		
		// dx, dy
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};
		
		while(!q.isEmpty()) {
			Position p = q.poll();

			// 이미 방문한 곳이면 리턴
			if(map[p.y][p.x] != 0) continue;
			// 방문처리는 power로 한다.
			map[p.y][p.x] = power;
			// 근데 최대 거리라면 더 이상 나가지 않는다.
			if(p.len == maxLen) continue;
			
			for(int i = 0; i < 4; i++) {
				int newX = dx[i] + p.x;
				int newY = dy[i] + p.y;
				
				if(isRange(newX, newY) && map[newY][newX] == 0 ) {
					q.add(new Position(newX, newY, p.len + 1));
				}
			}
		}
		
		return map;
	} // end of makeMap ================
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= 10 || y < 0 || y >= 10) {
			return false;
		}
		return true;
	}

}


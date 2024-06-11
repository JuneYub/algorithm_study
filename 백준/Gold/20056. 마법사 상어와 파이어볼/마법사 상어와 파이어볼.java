
import java.util.*;
import java.io.*;

class Fire{
	int x, y, m, s, d;
	
	Fire(int x, int y, int m, int s, int d) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}

class Position {
	int x, y;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object o) {
		Position p = (Position) o;
		if(p.x == this.x && p.y == this.y) return true;
		return false;
	}
	
	public int hashCode() {
		return Objects.hash(x, y);
	}
}

public class Main {
	
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int n, m , k;
	static List<Fire> list = new ArrayList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		// y , x, 질량, 속력, 방향
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			list.add(new Fire(x, y, m, s, d));
		}
		
		
		// k만큼 파이어볼 이동
		for(int i = 0; i < k; i++) {
			move();
		}
		
		int ans = 0;
		for(Fire fire : list) {
			ans += fire.m;
		}
		System.out.println(ans);
	}
	
	public static void move() {
		
		// list를 순회하면 해당 방향으로 이동
		// 이때 이동방향 공식은 다음과 같다. (기존 x + dx[d] * s) % n
		
		for(Fire fire : list) {
			fire.x = (fire.x + dx[fire.d] * fire.s) % n;
			fire.y = (fire.y + dy[fire.d] * fire.s) % n;
			
			if(fire.x < 0) {
				fire.x += n;
			}
			
			if(fire.y < 0) {
				fire.y += n;
			}
		}
		
		// 같은 위치에 있는지 확인 방법
		Map<Position, List<Fire>> map = new HashMap<>();
		
		for(Fire fire : list) {
			Position pos = new Position(fire.x, fire.y);
			map.putIfAbsent(pos, new ArrayList<Fire>());
			map.get(pos).add(fire);
		}
		
		list = new ArrayList<>();
		
		for(Map.Entry<Position, List<Fire>> entry : map.entrySet()) {
			Position pos = entry.getKey();
			List<Fire> fireBalls = entry.getValue();
			
			if(fireBalls.size() >= 2) {
				
				int sumSpeed = 0;
				int sumMass = 0;
				int beforeDir = fireBalls.get(0).d;
				boolean isEqual = true;
				
				for(Fire fire : fireBalls) {
					sumSpeed += fire.s;
					sumMass += fire.m;
					
					// 하나라도 같지 않으면 false
					if(fire.d % 2 != beforeDir % 2) {
						isEqual = false;
					}
					
					beforeDir = fire.d;
				}
				
				if(sumMass / 5 > 0) {
					// 홀짝이 모두 같은 경우
					if(isEqual) {
						// x y m s d
						for(int j = 0; j <= 6; j+=2) {
							list.add(new Fire(pos.x, pos.y, sumMass/5, sumSpeed/fireBalls.size(), j));
						}
					} else {
						// x y m s d
						for(int j = 1; j <= 7; j+=2) {
							list.add(new Fire(pos.x, pos.y, sumMass/5, sumSpeed/fireBalls.size(), j));
						}
					}
				}
			} // 겹쳐진 파이어볼이 있는 경우의 수 끝
			
			else if(fireBalls.size() == 1) {
				list.add(fireBalls.get(0));
			} // 겹친 파이어볼이 없는 경우의 수
			
		}
		
		
	}
	
	
}

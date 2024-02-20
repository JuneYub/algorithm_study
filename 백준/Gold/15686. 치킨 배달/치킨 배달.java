
import java.util.*;
import java.io.*;


class Position {
	int x;
	int y;
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this);
	}
	@Override
	public boolean equals(Object obj) {
		if(this.x != ((Position)obj).x) return false;
		if(this.y != ((Position)obj).y) return false;
		return true;
	}
}

public class Main {

	static int n, m;
	static int[][] map;
	static List<Position> homes;
	static List<Position> bbqs;
	static List<Position> result;
	static boolean[] visited;
	static int cityLen = Integer.MAX_VALUE;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 지도
		map = new int[n][n];
		// 집 정보 리스트
		homes = new ArrayList<Position>();
		// 치킨집 위치 리스
		bbqs = new ArrayList<Position>();
		// m개만큼 뽑았을 때 리스트
		result = new ArrayList<Position>();
		
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x< n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
				// 1이면 집
				if(map[y][x] == 1) {
					homes.add(new Position(x, y));
				} else if(map[y][x] == 2) {
					// 2이면 치킨집
					bbqs.add(new Position(x, y));
				}
			}
		}
		
		// 방문처리 배열
		visited = new boolean[bbqs.size()];
		dfs(0, 0);
		System.out.println(cityLen);
		
	}
	
	/*
	 * depth는 m까지 증가한다.
	 * startIdx를 통해서 조합을 구현한다.
	 */
	public static void dfs(int depth, int startIdx) {
		if(depth == m) {
			int tmpCityLen = 0;
			// 선택된 치킨집과 집들 사이의 거리를 구한다.
			for(int i = 0; i < homes.size(); i++) {
				// 집에서 치킨집까지 가장 작은 거리 구할 변수
				int minLen = Integer.MAX_VALUE;
				// 치킨집을 순회한다.
				for(int j = 0; j < result.size(); j++) {
					int len = Math.abs(homes.get(i).x - result.get(j).x) + Math.abs(homes.get(i).y - result.get(j).y);
					minLen = Math.min(minLen, len);
				}
				tmpCityLen += minLen;
			}
			// 도시의 치킨거리
			cityLen = Math.min(cityLen, tmpCityLen);
			return;
		}
		
		for(int i = startIdx; i < bbqs.size(); i++) {

			result.add(bbqs.get(i));
			dfs(depth + 1, i+1);
			result.remove(result.size()-1);

		}
	}
}







import java.io.*;
import java.util.*;

public class Main {
	static int n;
	static int[][] map;
	static int[][] nextMap;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		StringTokenizer st;
		// map에 데이터를 받는다.
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		while(n != 1) {
			List<Integer> list = new ArrayList<>();
			for(int y = 0; y < n; y=y+2) {
				for(int x = 0; x < n; x=x+2) {
					list.add(search(x, y));
				}
			}
			n = n/2;
			map = new int[n][n];
			int cnt = 0;
			for(int y = 0; y < n; y++) {
				for(int x = 0; x < n; x++) {
					map[y][x] = list.get(cnt++);
				}
			}
		}
		System.out.println(map[0][0]);
	}
	
	public static int search(int x, int y) {
		int a1 = map[y][x];
		int a2 = map[y][x+1];
		int a3 = map[y+1][x];
		int a4 = map[y+1][x+1];
		
		List<Integer> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		
		Collections.sort(list);
		return list.get(2);
	}
	
}
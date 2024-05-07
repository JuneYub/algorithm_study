import java.io.*;
import java.util.*;

public class Main {

	static int m, n;
	static int[][] map;
	static int dy[] = { -1, -1, 0 };
	static int dx[] = { -1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());

		map = new int[m][n];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {
					map[i][j] = 1;
				}else {
					map[i][j] = 0;
				}
			}
		}


		for (int y = 1; y < m; y++) {
			for (int x = 1; x < n; x++) {
				if (map[y][x] != 0) {
					boolean flag = true;
					int value = Integer.MAX_VALUE;
					for (int i = 0; i < 3; i++) {
						int ny = y + dy[i];
						int nx = x + dx[i];
						if (ny < 0 || nx < 0 || ny >= m || nx >= n) continue;
						if(map[ny][nx] == 0) {
							value = 1;
							flag = false;
							break;
						}
						value = Math.min(value, map[ny][nx]);
					}
					if(flag) {
						map[y][x] = value+1;						
					}else {
						map[y][x] = value;
					}
				}
			}
		}
		
		int ans = 0;
		for(int i=0; i<m; i++) {
			for(int j=0; j<n; j++) {
				ans = Math.max(ans, map[i][j]);
			}
		}
		
		System.out.println(ans);
	}

}
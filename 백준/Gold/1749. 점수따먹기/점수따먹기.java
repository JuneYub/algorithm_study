
import java.util.*;
import java.io.*;

public class Main {
	
	static int n,m;
	static int[][] map;
	static int[][] sumMap;
	public static void main(String[] args) throws Exception {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	StringTokenizer st = new StringTokenizer(br.readLine());
	n = Integer.parseInt(st.nextToken());
	m = Integer.parseInt(st.nextToken());
	
	map = new int[n+1][m+1];
	sumMap = new int[n+1][m+1];
	
	
	for(int y = 1; y <= n; y++) {
		st = new StringTokenizer(br.readLine());
		for(int x = 1; x <= m; x++) {
			map[y][x] = Integer.parseInt(st.nextToken());
		}
	}
	
	for(int i = 1; i <= n; i++) {
		for(int j = 1; j<= m; j++) {
			// 바로 왼쪽 + 바로 위 - 좌측 대각선 + 현재지점
			sumMap[i][j] = map[i][j] + sumMap[i-1][j] + sumMap[i][j-1] - sumMap[i-1][j-1];
		}
	}
	
	int result = Integer.MIN_VALUE;
    for(int y1 = 1; y1 <= n; y1++) {
        for(int x1 = 1; x1 <= m; x1++) {
            for(int y2 = y1; y2 <= n; y2++) {
                for(int x2 = x1; x2 <= m; x2++) {
                    int tmp = sumMap[y2][x2] - sumMap[y1-1][x2] - sumMap[y2][x1-1] + sumMap[y1-1][x1-1];
                    result = Math.max(result, tmp);
                }
            }
        }
    }
	
	System.out.println(result);
	}
	
	
}

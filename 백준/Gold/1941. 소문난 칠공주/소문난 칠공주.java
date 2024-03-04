import java.util.*;
import java.io.*;
public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		char[][] map = new char[5][5];
		
		for(int i = 0; i < 5; i++) {
			String s = bf.readLine();
			for(int j = 0; j < 5; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		int[] comb = new int[25];
		for(int i = 24; i > 17; i--) {
			comb[i] = 1;
		}
		
		int cnt = 0;
		
		do {
			int[][] arr = new int[5][5];
			Pair start = null;

			int y = 0;
			for(int i = 0; i < 25; i++) {
				if(comb[i] == 1) {
					if(map[i / 5][i % 5] == 'Y') {
						y++;
					}
					arr[i/5][i%5] = 1;
					
					start = new Pair(i/5, i%5);
				}
			}
			
			if(y > 3) continue;
			else {
				Queue<Pair> q = new LinkedList<>();
				q.add(start); arr[start.x][start.y] = 0;
				
				int iter = 0;
				while(!q.isEmpty()) {
					Pair cur = q.poll();
					
					int[] dx = {1, 0, -1, 0};
					int[] dy = {0, 1, 0, -1};
					
					for(int i = 0; i < 4; i++) {
						int nx = cur.x + dx[i];
						int ny = cur.y + dy[i];
						
						if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
							if(arr[nx][ny] == 1) {
								arr[nx][ny] = 0;
								q.add(new Pair(nx, ny));
							}
						}
					}
					
					iter++;
				}
				
				if(iter == 7) {
					cnt++;
					
//					System.out.println(Arrays.toString(comb));
				}
			}
			
			
		}while(np(comb));
		
		System.out.println(cnt);
	}

	
	public static boolean np(int[] p) {
		int n = p.length;
		
		int i = n-1;
		while(i > 0 && p[i] <= p[i-1]) i--;
		if(i == 0) return false;
		
		int j = n-1;
		while(p[j] <= p[i-1]) j--;
		
		int temp = p[i-1];
		p[i-1] = p[j];
		p[j] = temp;
		
		Arrays.sort(p, i, n);
		
		return true;
	}
	
	private static class Pair{
		int x; int y;
		Pair(int x, int y){
			this.x = x; this.y = y;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class xy {
	int x;
	int y;
	
	xy(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int n, m;
	static int ans = 0;
	static int[][] map;
	static int[] Xmove = {-1, 0, 1, 0};
	static int[] Ymove = {0, 1, 0, -1}; // 12 3 6 9 시계방향
	static boolean[][] visited;
	

	
	public static boolean isRange(int x, int y) {
		if(0 > x || x >= n || 0 > y || y >= m) return false;
		
		return true;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[n][m];
		visited = new boolean[n][m];
		
		Queue<xy> queue = new LinkedList<xy>();
		
		for(int i = 0; i < n; i++) {
			
			String str = br.readLine();
			
			for(int j = 0; j < m; j++) {
				
				map[i][j] = (int)(str.charAt(j)) - '0';
				visited[i][j] = true;
			}
		}
		
		int ans = 1;
		visited[0][0] = false;
		queue.offer(new xy(0, 0));
		boolean flag = false;
		
		while(!queue.isEmpty()) {
			int sz = queue.size(); // 큐를 통해 최단거리를 구하는 방법 = > 먼저 큐의 사이즈를 정하고 사이즈만큼 돌면 카운트한다. 
			
			for(int i = 0; i < sz; i++) {
				
				int x = queue.peek().x;
				int y = queue.peek().y;
				queue.poll();
				
				if(x == n-1 && y == m-1 ) {
					System.out.println(ans);
					
				}
				
				for(int j = 0; j < 4; j++) {
					int newX = x + Xmove[j];
					int newY = y + Ymove[j];
					
					if(isRange(newX, newY) && map[newX][newY] == 1 && visited[newX][newY]) {
						queue.offer(new xy(newX, newY));
						visited[newX][newY] = false;
					}
				}
			}
			
			ans++;
		}
	}
}
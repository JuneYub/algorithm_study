import java.io.*;
import java.util.*;

public class Main {

	static class Point {
		int idx;
		int dist;

		public Point(int idx, int dist) {
			super();
			this.idx = idx;
			this.dist = dist;
		}

	}

	static int n, m;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[201][n + 1];
		for (int i = 0; i < m; i++) {
			int idx = Integer.parseInt(br.readLine());
			for(int dist=1; dist<=200; dist++) {
				visited[dist][idx] = true;
			}
		}

		System.out.println(bfs());
	}

	static int bfs() {
		if(visited[1][2]) return -1;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(2, 1));
		int cnt = 0;
		while (q.size() > 0) {
			cnt++;
			int qSize = q.size();
			for (int i = 0; i < qSize; i++) {
				Point now = q.poll();
				int curIdx = now.idx;
				int moveDist = now.dist;
				if(curIdx == n) {
					return cnt;
				}

				for (int it : new int[] { moveDist + 1, moveDist, moveDist - 1 }) {
					if(it <= 0) continue;
					int nextIdx = curIdx + it;
					if (nextIdx > n || nextIdx <= 0) continue;
					if (visited[it][nextIdx]) continue;
					visited[it][nextIdx] = true; 
					q.add(new Point(nextIdx, it));

				}
			}
		}
		return -1;
	}
}
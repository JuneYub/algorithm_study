import java.util.*;
import java.io.*;


public class Main {
	
	static List<Integer>[] list;
	static int[] depth;
	static int[][] parent;
	static int LOG;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		// 밑이 2인 로그로 변환한 후 올림하여 정수로 변환시켜 가장 큰 깊이에서의 로그 값을 구함
		LOG = (int) Math.ceil(Math.log(n) / Math.log(2));
		
		parent = new int[n+1][LOG];
		depth = new int[n+1];
		list = new ArrayList[n+1];
		for(int i = 1; i < n+1; i++) {
			list[i] = new ArrayList<>();
		}
		
		StringTokenizer st;
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		init(1, 1);
		
		// j 는 1부터 최대 깊이 로그 직전까지의 부모노드를 세팅해준다. (j==0 은 바로 위에 부모 노드임)
		for(int j = 1; j < LOG; j++) {
			for(int i = 1; i <= n; i++) {
				if(parent[i][j-1] != 0) {
					// parent[1][1] = parent[ parent[1][0] ][0] 1번 노드의 2^1 부모는 1번 노드의 바로 윗 부모의 바로 윗 부모이다.
					parent[i][j] = parent[parent[i][j-1]][j-1];
				}
			}
		}
		
		
		StringBuilder sb = new StringBuilder();
		int m = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(lca(a,b)).append("\n");
		}
		System.out.println(sb.toString());
		
	}
	
	// 높이 배열과 부모 배열을 초기화 하는 함수
	static void init(int current, int d) {
		// 현재 노드의 깊이를 지정한다. 루트노드는 1이다.
		depth[current] = d;
		
		// 현재 노드 아래에 연결된 노드를 돌면서 다시 세팅한다.
		for(int next : list[current]) {
			// 연결된 노드의 깊이가 세팅되어 있지 않다면
			if(depth[next] == 0) {
				// 연결된 노드의 i==0 부모로 현재 노드를 세팅해준다.
				parent[next][0] = current;
				init(next, d+1);
			}
		}
		
	}
	
	// 최소 공통 노드를 찾는 함수
	static int lca(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		
		if(ah < bh) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		
		for(int i = LOG-1; i>=0; i--) {
			if(Math.pow(2,  i) <= depth[a] - depth[b] ) {
				a = parent[a][i];
			}
		}
		
		if(a==b) return a;
		
		for(int i=LOG-1; i >= 0; i--) {
			if(parent[a][i] != parent[b][i]) {
				a = parent[a][i];
				b = parent[b][i];
			}
		}
		return parent[a][0];
		
	}
	
}

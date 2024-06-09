import java.util.*;
import java.io.*;


public class Main {
	
	static List<Integer>[] list;
	static int[] parent, depth;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		parent = new int[n+1];
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
		
		init(1, 1, 0);
		
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
	static void init(int current, int height, int parentNum) {
		// 현재 노드의 깊이를 지정한다. 루트노드는 1이다.
		depth[current] = height;
		// 현재 노드의 부모 노드를 지정한다. 루트 노드는 0 이다.
		parent[current] = parentNum;
		
		// 현재 노드 아래에 연결된 노드를 돌면서 다시 세팅한다.
		for(int next : list[current]) {
			// 연결된 노드가 부모노드와 같지 않다면
			if(next != parentNum) {
				// 연결된 노드, 깊이 1 더해주고, 부모노드는 지금 노드로 세팅해서 초기화
				init(next, height+1, current);
			}
		}
		
	}
	
	// 최소 공통 노드를 찾는 함수
	static int lca(int a, int b) {
		int ah = depth[a];
		int bh = depth[b];
		
		// a 와 b의 높이 맞추기
		while(ah > bh) {
			a = parent[a];
			ah--;
		}
		while(ah < bh) {
			b = parent[b];
			bh--;
		}
		
		// 높이가 같아지면 한칸씩 위로 올림
		while(a!=b) {
			a = parent[a];
			b = parent[b];
		}
		return a;
		
	}
	
}

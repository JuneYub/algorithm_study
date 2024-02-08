
import java.util.*;
import java.io.*;


public class Main {

	static int n;
	static ArrayList<ArrayList<Integer>> graph;
	static boolean[] visited;
	static int[] parents;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	graph = new ArrayList<>();
    	// 트리 초기화 ( ArrayList에 add를 총 n번해줘야함 그래야지 0번부터 n번까지 생성가능, 0은 안쓴다.)
    	for(int i = 0; i <= n; i++) {
    		graph.add(new ArrayList<Integer>());
    	}
    	
    	// 방문배열
    	visited = new boolean[n+1];
    	// 부모 저장 배열
    	parents = new int[n+1];
    	
    	StringTokenizer st; 
    	for(int i = 0; i < n-1; i++) {
    		st = new StringTokenizer(br.readLine());
    		int node1 = Integer.parseInt(st.nextToken());
    		int node2 = Integer.parseInt(st.nextToken());
    		
    		graph.get(node1).add(node2);
    		graph.get(node2).add(node1);
    	}
    	
    	dfs(1);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 2; i < parents.length; i++) {
			bw.write(parents[i]+"");
			bw.newLine();
		}
		bw.flush();
    }
    
    // dfs 메서드 파라미터로 현재 노드를 받는다.
    public static void dfs(int curNode) {
    	// 현재 노드와 이어진 노드들을 찾는다.
    	for(int i = 0; i < graph.get(curNode).size(); i++) {
    		
    		// 루트 노드부터 dfs탐색을 따라 노드들을 쭉 탐색하는데
    		// 부모배열에 자식노드 index에 현재 노드(부모)를 저장한다.
    		int child = graph.get(curNode).get(i);
    		if(!visited[child]) {
    			
    			parents[child] = curNode;
    			visited[child] = true;
    			dfs(child);
    		}
    	}
    }

}
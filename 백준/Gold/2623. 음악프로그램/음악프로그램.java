import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static List<ArrayList<Integer>> graph;
	static int[] edgeCnt;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		edgeCnt = new int[n+1];
		
		graph = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			
			int[] arr = new int[num];
			
			for(int j = 0; j < num; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			for(int j = 0; j < num-1; j++) {
				graph.get(arr[j]).add(arr[j+1]);
				edgeCnt[arr[j+1]]++;
			}			
		}
		
		
		Queue<Integer> q = new ArrayDeque<>();
		for(int i = 1; i <= n; i++) {
			if(edgeCnt[i] == 0) {
				q.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!q.isEmpty()) {
			
			int curNum = q.poll();
			sb.append(curNum + " ").append("\n");
			
			for(int i = 0; i < graph.get(curNum).size(); i++) {
				int node = graph.get(curNum).get(i);
				edgeCnt[node]--;
				if(edgeCnt[node] == 0) {
					q.add(node);
				}
			}
		}
		
		boolean flag = false;
		for(int i = 1; i <= n; i++) {
			if(edgeCnt[i] != 0) {
				flag = true;
				break;
			}
		}
		
		if(flag) {
			System.out.println(0);
		} else {
			System.out.println(sb.toString());
		}
	}
}
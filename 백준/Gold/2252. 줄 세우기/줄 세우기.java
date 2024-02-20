import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static List<ArrayList<Integer>> graph;
	static int[] arr;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[n+1];
		
		graph = new ArrayList<ArrayList<Integer>>();
		
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			arr[b]++;
		}
		
		Queue<Integer> q = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= n; i++) {
			if(arr[i] == 0) {
				q.add(i);
				arr[i] = -1;
			}
		}
		
		while(!q.isEmpty()) {
			int person = q.poll();
			sb.append(person + " ");
			
			for(int i = 0; i < graph.get(person).size(); i++) {
				int num = graph.get(person).get(i);
				arr[num]--;
				if(arr[num] == 0) {
					q.add(num);
				}
			}
		}
		
		System.out.println(sb.toString());
	}
}

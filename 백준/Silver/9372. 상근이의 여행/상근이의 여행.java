
import java.util.*;
import java.io.*;

class Airplane {
	int end;
	
	Airplane(int end) {
		this.end = end;
	}
	
	
}
public class Main
{

	static int n, m;
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 1; tc <= testCase; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			
			List<List<Integer>> graph = new ArrayList<>();
			for(int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				graph.get(start).add(end);
				graph.get(end).add(start);
			}
			
			boolean[] visited = new boolean[n+1];
			Queue<Airplane> q = new ArrayDeque<>();
			q.add(new Airplane(1));
			visited[1] = true;
			int visitedCountry = 1;
			int cnt = 0;
			
			while(!q.isEmpty()) {
				Airplane air = q.poll();
				
				if(visitedCountry == n) {
					System.out.println(cnt);
					break;
				}
				
				for(int j = 0; j < graph.get(air.end).size(); j++) {
					int end = graph.get(air.end).get(j);
					if(!visited[end]) {
						visited[end] = true;
						q.add(new Airplane(end));
						visitedCountry++;
						cnt++;
					}
				}
				
			}
			
		}
	}
	
} 	

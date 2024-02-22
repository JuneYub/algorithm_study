import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static int[] popu;
    static boolean bFlag;
    static int 기저조건;
    static int ans = Integer.MAX_VALUE;
    
    static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        popu = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            popu[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int nearNum = Integer.parseInt(st.nextToken());

            for(int j = 0; j < nearNum; j++) {
                graph.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        // m 만큼 탐색하고 인구수를 합친 값과 탐색하지 않은 곳의 인구수를 비교한다.

        for(int i = 1; i <= n/2; i++) {
            boolean visited[] = new boolean[n+1];
            dfs(0, i, 1, visited);
        }
        
        if(ans == Integer.MAX_VALUE) {
        	System.out.println(-1);
        } else {
        	System.out.println(ans);
        }

	} // end of main

	public static void dfs(int depth, int m, int startIdx, boolean[] visited) {
	    if(m == depth) {
	         
	        List<Integer> teamA = new ArrayList<>();
	        List<Integer> teamB = new ArrayList<>();
	        int totalA = 0;
	        int totalB = 0;
	        
	        for(int i = 1; i <= n; i++) {
	            if(!visited[i]) {
	                teamA.add(i);
	                totalA += popu[i];
	            }
	            else {
	                teamB.add(i);
	                totalB += popu[i];
	            }
	        }
	        
	        if(bfs(teamA) && bfs(teamB)) {

	        	ans = Math.min(ans, Math.abs(totalA - totalB));
	        }
	        
	        return;
	    }
	    
	    for(int i = startIdx; i < graph.size(); i++) {
	        if(!visited[i]) {
	            visited[i] = true;
	            dfs(depth+1, m, startIdx+1, visited);
	            visited[i] = false;
	        }
	        
	    }

	    
	} // end of dfs
	
	public static boolean bfs(List<Integer> team) {
		boolean visited[] = new boolean[n+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.add(team.get(0));
		visited[team.get(0)] = true;
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int num = q.poll();
			cnt++;
			
			if(cnt == team.size()) {
				return true;
			}
			
			for(int i = 0; i < graph.get(num).size(); i++) {
				int nextNum = graph.get(num).get(i);
				if(!visited[nextNum] && team.contains(nextNum)) {
					visited[nextNum] = true;
					q.add(nextNum);
				}
			}
		}
		
		return false;
	} // end of bfs
}

import java.util.*;
import java.io.*;

class Bus {
	int start, end, money;
	Bus(int start, int end, int money) {
		this.start = start;
		this.end = end;
		this.money = money;
	}
}

public class Main {

	static int m, n;
	static int startCity, endCity;
	static List<Bus>[] graph;
	static long[] minEdgeArr;
	static int INF = Integer.MAX_VALUE;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	
    	// 도시의 숫자만큼 그래프를 초기화 해준다.
    	graph = new List[n+1];
    	for(int i = 0; i <= n; i++) {
    		graph[i] = new ArrayList<Bus>();
    	}
    	
    	// 버스 노선을 그래프에 추가한다.
    	StringTokenizer st;
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		int money = Integer.parseInt(st.nextToken());
    		graph[start].add(new Bus(start, end, money));
    	}
    	
    	st = new StringTokenizer(br.readLine());
    	startCity = Integer.parseInt(st.nextToken());
    	endCity = Integer.parseInt(st.nextToken());
    	
    	// 해당 노드로 가는 가중치 배열을 만든다.
    	minEdgeArr = new long[n+1];
    	// 노드로 가는 모든 길에 최대 값을 부여한다.
    	Arrays.fill(minEdgeArr, INF);
    	
    	// 시작 노드 설정
    	minEdgeArr[startCity] = 0;
    	
    	// 노드 방문처리 배열 설정
    	boolean[] visited = new boolean[n+1];
    	
    	// 노드의 개수만큼 반복
    	for(int i = 1; i <= n; i++) {
    		
    		long minValue = INF;
    		int minIdx = 1;
    		// 방문하지 않는 노드 중 해당과 노드로 가는 가장 적은 값을 찾는다.(1부터 시작)
    		for(int j = 1; j < minEdgeArr.length; j++) {
    			if(!visited[j] && minValue > minEdgeArr[j]) {
    				minIdx = j;
    				minValue = minEdgeArr[j];
    			}
    		}
    		
    		visited[minIdx] = true;
    		
    		// 노드로 가는 가중치 배열을 갱신한다.
    		for(int j = 0; j < graph[minIdx].size(); j++) {
    			int end = graph[minIdx].get(j).end;
    			int money = graph[minIdx].get(j).money;
    			minEdgeArr[end] = Math.min(minEdgeArr[end], money+minEdgeArr[minIdx]);
    		}
    		
    	}
    	
    	System.out.println(minEdgeArr[endCity]);

    }
    

}
    	
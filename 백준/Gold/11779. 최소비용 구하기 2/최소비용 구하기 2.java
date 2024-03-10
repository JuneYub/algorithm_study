
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

	static int n,m;
	static List<Bus>[] graph;
	static long[] minMoneyArr;
	static int startCity, endCity;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	n = Integer.parseInt(br.readLine());
    	m = Integer.parseInt(br.readLine());
    	
    	// 그래프 초기화
    	graph = new List[n+1];
    	for(int i = 0; i <= n; i++) {
    		graph[i] = new ArrayList<>();
    	}
    	
    	// 버스 정보 추가
    	StringTokenizer st;
    	for(int i = 0; i < m; i++) {
    		st = new StringTokenizer(br.readLine());
    		int start = Integer.parseInt(st.nextToken());
    		int end = Integer.parseInt(st.nextToken());
    		int money = Integer.parseInt(st.nextToken());
    		
    		graph[start].add(new Bus(start, end, money));
    		
    	}
    	
    	// 각 도시로 가는 최소 비용 배열 생성 
    	minMoneyArr = new long[n+1];
    	Arrays.fill(minMoneyArr, Integer.MAX_VALUE);
    	// 방문 배열 생성
    	boolean[] visited = new boolean[n+1];
    	
    	//  시작 도시와 끝 도시 설정
    	st = new StringTokenizer(br.readLine());
    	startCity = Integer.parseInt(st.nextToken());
    	endCity = Integer.parseInt(st.nextToken());
    	
    	minMoneyArr[startCity] = 0;
    	
    	// 이전도시를 기록 배열
    	int[] beforeCity = new int[n+1];
    	beforeCity[startCity] = startCity;
    	
    	// 도시 노드 개수만큼 반복 
    	for(int i = 0; i < n; i++) {
    		long minMoney = Integer.MAX_VALUE;
    		int minIdx = 0;
    		
    		// 방문하지 않은 노드중 해당 노드로 가는 가장 적은 값을 찾는다.
    		for(int j = 1; j <= n; j++) {
        		if(!visited[j] && minMoney > minMoneyArr[j]) {
        			minIdx = j;
        			minMoney = minMoneyArr[j];
        		}
    		}
    		// 방문처리
    		visited[minIdx] = true;
    		
    		// 노드로 가는 최소 비용을 갱신한다.
    		for(int j = 0; j < graph[minIdx].size(); j++) {
    			int end = graph[minIdx].get(j).end;
    			int money = graph[minIdx].get(j).money;

    			// 갱신
    			if(minMoneyArr[end] > minMoneyArr[minIdx] + money) {
    				minMoneyArr[end] = minMoneyArr[minIdx] + money;
    				// 해당 노드로 가기 위한 이전 노드를 현재 노드로 바꾼다.
    				beforeCity[end] = graph[minIdx].get(j).start;
    			}
    		}
    		
    	}
		System.out.println(minMoneyArr[endCity]);
		
		List<Integer> visitList = new ArrayList<>();
		int start = endCity;
		while(start != startCity) {
			visitList.add(start);
			start = beforeCity[start];
		}
		
		visitList.add(start);
		System.out.println(visitList.size());
		
		for(int i = visitList.size()-1; i >= 0; i--) {
			System.out.print(visitList.get(i) + " ");
		}
		
    }
    

}
    	
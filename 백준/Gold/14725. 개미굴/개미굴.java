import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	static Map<String, Map> graph;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// 초기화
		graph = new HashMap<>();
		
		// 그래프 생성
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			
			Map<String, Map> tmpMap = graph;
			for(int j = 0; j < m; j++) {
				// j는 트리의 높이를 의미한다.
				
				// 단어를 받는다.
				String word = st.nextToken();
				
				// 현재 높이에 해당 단어로 새로운 가지를 생성한다.
				tmpMap.putIfAbsent(word, new HashMap<String,Map>());

				// 현재 단어 자식 트리쪽으로 이동
				tmpMap = tmpMap.get(word);
			}
		}
		
		dfs(graph, "");
		System.out.println(sb.toString());

	}
	private static void dfs(Map<String, Map> treeMap, String bar) {
		
		List<String> keySet =  new ArrayList(treeMap.keySet());
		Collections.sort(keySet);
		
		for(String key : keySet) {
			sb.append(bar).append(key).append("\n");
			dfs(treeMap.get(key), bar+"--");
		}
		
	}


}

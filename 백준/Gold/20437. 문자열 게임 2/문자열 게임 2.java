
import java.util.*;
import java.io.*;

public class Main {
	
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase = Integer.parseInt(br.readLine());
		
		for(int tc = 0; tc < testCase; tc++) {
			
			String str = br.readLine();
			int k = Integer.parseInt(br.readLine());
			
			int minLength = Integer.MAX_VALUE;
			int maxLength = Integer.MIN_VALUE;
			
			// map에 모든 문자의 개수를 카운트함
			HashMap<Character, List<Integer>> map = new HashMap();
			for(int i = 0; i < str.length(); i++) {
				char key = str.charAt(i);
				map.putIfAbsent(key, new ArrayList<>());
				map.get(key).add(i);
			}
			
			for(List<Integer> idxList : map.values()) {
				if(idxList.size() < k) continue;
				
				
				// 최단 길이 갱신은 k개씩 끊어서 계산한다. 0 1 2 3 4 인데 k가3이면 012 123 234
				for(int i = 0; i <= idxList.size() - k; i++) {
					int length = idxList.get(i + k - 1) - idxList.get(i) + 1;
					minLength = Math.min(length, minLength);
					maxLength = Math.max(length, maxLength);
				}
				
			}
			
			if(minLength == Integer.MAX_VALUE && maxLength == Integer.MIN_VALUE) {
				System.out.println(-1);
			} else {
				System.out.println(minLength + " " + maxLength);
			}

			
			
			
		}

	}


}

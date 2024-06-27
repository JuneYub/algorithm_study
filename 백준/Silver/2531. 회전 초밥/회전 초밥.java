
import java.util.*;
import java.io.*;

public class Main {
	
	static int n, d, k, c;
	static int[] arr;
	static int maxSize = 0;
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		Map<Integer, Integer> shushiType = new HashMap<>(); 
		for(int i = 0; i < k; i++) {
			if(!shushiType.containsKey(arr[i])) {
				shushiType.put(arr[i], 1);
			} else {
				shushiType.put(arr[i], shushiType.get(arr[i]) + 1);
			}
		}
		
		if(!shushiType.containsKey(c)) {
			shushiType.put(c, 1);
		}
		maxSize = shushiType.size();
		
		// 시작
		int idx = k;
		for(int i = idx; i < n; i++) {
			// 새로운 초밥 추가
			if(!shushiType.containsKey(arr[i])) {
				shushiType.put(arr[i], 1);
			} else {
				shushiType.put(arr[i], shushiType.get(arr[i]) + 1);
			}
			
			// 앞에 추가한 초밥 삭제
			if(shushiType.get(arr[i-k]) == 1) {
				shushiType.remove(arr[i-k]);
			}  else {
				shushiType.put(arr[i-k], shushiType.get(arr[i-k]) - 1);
			}
			
//			System.out.println("현재 먹을 초밥들");
//			for(Integer key : shushiType.keySet()) {
//				System.out.print(key + " ");
//			}
//			System.out.println();
			
			// 쿠폰 음식 포함되어있는지 확인하고 없을때만 추가
			if(!shushiType.containsKey(c)) {
				shushiType.put(c, 1);
			}
			// 업데이트
			maxSize = Math.max(shushiType.size(), maxSize);			
		}
		
		// 맨 마지막까지 추가한 경우
		for(int i = 0; i < k-1; i++) {
			// 새로운 초밥 추가
			if(!shushiType.containsKey(arr[i])) {
				shushiType.put(arr[i], 1);
			} else {
				shushiType.put(arr[i], shushiType.get(arr[i]) + 1);
			}
			
			// 앞에 추가한 초밥 삭제
			if(shushiType.get(arr[n-k+i]) == 1) {
				shushiType.remove(arr[n-k+i]);
			}  else {
				shushiType.put(arr[n-k+i], shushiType.get(arr[n-k+i]) - 1);
			}
			
			// 쿠폰 음식 포함되어있는지 확인하고 없을때만 추가
			if(!shushiType.containsKey(c)) {
				shushiType.put(c, 1);
			}
			// 업데이트
			maxSize = Math.max(shushiType.size(), maxSize);	
		}
		System.out.println(maxSize);
	}
}

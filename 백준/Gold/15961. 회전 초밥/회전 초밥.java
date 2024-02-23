
import java.util.*;
import java.io.*;

public class Main {
	
	static int n, d, k, c;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		HashMap<Integer, Integer> map = new HashMap<>(); // 타입 저장소
		
		int arr[] = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		map.put(c, 1); // 쿠폰 저장
		
		for(int i = 0; i < k; i++) {
			int food = arr[i];
			if(!map.containsKey(food)) {
				map.put(food, 1);
			} else {
				int cnt = map.get(food);
				map.put(food, cnt+1);
			}
		}
		
		int ans  = map.size();
		
		// i는 시작위치 , startPoint <= n-k
		for(int startPoint = 1, endPoint = k; startPoint < n; startPoint++, endPoint++) {
			if(startPoint == n-k+1) {
				endPoint = 0;
			}
			
			
			int food = arr[endPoint];
			if(!map.containsKey(food)) {
				map.put(food, 1);
			} else {
				int cnt = map.get(food);
				map.put(food, cnt+1);
			}
			
			int deleteFood = arr[startPoint-1];
			int cnt = map.get(deleteFood);
			if(cnt-1 == 0) {
				map.remove(deleteFood);
			} else {
				map.put(deleteFood, cnt-1);
			}
			
			ans = Math.max(ans, map.size());
		}
		
		
		
		System.out.println(ans);
	} // end of main
}

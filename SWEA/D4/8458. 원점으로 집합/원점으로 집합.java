import java.util.*;
import java.io.*;


public class Solution
{


	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= testCase; tc++) {
				
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			List<Long> distanceList = new ArrayList<>();
			long longDistance = 0;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				
				long distance = Math.abs(x) + Math.abs(y);
				distanceList.add(distance);
				longDistance = Math.max(distance, longDistance);
			}
			
			// -1을 리턴하는 조건인지 체크
			boolean isContinue = true;
			long beforeCheck = distanceList.get(0) % 2;
			for(int i = 0; i < distanceList.size(); i++) {
				if (beforeCheck != distanceList.get(i) % 2) {
					isContinue = false;
				}
			}
			
			if(!isContinue) {
				System.out.println("#"+tc+" "+ -1);
				continue;
			}
			
			
			// 최대 길이를 기준으로 1씩 증가시키면서 모두 만족하는 i를 구한다.
			// i(i-1)/2 = 길이
			long i = 0;
			while(true) {
				long distance = i*(i+1)/2;
				if(distance >= longDistance) {
					break;
				} else {
					i++;
				}
			}
			
			// 최소 i를 구하다(최대 멘하탄 거리를 통해서)
			while(true) {
				long distance = i*(i+1)/2;
				boolean isAns = true;
				
				for(int j = 0; j < distanceList.size(); j++) {
					
					// 홀수가 뜨면 브레이크
					if( (distanceList.get(j) - distance) % 2 != 0 ) {
						isAns = false;
						break;
					}
				}
				
				// 답이면 출력
				if(isAns) {
					System.out.println("#"+tc+" "+i);
					break;
				}
				
				// 답이면 i증
				i++;
			}
		}
	}
}

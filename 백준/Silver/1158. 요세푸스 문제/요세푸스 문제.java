
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;



public class Main {
	
	
	public static void main(String args[]) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] list = new int[n];
		List<Integer> answer = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			list[i] = i + 1;
		}
		
		int cnt = 0;
		int idx = 0;
		while(answer.size() != n) {
			if(idx >= n) {
				idx = 0;
				continue;
			}
			if(list[idx] == 0) {
				idx++;
				continue;
			}
			cnt++;
			
			if(cnt%k == 0) {
				answer.add(list[idx]);
				list[idx] = 0;
				
			}
			idx++;

			
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for(int i = 0; i < answer.size(); i++) {
			if(i == answer.size() -1) {
				sb.append(answer.get(i));
				
			}
			else {
				sb.append(answer.get(i)+", ");
			}
		}
		sb.append(">");
		System.out.println(sb.toString());
		
	}// end of main(String args[])  -----------------------------
		
	
}      



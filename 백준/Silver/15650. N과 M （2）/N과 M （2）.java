
import java.io.*;
import java.util.*;

public class Main {
	static int n, m;
	static int[] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new int[m];
		sb = new StringBuilder();
		
		permutation(0, 0);
		System.out.println(sb.toString());
	}
	
	
	// 현재 배열에 넣은 개수 cnt와 idx 를 받는다 idx를 통해서 이전 진행값보다 큰 값을 배열에 넣도록 한다.
	public static void permutation(int cnt, int idx) {
		// 기저 조건 m만큼 뽑은 경우에는 출력
		if(cnt == m) {
			for(int i = 0; i < m; i++) {
				sb.append(arr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		
		// 다 뽑지 않았다면 받은 idx 부터 n까지 출력 ex) 0만큼 뽑은 0부터 돌려서 1을 배열에 넣고 1만큼 뽑은 경우 1부터 돌려서 2를 배열에 넣고
		for(int i = idx; i < n; i++) {

			arr[cnt] = (i+1);
			permutation(cnt+1, i+1);
		
		}
	}

	
}

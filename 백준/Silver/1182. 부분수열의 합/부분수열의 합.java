import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static int n, s;
	public static int[] arr;
	public static int ans = 0;
		
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= n; i++) {
			dfs(i, 0, 0);
		}
		
		System.out.println(ans);
		
		
		
	}
	
	public static int dfs(int remainingCount, int idx, int sum) {  // dfs를 이용해서 푼다. remainingCount는 잔여개수이며 sum은 누적합이다.
		
		if(remainingCount == 0) {
			if(sum == s) ans++;
			return 0;
		}
		
		if(idx+1 > n) {
			return 0;
		}
		
		return dfs(remainingCount-1, idx+1, sum + arr[idx]) + dfs(remainingCount, idx+1, sum); //이번 배열값을 고르는 선택, 고르지 않는 선택
	}
}

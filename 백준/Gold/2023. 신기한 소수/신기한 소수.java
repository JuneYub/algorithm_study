
import java.io.*;
import java.util.*;

public class Main {

	static int n;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n자리
		n = Integer.parseInt(br.readLine());
		

		sb = new StringBuilder();
		dfs(0, 0);
		
		
		System.out.println(sb.toString());
	}
	
	public static void dfs(int depth, int num) {
		if(depth == n) {
			sb.append(num/10 + "\n");
		}
		
		for(int i = 1; i <= 9; i++) {
			if(isPrime(num+i)) {
				dfs(depth+1, (num+i)*10);
			}
		}
		
		
	}
	
	public static boolean isPrime(int n) {
		if(n < 2) {
			return false;
		}
		
		for(int i = 2; i*i <= n; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
}


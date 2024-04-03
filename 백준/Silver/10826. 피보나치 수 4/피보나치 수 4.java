import java.util.*;
import java.io.*;
import java.math.*;


public class Main {
	
	static BigInteger[] memo;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		memo = new BigInteger[10001];
        memo[0] = new BigInteger("0");
		memo[1] = new BigInteger("1");
		memo[2] = new BigInteger("1");
		int n = Integer.parseInt(st.nextToken());
		if(n >= 3) {
			for(int i = 3; i <= n; i++) {
				memo[i] = memo[i-1].add(memo[i-2]);
			}
		}
		System.out.println(memo[n]);
		
	}
}

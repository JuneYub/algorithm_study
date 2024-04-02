import java.util.*;
import java.io.*;


public class Solution {
	public static long[] cnts;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int testCase = Integer.parseInt(st.nextToken());
		
		for(int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			cnts = new long[10];
			long delta = 1;
			while(a <= b) {
				for(; a%10!=0 && a <= b; a++) {
					parse(a, delta);
				}
				
				for(; b%10!=9 && a <=b; b--) {
					parse(b, delta);
				}
				
				if(a > b) {
					break;
				}
				
				a/=10;
				b/=10;
				long rowCnt = b-a+1;
				for(int i = 0; i < 10; i++) {
					cnts[i] += rowCnt*delta;
				}
				delta *= 10;
				
			}
			
			long sum = 0;
			for(int i = 1; i < 10; i++) {
				sum += i*cnts[i];
			}
			System.out.println("#" + tc + " " + sum);
			
		}
	}
	private static void parse(long x, long delta) {
		while(x > 0) {
			cnts[(int)(x%10)] += delta;
			x/=10;
		}
		
	}


}


import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n자리
		int n = Integer.parseInt(br.readLine());
		
		String tmp = "1";
		for(int i = 0; i < n-1; i++) {
			tmp += 0;
		}
		
		int num = Integer.parseInt(tmp);
		int finalNum = Integer.parseInt(tmp+0);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = num; i < finalNum; i++) {
			if(checkNum(i, num)) {
				sb.append(i +"\n");
			}
		}
		System.out.println(sb.toString());
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
	
	public static boolean checkNum(int num, int bigyoNum) {
		while(bigyoNum > 0) {
			
			if(!isPrime(num / bigyoNum)) {
				return false;
			}
			bigyoNum /= 10;
		}
		return true;
	}
	

}

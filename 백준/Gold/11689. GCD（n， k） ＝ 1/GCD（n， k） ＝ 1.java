
import java.util.*;
import java.io.*;

public class Main
{

	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n = Long.parseLong(br.readLine());
		System.out.println(phi(n));
	}

	private static long phi(long n) {
		long result = n;
		// 만약 
		for(long p = 2; p*p <= n; p++) {
			if(n % p == 0) {
				// 모든 원소개수 중에서 배수 지우기
				result -= result / p;
				while(n%p == 0) {
					// 배수 지우기
					n = n/p;
				}
			}
		}
		
		// n이 소수이면 1보다 크다. 나눠지지가 않은 것임
		if(n > 1) {
			// 잔여 개수 중에서 소수 배수만큼 빼주자
			result -= result / n;
		}
		
		return result;
		
	}
	
} 	

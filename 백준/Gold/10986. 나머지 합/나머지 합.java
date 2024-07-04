import java.util.*;
import java.io.*;

public class Main
{
	
	static int n,m;
	static long[] nujuck;
	static long[] nanugi;
	static long[] result;
	public static void main(String[] args) throws Exception  {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		nujuck = new long[n];
		nanugi = new long[n];
		st = new StringTokenizer(br.readLine());
		
		nujuck[0] = Integer.parseInt(st.nextToken());
		nanugi[0] = nujuck[0] % m;
		
		for(int i = 1; i< n; i++) {
			nujuck[i] = Integer.parseInt(st.nextToken()) + nujuck[i-1];
			nanugi[i] = nujuck[i] % m;
		}
		
		
		result = new long[m];
		
		for(int i = 0; i < n; i++) {
			result[(int) nanugi[i]]++;
		}
		
		long ans = result[0];
		for(int i = 0; i < m; i++) {
			ans += comb(result[i]);
		}
		
		System.out.println(ans);
	}
	
	public static long comb(long a) {
		if(a < 2) {
			return 0;
		}
		else {
			return a * (a-1) / 2;
		}
	}
} 	

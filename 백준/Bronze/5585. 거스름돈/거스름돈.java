import java.util.*;
import java.io.*;


public class Main {
	
	static int pay;
	static int[] type = {500, 100, 50, 10, 5, 1};
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());

		pay = 1000 - m;
		
		int cnt = 0;
		int typeIdx = 0;
		
		while(pay > 0) {
			cnt += pay / type[typeIdx];
			pay = pay % type[typeIdx];
			typeIdx++;
		}
		System.out.println(cnt);
		
	}
	
}

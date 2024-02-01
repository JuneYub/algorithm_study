
import java.util.*;
import java.io.*;

public class Main {


	static int n, m, result;
	static int[] curArr;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		String str = br.readLine();
		
		arr = new int[4];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 4; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int startPoint = 0;
		
		curArr = new int[4];
		
		for(int i = 0; i < m; i++) {
			cntDna(str.charAt(i));
		}
		
		if(isEqual()) result++;
		
		for(int endPoint = m; endPoint < n; endPoint++) {
			minusCntDna(str.charAt(startPoint));
			startPoint++;
			cntDna(str.charAt(endPoint));
			
			if(isEqual()) result++;
		}
		
		System.out.println(result);

	}
	
	static void cntDna(char ch) {
		switch(ch) {
			case 'A':
				curArr[0]++;
				break;
			case 'C':
				curArr[1]++;
				break;
			case 'G':
				curArr[2]++;
				break;
			case 'T':
				curArr[3]++;
				break;
		}	
	}
	
	static void minusCntDna(char ch) {
		switch(ch) {
			case 'A':
				curArr[0]--;
				break;
			case 'C':
				curArr[1]--;
				break;
			case 'G':
				curArr[2]--;
				break;
			case 'T':
				curArr[3]--;
				break;
		}	
	}
	
	static boolean isEqual() {
		for(int i = 0; i < 4; i++) {
			if(arr[i] > curArr[i]) {
				return false;
			}
		}
		
		return true;
	}

}

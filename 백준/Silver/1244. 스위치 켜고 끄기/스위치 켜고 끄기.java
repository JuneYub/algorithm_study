
import java.io.*;
import java.util.*;

public class Main {


	static int n, num;
	static int[] arr;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		num = Integer.parseInt(br.readLine());
		
		
		for(int i = 0; i < num; i++) {
			st = new StringTokenizer(br.readLine());
			int student = Integer.parseInt(st.nextToken());
			int idx = Integer.parseInt(st.nextToken());
			if(student == 1) {
				male(idx);
			} else {
				female(idx);
			}
		}
		
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
			if((i+1) % 20 == 0) {
				System.out.println();
			}
			
		}
		
		
	}
	
	public static void male(int idx) {
		for(int i = 0; i < n; i++) {
			if( (i+1)%idx == 0 ) {
				arr[i] = (arr[i] == 0 ? 1 : 0); 
			}
		}
	}
	
	public static void female(int idx) {
		idx = idx-1;
		int width = 0;
		int low = 0;
		int high = 0;
		while(true) {
			
			if(idx-width < 0 || idx+width >= n) break;
			
			if(arr[idx-width] != arr[idx+width]) {
				break;
			}
			low = idx - width;
			high = idx + width;
			
			width++;
		}
		for(int i = low; i <= high; i++) {
			arr[i] = (arr[i] == 0 ? 1 : 0); 
		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n;
	static int[] inputArr;
	
	public static void findReverseSort(int[] arr) {
		boolean changeMaxIdx = true;
		int maxIdx = 0;
		
		xx:
		for(int i = 0; i < n; i++) {
			if(changeMaxIdx) {
				maxIdx = i;
			}
			for(int j = i+1; j < n; j++) {
				if(arr[i] < arr[j]) {
					changeMaxIdx = true;
					continue xx;
				}
				changeMaxIdx = false;
			}
		}
		
		if(maxIdx == 0) {
			System.out.println(-1);
		} 
		else {
			swap(maxIdx, arr);
		}
	}
	
	public static void swap(int swapStartIdx, int[] arr) {
		Arrays.sort(arr, swapStartIdx, n);
		for(int i = swapStartIdx; i < n; i++) {
			if(arr[swapStartIdx-1] < arr[i]) {
				int tmp = 0;
				tmp = arr[swapStartIdx-1];
				arr[swapStartIdx-1] = arr[i];
				arr[i] = tmp;
				break;
			}
		}
		
		for(int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
		}
	}
	
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		inputArr = new int[n];
		
		for(int i = 0; i < n; i++) {
			inputArr[i] = Integer.parseInt(st.nextToken());
		}
		
		findReverseSort(inputArr);
	}
}
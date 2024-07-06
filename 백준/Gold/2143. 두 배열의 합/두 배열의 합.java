import java.util.*;
import java.io.*;

public class Main
{

	static int n, m, t;
	public static void main(String[] args) throws Exception  {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		t = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		m = Integer.parseInt(br.readLine());
		int[] arr2 = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		int aSize = n*(n+1)/2;
		int bSize = m*(m+1)/2;
		
		// 부분집합
		long[] aSum = new long[aSize];
		long[] bSum = new long[bSize];

		getSubArray(arr, aSum);
		getSubArray(arr2, bSum);
		
		Arrays.sort(aSum);
		Arrays.sort(bSum);
		
		long result = 0;
		// aSum 의 시작부터 끝까지 돌아갑니다.
		// upperBound - lowerBound 하면 부분 배열의 특정 값의 개수를 구할 수 있습니다.
		for(int i = 0; i < aSum.length;) {
			long key = aSum[i];
			
			long aCnt = upperBound(aSum, key) - lowerBound(aSum, key);
			long bCnt = upperBound(bSum, t - key) - lowerBound(bSum, t - key);
			
			result += aCnt * bCnt;
			i += aCnt;
		}
		System.out.println(result);

	}
	
	// key 값보다 큰 값중 처음값
	public static int upperBound(long[] arr, long key) {
		int low = 0;
		int hight = arr.length;
		
		while(low < hight) {
			int mid = (low + hight) / 2;
			if(arr[mid] <= key) {
				low = mid + 1;
			} else {
				hight = mid;
			}
		}
		
		return hight;
	}
	
	// key 값 이상의 첫번째 위치
	public static int lowerBound(long[] arr, long key) {
		int low = 0;
		int hight = arr.length;
		
		while(low < hight) {
			int mid = (low + hight) / 2;
			if(arr[mid] < key) {
				low = mid + 1;
			} else {
				hight = mid;
			}
		}
		
		return hight;
	}
	
	public static void getSubArray(int[] arr, long[] sumArr) {
		
		int idx = 0;
		for(int i = 0; i < arr.length; i++) {
			
			int sum = 0;
			for(int j = i; j < arr.length; j++) {
				sum += arr[j];
				sumArr[idx++] = sum;
			}
		}
	}
	
} 	

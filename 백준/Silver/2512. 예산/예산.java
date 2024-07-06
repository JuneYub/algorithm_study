
import java.util.*;
import java.io.*;

public class Main
{

	static int n, m;
	static int[] arr;
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int maxV = 0;
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			maxV = Math.max(arr[i], maxV);
		}
		m = Integer.parseInt(br.readLine());
		
		System.out.println(upperBound(maxV));
			
	}
	
	public static int upperBound(int maxV) {
		int left = 0;
		int right = maxV;
		int mid = (left+right)/2;
		while(left <= right) {
			
			mid = (left+right)/2;
			
			// 조건을 만족하는 경우에는 left를 좀더 키운다.
			if(isOk(mid)) {
				left = mid + 1;
				
				// 조건 미충족시 right를 뒤로 땡긴다.
			} else {
				right = mid-1;
				//if(right == left) break;
			}
		}
		return left-1;
	}
	
	public static boolean isOk(int num) {
		int sum = 0;
		for(int i = 0; i < n; i++) {
			if(num >= arr[i]) {
				sum += arr[i];
			} else {
				sum += num;
			}
		}
		if(sum <= m) return true;
		else return false;
	}

	
} 	

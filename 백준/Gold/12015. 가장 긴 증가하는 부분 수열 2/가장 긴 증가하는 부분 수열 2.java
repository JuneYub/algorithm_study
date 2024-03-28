import java.util.*;
import java.io.*;

public class Main
{
    static int n;
    static int[] arr;
    static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        dp = new int[n+1];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        int len = 0;
        for(int i = 1; i <= n; i++) {
    		if(dp[len] < arr[i]) {
    			dp[len+1] = arr[i];
    			len++;
    		}
    		else {
    			int idx = binarySearch(0, len, arr[i]);
    			dp[idx] = arr[i];
    		}
        }
        
        System.out.println(len);
	}
	
	public static int binarySearch(int left, int right, int target) {
	    int mid;
	    while(left < right) {
	        mid = (left + right) / 2;
	        
	        // 가운데 값이 타겟보다 작다면
	        // 가운데보다 1 큰 값이 왼쪽이 된다.
	        if(dp[mid] < target) {
	            left = mid + 1;
	        }
	        // 가운데 값이 타겟보다 크다면
	        // 가운데가 오른쪽이 된다.
	        else {
	            right = mid;
	        }
	    }
	    return right;
	}
}


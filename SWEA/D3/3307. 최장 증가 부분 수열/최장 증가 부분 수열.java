/******************************************************************************

                            Online Java Compiler.
                Code, Compile, Run and Debug java program online.
Write your code in this editor and press "Run" button to execute it.

*******************************************************************************/

import java.util.*;
import java.io.*;

public class Solution
{
    static int n, m;
    static int[] arr;
    static int[] lis;
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
	    
		StringTokenizer st;
		for(int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			arr = new int[n+1];
			lis = new int[n+1];
			
			for(int i = 1; i <= n; i++) {
			    arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i = 1; i <= n; i++) {
			    
			    lis[i] = 1;
			    for(int j = 1; j < i; j++) {
			        if(arr[i] > arr[j] && lis[i] < lis[j]+1) {
			            lis[i] = lis[j]+1;
			           
			        }
			    }
			}
			
			int ans = 0;
			for(int i = 1; i <= n; i++) {
			    //System.out.print(lis[i] + " ");
			    ans = Math.max(ans, lis[i]);
			}
			
			System.out.println("#"+tc+" "+ans);
		}
	}
}

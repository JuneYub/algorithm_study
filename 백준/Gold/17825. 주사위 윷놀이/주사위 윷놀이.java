
import java.io.*;
import java.util.*;

import java.io.*;
import java.util.*;


public class Main {
	
		
	public static int[][] map =  {
			
	    //    0    1    2    3    4      5      6    7
			 {1}, {2}, {3}, {4}, {5}, {6, 21}, {7}, {8},
		//    8     9      10      11    12    13    14    15
			 {9}, {10}, {11, 27}, {12}, {13}, {14}, {15}, {16, 29},
       //     16    17   18     19    20     21   22    23
			 {17}, {18}, {19}, {20}, {32}, {22}, {23}, {24},
	  //     24    25    26    27    28    29    30    31   32
		    {25}, {26}, {20}, {28}, {24}, {30}, {31}, {24}, {32}
			
	};
	
	public static int[] score = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20,
			22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13, 16, 19, 25, 30,
			35, 22, 24, 28, 27, 26, 0};
	
	public static int[] arr = new int[10];
	public static int[] position = new int[4];
	public static int ans = 0;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 10; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(0, 0);
        System.out.print(ans);
    }
    
    public static void dfs(int n, int sum) {
    	if(n == 10) {
    		
    		ans = Math.max(ans, sum);
    		return;
    	}
    	
    	int cnt = arr[n];
    	
    	Set<Integer> set = new HashSet<>();
    	for(int i = 0; i < 4; i++) {
    		set.add(position[i]);
    	}
    	
    	for(int i = 0; i < 4; i++) {
    		// 현재 말의 위치
    		int orgCur = position[i];
    		int cur = position[i];

    		int tmpSum = sum;
    		// 1칸 이동
    		cur = map[cur][map[cur].length-1];
    		
    		// 나머지 칸도 이동
    		for(int j = 0; j < cnt-1; j++) {
        		cur = map[cur][0];
        		
    		}
    		
    		// 다른 말이랑 부딫치면 넘긴다.
    		if(cur!=32 && set.contains(cur)) continue;
    		
    		
    		tmpSum += score[cur];
    		position[i] = cur;
    		dfs(n+1, tmpSum);
    		position[i] = orgCur;
    	}
    	
    	
    }
    
}
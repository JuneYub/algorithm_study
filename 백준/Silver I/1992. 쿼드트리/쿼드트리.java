import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[][] arr;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(br.readLine());
	    arr = new int[n][n];
	    for(int y = 0; y < n; y++) {
	        String str = br.readLine();
	        for(int x = 0; x < n; x++) {
	            arr[y][x] = str.charAt(x) - '0';
	        }
	    }
	    
	    System.out.println(dfs(0, 0, n));
	}
	
	public static String dfs(int x, int y, int length) {
		StringBuilder sb = new StringBuilder();
	    // 1칸에 도달하면 값을 반환
	    if(length == 1) {
	    	return arr[y][x] + "";
	    }
	    
	    int half = length/2;
	    
	    //11시
	    String a11 = dfs(x, y, half);
	    
		//1시
		String a1 = dfs(x+half, y, half);
		
		//7시
		String a7 = dfs(x, y+half, half);
		
		// 5시
		String a5 =dfs(x+half, y+half, half);
		
		if((a11.equals("1") || a11.equals("0")) && a11.equals(a1) && a1.equals(a7) && a7.equals(a5)) {
			return a11;
		} else {
			sb.append("("+a11+a1+a7+a5+")");
		}
		
		return sb.toString();
	}
}


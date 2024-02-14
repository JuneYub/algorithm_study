
import java.io.*;
import java.util.*;

public class Main {


	static int n, r, c, sum;
	static int[][] arr;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken()); // 행
		c = Integer.parseInt(st.nextToken()); // 열
		n = (int) Math.pow(2, n); // 한변의 길이
		sum = 0;
		// 재귀함수 호출
		recursion(n, 0, 0);
		System.out.println(sum);

		
	}
	

	/**
	 * @param len
	 * @param leftTopX
	 * @param leftTopY
	 */
	public static void recursion(int len, int leftTopX, int leftTopY) {
		if(len == 1) {
			return;
		}
		
		int newLen = len/2;
		int tmp = newLen*newLen;
		
		//11시 방향인가?
		if((r < leftTopY + newLen) && (c < leftTopX + newLen ) )  {
			//System.out.println("11 ----------");
			//System.out.println(sum);
			recursion(newLen, leftTopX, leftTopY);
			return;
		}
		
		//1시 방향인가?
		if((r < leftTopY + newLen ) && (c >= leftTopX + newLen ) )  {
			//System.out.println("1 ----------");
			sum += tmp;
			recursion(newLen, leftTopX+newLen, leftTopY);
			return;
		}
		
		//5시 방향인가?
		if((r >= leftTopY + newLen) && (c >= leftTopX + newLen) )  {
			//System.out.println("5 ----------");
			sum += tmp*3;
			//System.out.println(sum);
			recursion(newLen, leftTopX+newLen, leftTopY+newLen);
			return;
		}
		//7시 방향인가?
		if((r >= leftTopY + newLen) && (c < leftTopX + newLen) )  {
			//System.out.println("7 ----------");
			sum += (tmp*2);
			//System.out.println(sum);
			recursion(newLen, leftTopX, leftTopY+newLen);
			return;
		}
		
	}
}

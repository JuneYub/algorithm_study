
import java.io.*;
import java.util.*;

public class Main {
	/**
	* 작성자 : 박준엽
	* 문제 : [S1] 구간 합 구하기 5 - 11660
	* 제출 : 2024년 1월 31일
	* 메모리: 59404 KB, 시간: 660 ms
	* 아이디어 : 이중 반복문으로 모두 계산한다면 최악의 경우 100억개의 계산이 필요하다.
	* 		  따라서 다른 1차원배열에 idx마다의 누적 합을 기록했다가 구해야하는 구간이 제시된다면
	* 		 시작부분의 누적합을 끝 부분의 누적합에서 뺀다면 해당 구간의 누적합을 바로 구할 수 있다.
	*/

	static int n, m;
	static int[][] cumulative;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		// 누적합 배열
		cumulative = new int[n][n];
		sb = new StringBuilder();
			
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				int num = Integer.parseInt(st.nextToken());
				
				if(x == 0) {
					cumulative[y][x] = num;
				} else {
					cumulative[y][x] = num + cumulative[y][x-1];
				}
			}
		}
		
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y1 = Integer.parseInt(st.nextToken())-1;
			int x1 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			int x2 = Integer.parseInt(st.nextToken())-1;
			
			int sum = 0;
			for(int y = y1; y <= y2; y++) {
				if(x1 == 0) {
					sum += cumulative[y][x2];
				}
				else {
					sum += (cumulative[y][x2] - cumulative[y][x1-1]);
				}
			}
			
			sb.append(sum + "\n");
		}
		System.out.println(sb.toString());
		
	}
}

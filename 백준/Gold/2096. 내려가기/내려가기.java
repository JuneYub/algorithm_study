
import java.util.*;
import java.io.*;


public class Main {
	
	static int n;
	static int[][] maxdp;
	static int[][] mindp;
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		maxdp = new int[2][3];
		mindp = new int[2][3];
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int fir = Integer.parseInt(st.nextToken());
		int snd = Integer.parseInt(st.nextToken());
		int trd = Integer.parseInt(st.nextToken());
		
		maxdp[0][0] = fir;
		maxdp[0][1] = snd;
		maxdp[0][2] = trd;

		mindp[0][0] = fir;
		mindp[0][1] = snd;
		mindp[0][2] = trd;
		
		for(int i = 1; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			
			fir = Integer.parseInt(st.nextToken());
			snd = Integer.parseInt(st.nextToken());
			trd = Integer.parseInt(st.nextToken());
			
			maxdp[1][0] = Math.max(maxdp[0][0], maxdp[0][1]) + fir; 
			maxdp[1][1] = Math.max(maxdp[0][0], Math.max(maxdp[0][1], maxdp[0][2])) + snd; 
			maxdp[1][2] = Math.max(maxdp[0][1], maxdp[0][2]) + trd; 
			
			mindp[1][0] = Math.min(mindp[0][0], mindp[0][1]) + fir; 
			mindp[1][1] = Math.min(mindp[0][0], Math.min(mindp[0][1], mindp[0][2])) + snd; 
			mindp[1][2] = Math.min(mindp[0][1], mindp[0][2]) + trd;
			
			for(int j = 0; j < 3; j++) {
				maxdp[0][j] = maxdp[1][j];
				mindp[0][j] = mindp[1][j];
			}
		}
		
		System.out.println(Math.max(maxdp[0][0], Math.max(maxdp[0][1], maxdp[0][2])) + " " + Math.min(mindp[0][0], Math.min(mindp[0][1], mindp[0][2])));
	}
	
}

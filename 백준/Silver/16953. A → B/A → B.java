
import java.util.*;
import java.io.*;


public class Main {
	
	static long start, end;
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		start = Long.parseLong(st.nextToken());
		end = Long.parseLong(st.nextToken());
		int cnt = 0;
		while(end > start) {
			if(end % 2 == 0) {
				end = end / 2;
				cnt ++;
			} else if(end % 10 == 1) {
				end = end / 10;
				cnt++;
			} else {
				break;
			}
		}
		
		if(end == start) {
			System.out.println(cnt + 1);
		}
		else {
			System.out.println(-1);
		}
	}
	
	
}

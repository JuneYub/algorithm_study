import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new ArrayDeque();
		
		for(int i = 1; i <= n; i++) {
			q.add(i);
		}
		
		int idx = 1;
		
		while(q.size() != 1) {
			int num = q.poll();
			if(idx % 2 == 0) {
				q.add(num);
			}
			idx++;
		}
		System.out.println(q.poll());
	}

}

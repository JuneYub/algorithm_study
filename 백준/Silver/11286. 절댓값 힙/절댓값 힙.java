import java.util.*;
import java.io.*;


public class Main {
	
	static int n;
	static int[] tree;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				
				if(Math.abs(o1) == Math.abs(o2)) {
					return o1-o2;
				} else {
					return Math.abs(o1) - Math.abs(o2);
				}
			}
		});
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(int i = 1; i <= n; i++) {
			int oper = Integer.parseInt(br.readLine());
			if(oper != 0) {
				pq.add(oper);
			} else {
				if(pq.isEmpty()) {
					bw.write("0");
					bw.newLine();
				} else {
					bw.write(pq.poll()+"");
					bw.newLine();
				}
				
			}
			
		}
		
		bw.flush();
	}
}
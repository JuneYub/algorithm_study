
import java.util.*;
import java.io.*;

class Ocun {
	int value;
	int index;
	
	public Ocun(int value, int index) {
		this.value = value; 
		this.index = index;
	}
}

public class Main {
	
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] result = new int[n];
		Arrays.fill(result, -1);
		
		Stack<Ocun> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if(stack.isEmpty()) {
				stack.push(new Ocun(num, i));
			}
			else {
				while(true) {
					if(stack.isEmpty()) {
						stack.push(new Ocun(num, i));
						break;
					} else {
						int topNum = stack.peek().value;
						if(topNum < num) {
							result[stack.peek().index] = num;
							stack.pop();
						} else {
							stack.add(new Ocun(num, i));
							break;
						}
					}
					
					
				}
			}
		}
		
		for(int i : result) {
			sb.append(i + " ");
		}
		System.out.println(sb.toString());
		
	}
	    
}
    

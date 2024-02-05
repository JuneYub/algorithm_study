import java.util.*;
import java.io.*;

public class Main {
	
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		Map<Integer, Integer> topMap = new HashMap<Integer, Integer>();
		Stack<Integer> stack = new Stack<>();
		int[] result = new int[n];
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// 오른쪽부터 입력을 받는다.
		for(int i = n-1; i >=0; i--) {
			int curNum = arr[i];
				// 스택이 비어있으면 넣고 멈추기
				if(stack.isEmpty()) {
					topMap.put(curNum, i);
					stack.add(curNum);
				} else {
					while(true) {
						if(stack.isEmpty()) {
							// 스택이 비어있으면 현재 값을 넣고 break;
							topMap.put(curNum, i);
							stack.add(curNum);
							break;
						}
						
						int stackTop = stack.peek();
						// 현재 번호가 스택의 탑보다 크면
						if(curNum > stackTop) {
							// 스택 탑의 위치를 가져와서 정답배열[스택탑 위치] = i로 높은 탑의 위치를 가리키도록 한다.
							result[topMap.get(stackTop)] = i+1;
							stack.pop();
							
						} else {
							// 현재 번호가 스택 탑보다 작으면
							topMap.put(curNum, i);
							stack.add(curNum);
							break;
						}
					
					} // end of while
				}
			
		}
		
		for(int i = 0; i < n; i++) {
			System.out.print(result[i] + " ");
		}

		
	}
	    
}
    

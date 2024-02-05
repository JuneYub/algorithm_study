import java.util.*;
import java.io.*;


class Tower {
	int height;
	int index;
	
	public Tower(int height, int index) {
		this.height = height;
		this.index = index;
	}
}

public class Main {
	
	static int n;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		Stack<Tower> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < n; i++) {
			int height = Integer.parseInt(st.nextToken());
			
			if(stack.isEmpty()) {
				sb.append("0 ");
				stack.push(new Tower(height, i+1));
			} else {
				while(true) {
					// 스택이 비어있으면 정답을 추가하고 새로운 타워를 스택에 넣자.
					if(stack.isEmpty()) {
						sb.append("0 ");
						stack.push(new Tower(height, i+1));
						break;
					}
					
					// 스택이 비어있지 않다면
					else {
						Tower tower = stack.peek();
						
						// 스택에서 꺼낸 탑의 높이가 현재 비교할 탑 높이보다 크다면
						if(tower.height > height) {
							// 현재 비교할 탑은 스택에 있는 탑에 레이저를 쏠 것이기 때문에
							// 현재 위치의 정답은 스택에 있는 탑의 위치이다.
							sb.append(tower.index + " ");
							stack.push(new Tower(height, i+1));
							break;
						} else {
							// 스택에서 꺼낸 탑의 높이가 현재 비교할 탑 높이보다 작다면
							// 스택에 있는 탑은 이제 쓸모가 없다 지워주자.
							stack.pop();
						}
					}			
				}
			}
		} // end of for
		System.out.println(sb.toString());
	}
	    
}
    

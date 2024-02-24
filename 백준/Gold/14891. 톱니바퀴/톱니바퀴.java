
import java.util.*;
import java.io.*;

public class Main {

	static LinkedList<Integer> list1;
	static LinkedList<Integer> list2;
	static LinkedList<Integer> list3;
	static LinkedList<Integer> list4;
	static List<LinkedList<Integer>> gears;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	list1 = new LinkedList<>();
    	list2 = new LinkedList<>();
    	list3 = new LinkedList<>();
    	list4 = new LinkedList<>();
    	gears = new ArrayList<>();
    	
		String str1 = br.readLine();
		String str2 = br.readLine();
		String str3 = br.readLine();
		String str4 = br.readLine();
		
		for(int x = 0; x < 8; x++) {
			list1.add(str1.charAt(x) - '0');
			list2.add(str2.charAt(x) - '0');
			list3.add(str3.charAt(x) - '0');
			list4.add(str4.charAt(x) - '0');
		}
		gears.add(list1);
		gears.add(list2);
		gears.add(list3);
		gears.add(list4);
    	
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			
			rotateGears(num, dir);
		}
		
		int ans = 0;
		for(int i = 0; i < 4; i++) {
			int num = gears.get(i).get(0);
			if(num != 0) {
				ans += Math.pow(2, (i+1)-1);
			}
		}
        System.out.println(ans);
		
	} // end of main
    
    public static void rotateGears(int num, int dir) {
    	// 톱니번호와 회전 방향을 받아 돌린다.
    	
    	// 톱니 네개의 S, N 유무를 확인한다.
    	boolean flag1 = false;
    	boolean flag2 = false;
    	boolean flag3 = false;
    	
    	if(list1.get(2) != list2.get(6)) flag1 = true; 
    	if(list2.get(2) != list3.get(6)) flag2 = true; 
    	if(list3.get(2) != list4.get(6)) flag3 = true; 
    	
    	// 1번톱니인 경우
    	if(num == 1) {
    		rotateGear(num, dir);
    		if(flag1) {
    			rotateGear(num+1, -dir);
    		}
    		if(flag1 && flag2) {
    			rotateGear(num+2, dir);
    		}
    		if(flag1 && flag2 && flag3) {
    			rotateGear(num+3, -dir);
    		}
    	}
    	
    	// 2번 톱니인 경우
    	if(num == 2) {
    		rotateGear(num, dir);
    		if(flag1) {
    			rotateGear(num-1, -dir);
    		}
    		if(flag2) {
    			rotateGear(num+1, -dir);
    		}
    		if(flag2 && flag3) {
    			rotateGear(num+2, dir);
    		}
    	}
    	
    	// 3번 톱니인 경우
    	if(num == 3) {
    		rotateGear(num, dir);
    		if(flag2) {
    			rotateGear(num-1, -dir);
    		}
    		if(flag3) {
    			rotateGear(num+1, -dir);
    		}
    		if(flag2 && flag1) {
    			rotateGear(num-2, dir);
    		}
    	}
    	
       	// 4번톱니인 경우
    	if(num == 4) {
    		rotateGear(num, dir);
    		if(flag3) {
    			rotateGear(num-1, -dir);
    		}
    		if(flag3 && flag2) {
    			rotateGear(num-2, dir);
    		}
    		if(flag3 && flag2 && flag1) {
    			rotateGear(num-3, -dir);
    		}
    	}
    		
    }
    
    public static void rotateGear(int num, int dir) {
    	// 시계 방향
    	if(dir == 1) {
    		int last = gears.get(num-1).removeLast();
    		gears.get(num-1).addFirst(last);
    	}
    	
    	// 반시계방향
    	if(dir == -1) {
    		int first = gears.get(num-1).getFirst();
    		gears.get(num-1).addLast(first);
    		gears.get(num-1).removeFirst();
    	}
    }
}
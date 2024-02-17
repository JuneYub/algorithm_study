
import java.util.*;
import java.io.*;

class Bucket {
	int a, b, c;
	
	Bucket(int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

public class Main {
	static int a, b, c;
	static List<Integer> result = new ArrayList<>();
	static boolean visited[][];
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		Queue<Bucket> q = new ArrayDeque<>();
		q.add(new Bucket(0, 0, c));
		visited = new boolean[201][201];
		
		
		while(!q.isEmpty()) {
			Bucket bu = q.poll();
//			System.out.println();
//			System.out.print( bu.a + " " + bu.b + " " + bu.c);
			
			if(visited[bu.a][bu.b] == true) continue;
			
			if(bu.a == 0 && !result.contains(bu.c)) {
				result.add(bu.c);
			}
			
			visited[bu.a][bu.b] = true;
			
			// 부었는데 넘치는 경우, 모자라게 채우는 경우
			// c -> a
			if(bu.a + bu.c > a) {
				q.add(new Bucket(a, bu.b, bu.c-(a-bu.a) ));
			} else {
				q.add( new Bucket(bu.a+bu.c, bu.b, 0));
			}
			// c -> b
			if(bu.b + bu.c > b) {
				q.add(new Bucket(bu.a, b, bu.c-(b-bu.b) ));
			} else {
				q.add( new Bucket(bu.a, bu.b+bu.c, 0));
			}
			
			// b -> a
			if(bu.a + bu.b > a) {
				q.add(new Bucket(a, bu.b-(a-bu.a), bu.c));
			} else {
				q.add( new Bucket(bu.a+bu.b, 0, bu.c));
			}
			// b -> c
			if(bu.c + bu.b > c) {
				q.add(new Bucket(bu.a, bu.b-(c-bu.c), c));
			} else {
				q.add( new Bucket(bu.a, 0, bu.c+bu.b));
			}
			
			// a -> b
			if(bu.b + bu.a > b) {
				q.add(new Bucket(bu.a-(b-bu.b), b, bu.c));
			} else {
				q.add( new Bucket(0, bu.b+bu.a,bu.c));
			}
			// a -> c
			if(bu.c + bu.a > c) {
				q.add(new Bucket(bu.a-(c-bu.c), bu.b, c));
			} else {
				q.add( new Bucket(0, bu.b, bu.a+bu.c));
			}
		}

		Collections.sort(result);
		for(int i : result) {
			System.out.print(i + " ");
		}
		
		
		
	} // end of main
	
	public static void dfs(int depth) {
		
		
		
	}

}
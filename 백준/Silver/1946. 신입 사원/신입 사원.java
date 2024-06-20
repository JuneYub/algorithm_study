
import java.util.*;
import java.io.*;

class Point {
	int paper;
	int interview;
	
	Point(int paper, int interview) {
		this.paper = paper;
		this.interview = interview;
	}
}
public class Main {
	
	static int testCase;
	static int n;
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for(int tc = 1; tc <= testCase; tc++) {
			n = Integer.parseInt(br.readLine());
			List<Point> list = new ArrayList<>();
			
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int paper = Integer.parseInt(st.nextToken());
				int interview = Integer.parseInt(st.nextToken());
				
				list.add(new Point(paper, interview));
			}
			
			Collections.sort(list, new Comparator<Point>() {
				public int compare(Point o1, Point o2) {
					return o1.paper - o2.paper;
				}
			});
			
			int minIdx = list.get(0).interview;
			
			int cnt = 1;
			for(int i = 1; i < list.size(); i++) {
				if(minIdx > list.get(i).interview) {
					minIdx = list.get(i).interview;
					cnt++;
				}
			}
			System.out.println(cnt);
		
		}
		
	}
	
	
}

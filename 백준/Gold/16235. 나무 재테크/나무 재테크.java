
import java.util.*;
import java.io.*;


class Tree implements Comparable<Tree>{
	
	int x, y, age;
	
	// 필요한 양분
	int needNutrition;
	
	boolean live = true;
	
	Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
		this.needNutrition = age;
	}
	
	public boolean isLive() {
		return this.live;
	}
	
	// 번식은 5년마다 한다.
	public boolean isBreeding() {
		if(age % 5 == 0) return true;
		return false;
	}
	
	public int compareTo(Tree t) {
		return this.age - t.age;
	}
	
	
	// 나무의 나이 증가
	public void grow() {
		this.age++;
	}
	
	public void setNeedNutrition() {
		this.needNutrition = age;
	}
}

public class Main {


	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[] dy = {-1, -1 ,0 ,1, 1, 1, 0, -1};
	
	
	static int n,m,k;
	static int[][] nutrition;
	static int[][] map;
	
	static List<Tree> trees = new ArrayList<>();
	static List<Tree> newTrees;
	static List<Tree> deadTrees;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		nutrition = new int[n][n];
		map = new int[n][n];
		for(int y = 0; y < n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x < n; x++) {
				nutrition[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
					
			trees.add(new Tree(x-1, y-1, z));
		}
		
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < n; x++) {
				map[y][x] = 5;
			}
		}

		int years = 0;
		
		// 어린 나무 먼저 정렬
		Collections.sort(trees, new Comparator<Tree>() {
			public int compare(Tree t1, Tree t2) {
				return t1.age - t2.age;
			}
		});
		
		while(true) {
			if(years == k) {
				System.out.println(trees.size());
				break;
			}
			
			for(int season = 0; season < 4; season++) {
				if(season == 0) {
					spring();
				}
				else if(season == 1) {
					summer();
				}
				else if(season == 2) {
					fall();
				}
				else {
					winter();
				}
			}
			years++;
		}
		
	}
	
	public static void spring() {

		for(Tree t : trees) {
			
			// 필요한 영양분 세팅
			t.setNeedNutrition();
			
			// 땅에 영양분이 충분한 경우
			if(t.needNutrition <= map[t.y][t.x]) {
				map[t.y][t.x] -= t.needNutrition;
				t.needNutrition = 0;
				t.grow();
			}
			else {
				// 영양분 안먹고 나무 죽음
				t.live = false;
			}
		}
		
	}
	
	public static void summer() {
		for(int i = 0; i < trees.size(); i++) {
			// 나무가 죽은 상태인 경우
			if(!trees.get(i).live) {
				map[trees.get(i).y][trees.get(i).x] += trees.get(i).age / 2;
			}
		}
	}
	
	public static void fall() {
		newTrees = new ArrayList<>();
		for(Tree t : trees) {
			if(t.isLive() && t.isBreeding()) {
				breeding(t.x, t.y);
			}
		}
		
		for(Tree t : trees) {
			if(t.isLive()) {
				newTrees.add(t);
			}
		}
		
		trees = newTrees;
		
	}
	
	public static void winter() {
		putNutrition();
	}
	
	public static void breeding(int x, int y) {
		for(int i = 0; i < 8; i++) {
			int newX = x + dx[i];
			int newY = y + dy[i];
			
			if(isRange(newX, newY) ) {
				newTrees.add(new Tree(newX, newY, 1));
			}
		}
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= n || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
	
	public static void putNutrition() {
		for(int y = 0; y < n; y++) {
			for(int x = 0; x < n; x++) {
				map[y][x] += nutrition[y][x];
			}
		}
	}
	
}

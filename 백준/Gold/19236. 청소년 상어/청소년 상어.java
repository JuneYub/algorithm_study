import java.util.*;
import java.io.*;


class Fish implements Cloneable {
	int x,y,dir;
	int id;
	boolean isDie = false;
	
	Fish (int x, int y, int id, int dir) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.dir = dir;
	}

	@Override
	protected Fish clone() throws CloneNotSupportedException {
		return (Fish)super.clone();
	}
}

class Shark implements Cloneable{
	int x,y;
	int dir;
	int ateFish = 0;
	List<Integer> ateList= new ArrayList<>();
	
	@Override
	protected Shark clone() throws CloneNotSupportedException {
		Shark cloned = (Shark)super.clone();
		cloned.ateList = new ArrayList<>(this.ateList);
		return cloned;
	}
	
	public void sysList() {
		for(Integer num : ateList) {
			System.out.print(num + " ");
		}
		System.out.println();
	}
}

public class Main {

	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int result = Integer.MIN_VALUE;
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map;
		HashMap<Integer, Fish> hashMap = new HashMap<>();
		
		map = new int[4][4];
		for(int y = 0; y < 4; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x = 0; x< 4; x++) {
				int id = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) -1;
				
				Fish fish = new Fish(x, y, id, dir);
				
				// 물고리를 저장할 딕션너리
				map[y][x] = id;
				hashMap.put(id, fish);
			}
		}
		
		Shark shark = new Shark();
		
		dfs(map, hashMap, shark, 0, 0);
		
		System.out.println(result);
	}

	
	// 물고기의 id 오름차순으로 뽑아서 물고리를 이동시킨다.
	public static void dfs(int[][] map, HashMap<Integer, Fish> hashMap, Shark shark, int moveX, int moveY) throws CloneNotSupportedException {
		

	    Fish fishToEat = hashMap.get(map[moveY][moveX]);
	    
		// 기존 상어의 위치는 0으로 수정
		map[shark.y][shark.x] = 0;
		
		// 상어의 물고기 먹기
		shark.x = moveX;
		shark.y = moveY;
	    
	    shark.dir = fishToEat.dir;
	    shark.ateFish += fishToEat.id;
	    shark.ateList.add(fishToEat.id);
	    fishToEat.isDie = true;
		
		//System.out.println("상어가 먹은 물고기 " + map[moveY][moveX] + "상어의 위치( " + moveY + " " + moveX + " )" + " 상어가 전체 먹은량 : " + shark.ateFish );
		//shark.sysList();
		
		map[moveY][moveX] = -1;
		
//		System.out.println("========================= 상어 이동 후 ");
//		for(int y = 0; y < 4; y++) {
//			for(int x =0; x< 4; x++) {
//				System.out.print(map[y][x] + " ");
//			}
//			System.out.println();
//		}

		
		for(int idNum = 1; idNum <=16; idNum++) {
			Fish fish = hashMap.get(idNum);
			if(!fish.isDie) {
				int dir = fish.dir;
				
				for(int i = 0; i < 8; i++) {
					// 기존 dir = 4 인경우 (4+0)%8 => 4 (4+1)%8=>5 (4+2)%8=>6... (4+4)%8=>0 ... (4+7)%8=>3
					int newDir = (dir + i) % 8;
					int nx = fish.x + dx[newDir];
					int ny = fish.y + dy[newDir];
					
					// 맵 범위에 들지않으면 패스
					if(!isRange(nx, ny)) continue;
					
					// 이동할 지점에 상어가 있으면 패스
					if(map[ny][nx] == -1) continue;
					
					// 이동할 지점에 있는 숫자는 따로 저장한다.
					int tmp = map[ny][nx];
					map[ny][nx] = map[fish.y][fish.x];
					map[fish.y][fish.x] = tmp;
					
					// 해당 지점에 물고기가 위치해 있었다면 해당 물고기를 찾아 위치를 수정해준다.
					if(tmp > 0) {
						Fish changedFish = hashMap.get(tmp);
						changedFish.x = fish.x;
						changedFish.y= fish.y;
					}
					
					// 현재 물고기의 위치를 갱신해준다.
					fish.y = ny;
					fish.x = nx;
					fish.dir = newDir;
					break;
				}
			}
		} // 물고기의 id를 오름차순으로 탐색한다.
		
		
//		System.out.println("========================= 물고기 이동 후 ");
//		for(int y = 0; y < 4; y++) {
//			for(int x =0; x< 4; x++) {
//				System.out.print(map[y][x] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		
		// 상어가 움직일 차례 ================================================================
		int nx = shark.x;
		int ny = shark.y;
		while(true) {
			int dir = shark.dir;
			nx += dx[dir];
			ny += dy[dir];
			
			// 상어의 이동 방향은 맵 안에 있어야 하고 물고기가 위치해 있어야 한다.
			if(isRange(nx, ny)) {
				if(map[ny][nx] > 0) {
					dfs(deepCopy(map), deepCopy(hashMap), shark.clone(), nx, ny);
				}
			}
			else {
				break;
			}
		}
		
		// 상어가 모두 
		result = Math.max(result, shark.ateFish);
		return;
	}
	
	public static boolean isRange(int x, int y) {
		if(x < 0 || x >= 4 || y < 0 || y >= 4) return false;
		return true;
	}
	
	public static int[][] deepCopy(int[][] map) {
		int[][] clone = new int[4][4];
		for(int y= 0; y < 4; y++) {
			clone[y] = map[y].clone();
		}
		return clone;
	}
	
	public static HashMap<Integer, Fish> deepCopy(HashMap<Integer, Fish> hashMap) throws CloneNotSupportedException {
		HashMap<Integer, Fish> clone = new HashMap<>();
		
		for(Integer key : hashMap.keySet()) {
			clone.put(key, hashMap.get(key).clone());
		}
		
		return clone;
	}
}

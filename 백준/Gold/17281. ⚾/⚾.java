
import java.util.*;
import java.io.*;


public class Main {
	
	static int n;
	static boolean[] visited;
	static int[] players;
	static int[][] iningList;
	static int maxPoint = Integer.MIN_VALUE;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		iningList = new int[n][9];
		
		StringTokenizer st;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				iningList[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[9];
		players = new int[9];
		players[3] = 0;
		visited[0] = true;
		setTasoon(0);
		System.out.println(maxPoint);
		
	} // end of main
	
	public static void setTasoon(int soonsu) {
		if(soonsu == 9) {
			// 게임 시작
			playGame();
			return;
		}
		
		if(soonsu != 3) {
			for(int i = 0; i < 9; i++) {
				// 해당 이닝의 선수한테 아직 순서가 배정되어 있지 않다면 배정을 해준다.
				if(!visited[i] && soonsu != 3) {
					visited[i] = true;
					players[soonsu] = i;
					setTasoon(soonsu+1);
					visited[i] = false;
				}
			}
		} else {
			
			setTasoon(soonsu+1);
			
		}

	} // end of setTasoon
	
	public static void playGame() {
		int[][] playersMap = new int[n][9];
		int point = 0; // 점수
		int outCount = 0; // 아웃카운트
		int finalTaja = 0; // 마지막 타자순번
		int[] jinroo = new int[3];
//		
//		System.out.println(Arrays.toString(playersMap[0]));
//		System.out.println(Arrays.toString(playersMap[1]));
		
		// 배치된 선수를 토대로 게임 시작
		for(int y = 0; y < n; y++) {
			// 새로운 이닝 시작
			for(int i = 0; i < 3; i++) {
				jinroo[i] = 0;
			}
			outCount = 0;
			
			inning:
			for(int x = finalTaja; x < 9; x++) {
//				System.out.print("선수 번호" +playersMap[y][x]);
				// 아웃이면 아웃카운트 1증가
				if(iningList[y][players[x]] == 0) {
					outCount++;
					if(outCount == 3) {
						// 3아웃이면 마지막 타자순번 + 1 한 다음에 9로 나눈 나머지를 다음 타자 순서로 입력한다.
						finalTaja = (x+1)%9;
						break inning;
					}
				}
				
				
				// 안타 친 경우 주자 1루씩 전진
				else if(iningList[y][players[x]] == 1) {
					for(int i = 2; i >= 0; i--) {
						if(jinroo[i] != 0 && i == 2) {
							point++;
							jinroo[i] = 0;
						}
						
						else if(jinroo[i] != 0) {
							jinroo[i+1] = 1;
							jinroo[i] = 0;
						}
					}
					jinroo[0] = 1; // 타자도 1루로 이동
				}
				
				// 이루타 친 경우 주자 2루씩 전진
				else if(iningList[y][players[x]] == 2) {
					for(int i = 2; i >= 0; i--) {
						if(jinroo[i] != 0 && i >= 1) {
							point++;
							jinroo[i] = 0;
						}
						
						else if(jinroo[i] != 0 && i == 0) {
							jinroo[i+2] = 1;
							jinroo[i] = 0;
						}
					}
					jinroo[1] = 1; // 타자도 2루로 이동
				}
				
				// 삼루타 친 경우 주자 3루씩 전진
				else if(iningList[y][players[x]] == 3) {
					for(int i = 2; i >= 0; i--) {
						if(jinroo[i] != 0) {
							point++;
							jinroo[i] = 0;
						}
					}
					jinroo[2] = 1; // 타자도 3루로 이동
				}
				
				// 홈런 친 경우 모두 사람들 1점획득
				else if(iningList[y][players[x]] == 4) {
					for(int i = 2; i >= 0; i--) {
						if(jinroo[i] != 0) {
							point++;
							jinroo[i] = 0;
						}
					}
					point++; // 타자도 점수획득
				}
				
//				System.out.println("타순 끝  점수: " + point);
//				for(int i = 0; i < 3; i++) {
//					System.out.print(jinroo[i] + " ");
//				}
//				System.out.println();
//				if(point == 47) System.exit(0);;
				
				if(x == 8 && outCount < 3) {
					x = -1;
				}
				
			} // end of inning
			maxPoint = Math.max(point, maxPoint);
		}
	}
}







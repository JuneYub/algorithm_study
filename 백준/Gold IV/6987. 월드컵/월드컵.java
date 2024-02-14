
import java.util.*;
import java.io.*;


class Nation {
    int win;
    int draw;
    int defeat;

    Nation(int win, int draw, int defeat) {
        this.win = win;
        this.draw = draw;
        this.defeat = defeat;
    }
}
public class Main {

static List<Nation> nationList;
static int result;
public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    	label:
        for(int tc = 0; tc < 4; tc++) {
	        
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        nationList = new ArrayList<>();
	        
	        // 데이터 넣기
	        boolean flag = false;
	        for(int i = 0; i < 6; i++) {
	            int sum = 0;
	                
                int win = Integer.parseInt(st.nextToken());
                int moo = Integer.parseInt(st.nextToken());
                int defeat = Integer.parseInt(st.nextToken());
                sum = win + moo + defeat;
                
                nationList.add(new Nation(win, moo, defeat));

	            if(sum != 5) {
	            	flag = true;
	            }
	        }
	        
	        if( flag ) {
                System.out.println(0);
                continue label;
            }
	        
	        result = 0;
	        
	        // A -> {B, C, D, E, F} B-> {C,D,E,F} , C-> {D,E,F} , D->{E,F}, E->{F}
	        // 이런식으로 탐색 첫 시작은 0,1 즉 A,B이다.
	        dfs(0,1);
	        System.out.println(result);
	    }
	} //  end of public static void main(String args[]) throws Exception

	public static void dfs(int teamA, int teamB) {
		// 상대팀은 끝까지 본 상태
		if(teamB == 6) {
			// 새로운 시작 A,B 로 시작했다면 다음은 B,C이다.
			dfs(teamA+1, teamA+2);
			return;
		}
		
		// 모든 경기를 끝냈다.
		if(teamA > 4) {
			for(int i = 0; i < 6; i++) {
				if(nationList.get(i).win != 0 || nationList.get(i).draw != 0 || nationList.get(i).defeat != 0) {
					result = 0;
					return;
				}
			}
			result = 1;
			return;
		}
		
		// 우리는 이기고 너흰 지고
		if(nationList.get(teamA).win > 0 && nationList.get(teamB).defeat > 0) {
			nationList.get(teamA).win--;
			nationList.get(teamB).defeat--;
			dfs(teamA, teamB+1);
			nationList.get(teamA).win++;
			nationList.get(teamB).defeat++;
		}
		
		// 둘다 무승부
		if(nationList.get(teamA).draw > 0 && nationList.get(teamB).draw > 0) {
			nationList.get(teamA).draw--;
			nationList.get(teamB).draw--;
			dfs(teamA, teamB+1);
			nationList.get(teamA).draw++;
			nationList.get(teamB).draw++;
		}
		
		// 우린 지고 너흰 이기고
		if(nationList.get(teamA).defeat > 0 && nationList.get(teamB).win > 0) {
			nationList.get(teamA).defeat--;
			nationList.get(teamB).win--;
			dfs(teamA, teamB+1);
			nationList.get(teamA).defeat++;
			nationList.get(teamB).win++;
		}
	}

}
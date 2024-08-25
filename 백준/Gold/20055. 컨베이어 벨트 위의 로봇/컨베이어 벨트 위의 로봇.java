import java.util.*;
import java.io.*;


class Belt {
	int durability;
	int position;
	boolean isRobotExitst;
	
	public Belt(int position, int durability) {
		this.position = position;
		this.durability = durability;
		this.isRobotExitst = false;
	}
}

class Robot {
	int trackId;
	boolean isFinish;
	
	Robot(int trackId) {
		this.trackId = trackId;
		this.isFinish = false;
	}
}

public class Main {
	

	static int n, k;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	n = Integer.parseInt(st.nextToken());
    	k = Integer.parseInt(st.nextToken());
    	
    	Belt[] belts = new Belt[2*n];
    	Queue<Robot> robots = new ArrayDeque<>();
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < 2*n; i++) {
    		int d = Integer.parseInt(st.nextToken());
    		belts[i] = new Belt(i+1, d);
    	}
    	
    	int time = 0;
    	int ans = 0;
    	int currStartBeltId = 0;
    	
    	while(true) {
    		time++;
    		
    		
    		// 컨베이어 벨트의 끝에서부터 진행한다.
    		for(int i = 2*n-1; i >= 0; i--) {
    			Belt curBelt = belts[i];
    			
    			// 벨트 위치 한칸 이동
    			curBelt.position = (curBelt.position) % (2*n);
    			curBelt.position++;
    			if(curBelt.position == 1) {
    				currStartBeltId = i;
    			}
    		}
    		
    		Queue<Robot> newQ = new ArrayDeque<>();
    		// 로봇도 한칸씩 이동하고 내릴 로봇은 내린다.
    		while(!robots.isEmpty()) {
    			Robot robot = robots.poll();
    			
    			// 이동하기 전에 내릴 로봇은 내리고 다음 로봇 진행
    			if(belts[robot.trackId].position == n) {
    				robot.isFinish = true;
    				belts[robot.trackId].isRobotExitst = false;
    				continue;
    			}
    			
    			// 다음 트랙
    			int nextBeltId = (robot.trackId + 1)%(2*n);
    			
    			// 다음 칸에 로봇 존재하면 안됨
    			if(belts[nextBeltId].isRobotExitst) {
    				newQ.add(robot);
    				continue;
    			}
    			
    			if(belts[nextBeltId].durability > 0) {
    				belts[robot.trackId].isRobotExitst = false;
    				belts[nextBeltId].isRobotExitst = true;
    				robot.trackId = nextBeltId;
    				
    				if(--belts[nextBeltId].durability == 0) {
    					ans++;
    				}
    				
    				// 이동한 곳이 n이면 해당 로봇도 내려야한다.
    				if(belts[nextBeltId].position == n) {
    					robot.isFinish = true;
    					belts[nextBeltId].isRobotExitst = false;
    					continue;
    				}
    			}
    			newQ.add(robot);
    			
    		}
    		robots = newQ;
    		
    		// 로봇을 태워도 되는지 확인한다.
    		if(belts[currStartBeltId].durability > 0) {
    			robots.add(new Robot(currStartBeltId));
    			belts[currStartBeltId].isRobotExitst = true;
    			if(--belts[currStartBeltId].durability == 0) {
    				ans++;
    			}
    		}
    		if(ans >= k) break;
    	}
    	System.out.print(time);
    	
    	
    	
    }
}

import java.util.*;
import java.io.*;



class Truck {
	int weight;
	int moveLength;
	
	Truck(int weight) {
		this.weight = weight;
	}
}

public class Main {

	static int n, w, l;
    public static void main(String args[]) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.parseInt(st.nextToken());
    	// 다리길이
    	w = Integer.parseInt(st.nextToken());
    	// 다리의 최대하중
    	l = Integer.parseInt(st.nextToken());
    	
    	Queue<Integer> trucks = new ArrayDeque<Integer>();
    	List<Truck> bridge = new ArrayList<Truck>();
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i = 0; i < n; i++) {
    		trucks.add(Integer.parseInt(st.nextToken()));	
    	}
    	
    	int time = 0;
    	int totalWeight = 0;
    	
    	while(true) {
    		time++;
    		
    		if(bridge.size() < w && !trucks.isEmpty()) {
    			if(totalWeight + trucks.peek() <= l) {
    				totalWeight += trucks.peek();
    				bridge.add(new Truck(trucks.poll()));
    			}
    		}
    		
    		// 다리 위 트럭들 1칸씩 이동
    		for(int i = 0; i < bridge.size(); i++) {
    			bridge.get(i).moveLength++;
    		}
    		
    		// 앞에 트럭이 다리 모두 건넜으면 제거
    		if(bridge.get(0).moveLength > w) {
    			totalWeight -= bridge.get(0).weight;
    			bridge.remove(0);
    		}
    		
    		if(bridge.size() < w && !trucks.isEmpty()) {
    			if(totalWeight + trucks.peek() <= l) {
    				totalWeight += trucks.peek();
    				bridge.add(new Truck(trucks.poll()));
    				bridge.get(bridge.size()-1).moveLength++;
    			}
    		}
    		
    		
    		
    		if(bridge.size() == 0 && trucks.isEmpty()) {
    			break;
    		}
    	}
    	System.out.println(time);
    }
    
}
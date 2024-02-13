
import java.util.*;
import java.io.*;


class Person {
	int 예시;
	int 도시;
	
	Person(int t1, int t2) {
		예시 = t1;
		도시 = t2;
	}
}
public class Main {

	static int n;

	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    n = Integer.parseInt(br.readLine());
	    
	    Map<Integer, Person> hashMap = new LinkedHashMap<>();
	    PriorityQueue<Person> personList = new PriorityQueue<>(new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				if(o1.도시 == o2.도시) {
					return o1.예시 - o2.예시;
				}
				return o1.도시 - o2.도시;
			}
		});
	    
	    StringTokenizer st;
	    for(int i = 0; i < n; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	int t1 = Integer.parseInt(st.nextToken());
	    	int t2 = Integer.parseInt(st.nextToken());
	    	
	    	personList.add(new Person(t1, t2));
	    }
	    
	    int time = 0;
	    int result = 0;
	    while(!personList.isEmpty() || !hashMap.isEmpty()) {
	    	time++;
	    	
	    	if(!personList.isEmpty()) {
	    		int 도착한시각 = personList.peek().도시;
	    		// 사람리스트의 사람이 도착한 시각이 현재시각과 같은 경우
	    		if(time == 도착한시각) {
	    			
		    		while(!personList.isEmpty() && 도착한시각 == personList.peek().도시) {
		    			Person person = personList.poll();
		    			hashMap.put(person.예시, person);
		    		}
	    		}
	    		
	    	}
	    	
    		if(!hashMap.isEmpty() && hashMap.containsKey(time)) {
    			Person p = hashMap.get(time);
    			result = Math.max(result, time - p.도시);
    			hashMap.remove(time);

    			
    		} else if(!hashMap.isEmpty()) {
    			
    			Iterator<Integer> it = hashMap.keySet().iterator();
    			if(it.hasNext()) {
    				int key = it.next();
    				result = Math.max(result, time - hashMap.get(key).도시);
    				hashMap.remove(key);
    				continue;
    			}
    		}
	    	
	    }
	    System.out.println(result);
	}
    
}
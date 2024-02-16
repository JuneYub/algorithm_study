
import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int truePersonCnt;
	static int result;
	public static void main(String args[]) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    
	    n = Integer.parseInt(st.nextToken());
	    m = Integer.parseInt(st.nextToken());
	    
	    st = new StringTokenizer(br.readLine());
	    
	    truePersonCnt = Integer.parseInt(st.nextToken());
	    Set<Integer> truePeople = new HashSet<>();
	    
	    for(int i = 0; i < truePersonCnt; i++) {
	    	truePeople.add(Integer.parseInt(st.nextToken()));
	    }
	    
	    // 파티 정보
	    String[] partyInfo = new String[m];
	    for(int i = 0; i < m; i++) {
	    	partyInfo[i] = br.readLine();
	    }
	    
	    for(int k = 0; k < m; k++) {
		    // 파티를 돈다.
		    for(int i = 0; i < m; i++) {
		    	st = new StringTokenizer(partyInfo[i]);
		    	// 파티 인원 수
		    	int cnt = Integer.parseInt(st.nextToken());
		    	
		    	Set<Integer> partyPeople = new HashSet<>();
		    	
		    	for(int j = 0; j < cnt; j++) {
		    		int people = Integer.parseInt(st.nextToken());
		    		partyPeople.add(people);
		    	}
		    	
		    	Set<Integer> tmp = new HashSet<>(partyPeople);
		    	tmp.removeAll(truePeople);
		    	
		    	if(tmp.size() != partyPeople.size()) {
		    		truePeople.addAll(partyPeople);
		    	} else if (k==(m-1) && tmp.size() == partyPeople.size()){
		    		result++;
		    	}
		    }
	    }

	    
	    System.out.println(result);
	  
	}
}


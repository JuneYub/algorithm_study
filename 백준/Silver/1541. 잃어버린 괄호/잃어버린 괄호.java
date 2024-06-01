import java.util.*;
import java.io.*;

public class Main
{
	public static void main(String[] args) throws Exception {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    // A-B+C => A , B+C
	    StringTokenizer st = new StringTokenizer(br.readLine(), "-");
	    int ans = 100000;
	    // A, B+C
	    
	    while(st.hasMoreTokens()) {
	        int tmp = 0;
	        // A => A, B+C => B, C
	        StringTokenizer stPlus = new StringTokenizer(st.nextToken(), "+");
	        
	        // 덧셈 진행
	        while(stPlus.hasMoreTokens()) {
	            tmp += Integer.parseInt(stPlus.nextToken());
	        }
	        
    	    if(ans == 100000) {
    	        ans = tmp;
    	    } else {
    	        ans -= tmp;
    	    }
	    
	        
	    }

	    System.out.println(ans);    
	    
	}
	
}
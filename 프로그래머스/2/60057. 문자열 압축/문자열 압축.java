import java.util.*;

class Solution {
    public int solution(String s) {

        int minLength = s.length();
        
        for(int n = 1; n <= s.length() / 2; n++) {
            StringBuilder compressed = new StringBuilder();
            String current = s.substring(0, n);
            
            int count = 1;
            System.out.println();
            for(int i = n; i < s.length(); i+= n) {
                
                String next;
                if( i+n > s.length()) {
                    next = s.substring(i);
                } else {
                    next = s.substring(i, i+n);
                }
                
                if(current.equals(next)) {
                    count++;
                } else {
                    if(count > 1) {
                        compressed.append(count);
                    }
                    compressed.append(current);
                    
                    
                    current = next;
                    count = 1;
                }
            }
            
            if(count > 1) {
                compressed.append(count);
            }
            compressed.append(current);
            minLength = Math.min(minLength, compressed.length());
        }
        
        return minLength;
        
    }
}
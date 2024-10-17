import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<String> list = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            list.add(str);
        }

        int sIdx = n;
        int tIdx = n;
        int maxLen = 0;
        
        for(int i = 0; i < n-1; i++) {
            for(int j = i+1; j < n; j++) {
                int num = getCompareLength(list.get(i), list.get(j));
                if(num > maxLen) {
                    maxLen = num;
                    sIdx = i;
                    tIdx = j;
                }
            }
        }

        System.out.println(list.get(sIdx));
        System.out.println(list.get(tIdx));
        
    }

    public static int getCompareLength(String s1, String s2) {
        
        int minLen = Math.min(s1.length(), s2.length());
        for(int k = 0; k < minLen; k++) {
            if(s1.charAt(k) != s2.charAt(k)) {
                return k;
            }
        }
        return minLen;
    }
    
}
import java.io.*;
import java.util.*;

class Solution {
    
    int n;
    int count = 0;
    int[][] q;
    int[] ans;
    
    
    public int solution(int n, int[][] q, int[] ans) {
        this.n = n;
        this.q = q;
        this.ans = ans;
        
        dfs(1, new ArrayList<>());
        return count;
    }
    
    public void dfs(int start, List<Integer> picked) {
        if(picked.size() == 5) {
            if(isValid(picked)) 
                count++;
            return;
        }
        
        for(int i = start; i <= n; i++) {
            picked.add(i);
            dfs(i + 1, picked);
            picked.remove(picked.size() - 1);
        }
    }
    
    public boolean isValid(List<Integer> picked) {
        
        for(int i = 0; i < q.length; i++) {
            int match = 0;   
            for(int num : q[i]) {
                if(picked.contains(num)) match++;
            }
            if(match != ans[i]) return false;
        }
        return true;
    }
    
}


import java.util.*;

class Info {
    int a;
    int b;
    
    public Info(int a, int b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(!(o instanceof Info)) return false;
        Info info = (Info) o;
        return a==info.a && b==info.b;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

class Solution {
    public int solution(int[][] info, int n, int m) {
        Set<Info> dp = new HashSet<>();
        dp.add(new Info(0, 0));
        
        for(int i = 0; i < info.length; i++) {
            int aTrace = info[i][0];
            int bTrace = info[i][1];
            
            Set<Info> nextDp = new HashSet<>();
            
            for(Info cur : dp) {
                if(cur.a + aTrace < n) {
                    nextDp.add(new Info(cur.a + aTrace, cur.b));
                }
                if(cur.b + bTrace < m) {
                    nextDp.add(new Info(cur.a, cur.b + bTrace));
                }
            }
            if(nextDp.isEmpty()) return -1;
            dp = nextDp;
        }
        
        int min = Integer.MAX_VALUE;
        for(Info cur : dp) {
            min = Math.min(min, cur.a);
        }
        return min;
    }
}
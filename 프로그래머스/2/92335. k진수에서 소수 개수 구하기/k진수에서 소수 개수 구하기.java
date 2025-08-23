import java.util.*;

class Solution {
    public int solution(int n, int k) {
        String base = toBaseK(n, k);
        
        String[] parts = base.split("0+");
        
        int count = 0;
        for(String part : parts) {
            if((part.isEmpty())) continue;
            long val = Long.parseLong(part);
            if(isPrime(val)) count++;
        }
        return count;
    }
    
    public String toBaseK(long n, int k) {
        if(n == 0) return "0";
        StringBuilder sb = new StringBuilder();
        while(n > 0) {
            sb.append(n % k);
            n /= k;
        }
        
        return sb.reverse().toString();
    }

    private boolean isPrime(long x) {
        if(x < 2 ) return false;
        if(x == 2) return true;
        if(x % 2 == 0) return false;
        
        long limit = (long) Math.sqrt(x);
        
        for(long d = 3; d <= limit; d+= 2) {
            if(x % d == 0) return false;
        }
        return true;
    }
}
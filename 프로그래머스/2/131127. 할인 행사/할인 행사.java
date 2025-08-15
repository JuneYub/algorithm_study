import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        
        Map<String, Integer> wantMap = new HashMap<>();
        
        for(int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }
        
        Map<String, Integer> disMap = new HashMap<>();
        
        // 초기 값 세팅
        for(int i = 0; i < 10; i++) {
            disMap.put( discount[i], disMap.getOrDefault(discount[i], 0) + 1);
        }
        // 개수 세기
        int answer = 0;
        
        // 할인받을 수 있는지
        if(isDiscount(wantMap, disMap)) {
            answer++;   
        }
        

        if(discount.length >= 10) {
            for(int i = 10; i < discount.length; i++) {
                
                if(disMap.get(discount[i-10]) > 0)  
                    disMap.put( discount[i-10], disMap.get(discount[i-10]) -1);
                
                 disMap.put( discount[i], disMap.getOrDefault(discount[i], 0) + 1);
                
                if(isDiscount(wantMap, disMap)) {
                    answer++;   
                }
            }
        }

        return answer;
    }
    
    public static boolean isDiscount(Map<String, Integer> wantMap, Map<String, Integer> disMap) {
    
        for(String key : wantMap.keySet()) {
            if(wantMap.get(key) != disMap.get(key)) return false;;
        }
        
        return true;
    }
}
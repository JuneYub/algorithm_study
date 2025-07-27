import java.util.*;

class Solution {
    
    static int 이동지점;
    static int dIdx;
    static int pIdx;
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        
        dIdx = deliveries.length-1;
        pIdx = deliveries.length-1;
        
        boolean 수거완료여부 = false;
        long answer = 0;
        
        while(!수거완료여부) {
            
            // 뒤에서부터 배달/수거 할 물건이 존재하는 곳 찾기
            while(dIdx >= 0 && deliveries[dIdx] == 0) {
                dIdx--;
            }
           
            while(pIdx >= 0 && pickups[pIdx] == 0) {
                pIdx--;
            }
            
            if(dIdx < 0 && pIdx < 0) break; // 수거완료
            int idx = Math.max(dIdx, pIdx);
            answer += (idx+1)*2;
            
            int remain_d = cap;
            int remain_p = cap;
            
            while(remain_d > 0 && dIdx >= 0) {
                if(remain_d >= deliveries[dIdx]) {
                    remain_d -= deliveries[dIdx];
                    deliveries[dIdx] = 0;
                    dIdx--;
                } else {
                    deliveries[dIdx] -= remain_d;
                    remain_d = 0;
                }
            }
            
            while(remain_p > 0 && pIdx >= 0) {
                if(remain_p >= pickups[pIdx]) {
                    remain_p -= pickups[pIdx];
                    pickups[pIdx] = 0;
                    pIdx--;
                } else {
                    pickups[pIdx] -= remain_p;
                    remain_p = 0;
                }  
            }
        }
        
        return answer;
    }
}
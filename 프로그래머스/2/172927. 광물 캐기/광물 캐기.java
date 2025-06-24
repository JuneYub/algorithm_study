import java.util.*;

class PickOrder implements Comparable<PickOrder> {
    int order;
    int total;
    int dia;
    int iron;
    int stone;
    
    public PickOrder(int order, int total, int dia, int iron, int stone) {
        this.order = order;
        this.total = total;
        this.dia = dia;
        this.iron = iron;
        this.stone = stone;
    }
    
    public int compareTo(PickOrder p) {
        return p.total - this.total;
    }
}


class Solution {
    static int dia = 0;
    static int iron = 0;
    static int stone = 0;
    
    public int solution(int[] picks, String[] minerals) {
        
        
        int cnt = 0;
        int chunkNum = 0;
        int minChunk = (picks[0] + picks[1] + picks[2]) * 5;

        int total = 0;
        List<PickOrder> pickorders = new ArrayList<>();
        
        for(int i = 0; i <= Math.min(minerals.length-1, minChunk); i++) {
            
            total += getFatigue(minerals[i]);
            cnt++;
            
            // 5개가 안채워졌는데 마지막이면
            if( i == minerals.length - 1 && cnt % 5 != 0) {
                pickorders.add(new PickOrder(chunkNum++ , total, dia, iron, stone));
                dia = 0;
                iron = 0;
                stone = 0;
            }
            
            // 5개가 채워지면 PickOrder를 넣는다.
            if( i >= 1 && cnt % 5 == 0) {
                pickorders.add(new PickOrder(chunkNum++ , total, dia, iron, stone));
                dia = 0;
                iron = 0;
                stone = 0;
                cnt = 0;
                total = 0;
            }
            
        }
        Collections.sort(pickorders);
        
        int result = 0;
        for(PickOrder p : pickorders) {
            for(int i = 0 ; i < 3; i++) {
                // 해당 곡갱이 사용
                if(picks[i] > 0) {
                    result += calculate(p, i);
                    picks[i]--;
                    break;
                } 
            }
        }
        
        return result;
        
    }
    
    public int calculate(PickOrder p, int pick) {
        int result = 0;
        
        if( 0 == pick) {
            result += p.dia*1;
            result += p.iron*1;
            result += p.stone*1;
        }
        else if(1 == pick) {
            result += p.dia*5;
            result += p.iron*1;
            result += p.stone*1;
        }
        else { 
            result += p.dia*25;
            result += p.iron*5;
            result += p.stone*1;
        }
        return result;
    }
    
    public int getFatigue(String mineral) {
        if("diamond".equals(mineral)) {
            dia++;
            return 25;
        }
        else if("iron".equals(mineral)) {
            iron++;
            return 5;
        }
        else { 
            stone++;
            return 1;
        }
    }
}
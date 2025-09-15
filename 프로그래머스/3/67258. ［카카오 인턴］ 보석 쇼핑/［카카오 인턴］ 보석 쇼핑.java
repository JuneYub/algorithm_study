import java.util.*;

class Solution {
    
    static Set<String> gemSet = new HashSet<>();
    static Map<String, Integer> map = new HashMap<>();
    public static int minLen = Integer.MAX_VALUE;
    public static int[] answer = new int[2];
    
    public int[] solution(String[] gems) {
        answer[0] = 0;
        answer[1] = Integer.MAX_VALUE;
        for(String gem : gems) {
            gemSet.add(gem);
            
        }
        
        int start = 0;
        int end = -1;
        
        
        if(gems.length == 1) {
            answer[0] = 1;
            answer[1] = 1;
            return answer;
        }
        
        while(start != gems.length && end != gems.length) {
            
            // 현재 보석이 구성되지 않은 경우
            if(!isFull()) {
                end++;
                if(end >= gems.length) break;
                //System.out.println("end : " + end + " " + gems[end]);
                addGem(gems[end]);
            } 
            // 현재 보석이 구성되어있는 경우
            else {
                //System.out.println("start : " + start + " " + gems[start]);
                removeGem(gems[start]);
                start++;
                
            }
            
            if(isFull()) {
                if(end - start < answer[1] - answer[0]) {
                    answer[0] = start+1;
                    answer[1] = end+1;
                }
            }
        }
        
        
        
        return answer;
    }
    
    public static boolean isFull() {
        if(map.size() == gemSet.size()) return true;
        return false;
    }
    
    public static void addGem(String name) {
        
        if(map.get(name) == null) {
            map.put(name, 1);
        } else {
            map.put(name, map.get(name) + 1);   
        }
    }
    
    public static void removeGem(String name) {
        if(map.get(name) == 1) {
            map.remove(name);
        } else {
            map.put(name, map.get(name) -1);
        }
    }
}
import java.util.*;

class Solution {
    
    public static int[] left;
    public static int[] right;
    public static int n;
    public int solution(int[] topping) {
        n = topping.length;
        left = new int[n];
        right = new int[n];
        
        
        
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            // set에 토핑 추가
            set.add(topping[i]);
            // left에 토핑의 개수 기록, i까지 자른다면
            //   1 2 2 3 3 4 4 4 
            //   1 2 1 3 1 4 1 2
            left[i] = set.size();
        }
        
        set = new HashSet<>();
        for(int i = n-1; i >= 0; i--) {
            set.add(topping[i]);
            right[i] = set.size();
            // right 토핑의 개수 기록, i까지 자른다면
            //   4 4 4 4 3 3 2 1
            //   1 2 1 3 1 4 1 2
        }
        
        int answer = 0;
        for(int i = 0; i < n-1; i++) {
            if(left[i] == right[i+1]) answer++;
        }

        return answer;
    }
}
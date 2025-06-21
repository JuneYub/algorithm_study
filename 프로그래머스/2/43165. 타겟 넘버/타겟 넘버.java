import java.util.*;

class Solution {
    int[] numbers;
    int target;
    int count = 0;
    
    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        dfs(0, new ArrayList<>(), new ArrayList<>());
        return count;
    }
    
    public void dfs(int depth, List<Integer> pickedNum, List<Integer> buho) {
        if(depth == numbers.length) {
            
            int result = 0;
            for(int i = 0; i < depth; i++) {
                result += (buho.get(i) == 0 ? pickedNum.get(i) : -pickedNum.get(i));
            }
            if(result == target) count++;
            return;
        }
        

        pickedNum.add(numbers[depth]);

        buho.add(0);
        dfs(depth+1, pickedNum, buho);
        buho.remove(buho.size() -1);

        buho.add(1);
        dfs(depth+1, pickedNum, buho);
        buho.remove(buho.size() -1);

        pickedNum.remove(pickedNum.size() -1);

    }
}
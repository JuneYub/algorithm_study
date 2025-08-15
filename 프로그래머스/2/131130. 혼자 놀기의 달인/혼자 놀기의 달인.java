import java.util.*;

class Solution {
    
    public static int n;
    public static int[] cards;
    public static int[] visited;
    public int solution(int[] cardsSample) {
        
        cards = cardsSample;
        n = cards.length;
        visited = new int[n];
        Arrays.fill(visited, -1);
        // dfs로 1번 상자를 구하는 프로세스를 진행한다.
        
        for(int i = 1; i <= n; i++) {
            if(visited[i-1] == -1) {
                dfs(visited, 1, i);    
            }
        }
        
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for(int i : visited) {
            if(i != -1) {
                countMap.put(i, countMap.getOrDefault(i, 0) + 1);
            }
        }
        
        List<Integer> list = new ArrayList<>();
        for(int key: countMap.keySet()) {
            int cnt = countMap.get(key);
            
            list.add(cnt);
        }
        Collections.sort(list, (a, b) -> b - a);
        
        if(list.size() >= 2) {
            int a = list.get(0);
            int b = list.get(1);    
            return a*b;
        }
        return 0;
    }
    
    public static void dfs(int[] visited, int sequence, int check) {
        
        if(visited[check-1] != -1) {
                //System.out.println(sequence + "회차가 끝났습니다");
                // for(int i = 0; i < n; i++) {
                //     if(visited[i] == sequence) System.out.print(cards[i] + " ");
                // }
                //System.out.println("");
                
                for(int i = 1; i <= n; i++) {
                    if(visited[i-1] == -1) {
                        dfs(visited, sequence+1, i);    
                    }
                }   
            return;
        }
        
        else {
            visited[check-1] = sequence;
            int num = cards[check-1];
            dfs(visited, sequence, num);
        }
        
        return;
    }
}
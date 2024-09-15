class Solution {
    
    public static boolean[] visited;
    public static int[][] computers;
    public static int n;
    public int solution(int max, int[][] orgComputers) {
        int answer = 0;
        n = max;
        // 컴퓨터 배열 초기화
        computers = new int[n][n];
        for(int y = 0; y < n; y++) {
            computers[y] = orgComputers[y].clone();
        }
        
        // 방문 노드 체크용
        visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                answer++;
                dfs(i);
            }
        }
        
        
        return answer;
    }
    
    public static void dfs(int node) {
        visited[node] = true;
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            if(computers[node][i] == 0) continue;
            if(node == i) continue;
            
            dfs(i);
        }
    }
}
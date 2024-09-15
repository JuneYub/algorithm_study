class Solution {
    static int maxLen;
    static int answer = Integer.MAX_VALUE;
    static String[] words;
    static boolean[] visited;
    static String target;
    public int solution(String begin, String end, String[] orgWords) {
        // 초기화
        maxLen = orgWords.length;
        words = new String[maxLen];
        visited= new boolean[maxLen];
        target = end;
        
        for(int i = 0; i < maxLen; i++) {
            words[i] = orgWords[i];
        }
        // 정답이 포함되어 있지 않은 경우
        for(int i = 0; i < maxLen; i++) {
            if(target.equals(words[i])) break;
            if(!target.equals(words[i]) && i == (words.length-1)) {
                return 0;
            }
        }
        
        // dfs
        dfs(begin, 0);
        if(answer == Integer.MAX_VALUE) answer = 0;
        return answer;
    }
    
    public static void dfs(String word, int step) {
        //System.out.println(word + " " + step + " " + answer);
        // 같은 경우
        if(word.equals(target)) {
            answer = Math.min(step, answer);
            return;
        }
        
        if(step >= answer) return;
        
        for(int i = 0; i < maxLen; i++) {
            // 방문한 곳이면 넘어간다.
            if(visited[i]) continue;
            
            int count = 0;
            for(int j = 0; j < word.length(); j++) {
                if(word.charAt(j) != words[i].charAt(j)) {
                    count++;
                }
            }
            
            if(count == 1) {
                visited[i] = true;
                dfs(words[i], step+1);
                visited[i] = false;
            }
            
        }
        
        
    }
}
import java.util.*;

class Solution {
    
    static String[] user_id;
    static String[] banned_id;
    
    public int solution(String[] i_user_id, String[] i_banned_id) {
        int answer = 0;
        user_id = Arrays.copyOf(i_user_id, i_user_id.length);
        banned_id = Arrays.copyOf(i_banned_id, i_banned_id.length);

        
        
        List<List<String>> candidates = new ArrayList<>();
        
        for(int i = 0; i < banned_id.length; i++) {
            List<String> tmp = new ArrayList<>();
            for(int j = 0; j < user_id.length; j++) {
                
                
                
                if(isMatch(banned_id[i], user_id[j])) {
                    tmp.add(user_id[j]);
                    
                }
            }
            candidates.add(tmp);
        }
        
        Set<Set<String>> result = new HashSet<>();
        
        dfs(candidates, 0, new HashSet<>(), result);
        
        
        return result.size();
    }
    
    private boolean isMatch(String banned, String user) {
        
        if(user.length() != banned.length()) return false;
        
        for(int i = 0; i < user.length(); i++) {
            
            if(banned.charAt(i) == '*') continue;
            
            //System.out.println(user.charAt(i) + " " + banned.charAt(i));
            
            if(user.charAt(i) != banned.charAt(i)) {
                return false;
            }
        }
        //System.out.println(user + " " + banned);
        
        return true;
    }
    
    private void dfs(List<List<String>> candidates,
                    int depth,
                    Set<String> visited,
                    Set<Set<String>> result) {
        
        if(depth == candidates.size()) {
            result.add(new HashSet<>(visited));
            return;
        }
        
        // 각 밴id의 매칭되는 user id를 돌면서
        for(String user : candidates.get(depth)) {
            if(!visited.contains(user)) {
                visited.add(user);
                dfs(candidates, depth+1, visited, result);
                visited.remove(user);
            }
        }
        
    }
}
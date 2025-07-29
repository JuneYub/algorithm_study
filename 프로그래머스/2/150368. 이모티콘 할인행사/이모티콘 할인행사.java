import java.util.*;

class Solution {
    
    public static int n;
    public static int[][] users;
    public static int[] emoticons;
    public static int[] 할인율옵션 = {10, 20, 30, 40};
    
    public static int[] answer = new int[2];
    
    public int[] solution(int[][] inputUsers, int[] inputEmoticons) {
        users = inputUsers;
        emoticons = inputEmoticons;
        
        n = emoticons.length;
        int[] 할인율 = new int[n];
        Arrays.fill(answer, 0);
        Arrays.fill(할인율, -1);
        dfs(0, 할인율);

        return answer;
    }
    
    public static void dfs(int depth, int[] 할인율) {
        if(depth == n) {
            checkUser(할인율);
            return;
        }
        
        for(int i = 0; i < 4; i++) {
            할인율[depth] = 할인율옵션[i];
            dfs(depth+1, 할인율);
            //할인율[depth] = -1;
        }
        
    }
    
    public static void checkUser(int[] 할인율) {
        
        int 가입자수 = 0;
        int 전체구매금액 = 0;
        
        for(int i = 0; i < users.length; i++) {
            int sum = 0;
            for(int j = 0; j < 할인율.length; j++) {
                
                if(users[i][0] <= (할인율[j]) ) {
                    int price = emoticons[j] * (100 - 할인율[j]) / 100;
                    //System.out.println( );
                    sum += price;
                    //System.out.println("구매 진행 " +  price  +  " sum : " +sum); 
                }
            }
            
            if(sum >= users[i][1]) 
                가입자수++;
            else 
                전체구매금액 += sum;   
        }
        
        if(answer[0] < 가입자수) {
            answer[0] = 가입자수;
            answer[1] = 전체구매금액;
        } else if(answer[0] == 가입자수) {
            if(answer[1] < 전체구매금액) {
                answer[0] = 가입자수;
                answer[1] = 전체구매금액;
            }
        }
        
    }
    
    
    
}
import java.util.*;

class Plan implements Comparable<Plan> {
    
    String name;
    int minute;
    int playtime;
    
    Plan(String name, int time, int minute, int playtime) {
        this.name = name;
        this.minute = (time * 60) + minute;
        this.playtime = playtime;
    }
    
    public int compareTo(Plan p) {
        return this.minute - p.minute;
    }
    
}


class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<Plan> q = new PriorityQueue<>();
        Stack<Plan> wait = new Stack<>();
        
        for(int i = 0; i < plans.length; i++) {
            String[] parts = plans[i][1].split(":");
            int time = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            int playTime = Integer.parseInt(plans[i][2]);
            q.add(new Plan(plans[i][0], time, minute, playTime));
        }
        
        List<String> answer = new ArrayList<>();
        
        while(!q.isEmpty()) {
            
            Plan curPlan = q.poll();
            int now = curPlan.minute;
            
            // 다음 과제가 있는 경우
            if(!q.isEmpty()) {
                
                // 현재 과제와 다음 과제의 시간을 비교
                Plan nextPlan = q.peek();
                
                // 1. 현재 과제가 끝나면 바로 다음 과제를 진행하는 경우
                if(now + curPlan.playtime == nextPlan.minute) {
                    answer.add(curPlan.name);
                    //System.out.println("1 번 수행 " + curPlan.name);
                }
                
                // 2. 현재 과제가 끝나고 다음 과제까지 시간이 남는 경우
                else if(now + curPlan.playtime < nextPlan.minute) {
                    answer.add(curPlan.name);
                    now += curPlan.playtime;
                    //System.out.println("2 번 수행 " + curPlan.name);
                    
                    // 대기과제가 있는 경우
                    while(!wait.isEmpty()) {
                        
                        Plan waitPlan = wait.pop();
                        if(now + waitPlan.playtime <= nextPlan.minute) {
                            //System.out.println("2 번 대기 과제 수행 " + waitPlan.name);
                            answer.add(waitPlan.name);
                            now += waitPlan.playtime;
                        } else {
                            
                            waitPlan.playtime = waitPlan.playtime - (nextPlan.minute - now);
                            wait.add(waitPlan);
                            break;
                        }
                    }
                } 
                
                // 3. 현재 과제를 다음 과제시작 전까지 못끝내는 경우
                else {
                    //System.out.println("3 번 과제 대기에 추가 " + curPlan.name);
                    curPlan.playtime = curPlan.playtime - (nextPlan.minute - now);
                    wait.add(curPlan);
                    
                }
                
            } 
            
            // 다음 과제가 없는 경우
            else {
                answer.add(curPlan.name);
                
                while(!wait.isEmpty()) {
                    answer.add(wait.pop().name);
                }
            }
            
        }
        
        String[] ans = answer.toArray(new String[answer.size()]);
        
        return ans;
    }
}
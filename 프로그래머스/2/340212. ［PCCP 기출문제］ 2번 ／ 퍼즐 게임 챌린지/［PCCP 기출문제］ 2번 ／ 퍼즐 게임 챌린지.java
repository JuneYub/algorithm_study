class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100_000;
        int ans = 100_000;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            if(solvedQuestion(diffs, times, limit, mid)) {
                right = mid - 1;
                ans = Math.min(mid, ans);
            } else {
                left = mid + 1;
            }
        }
        return ans;
        
    }
    
    public boolean solvedQuestion(int[] diffs, int[] times, long limit, int level) {
        // 전체 문제 개수
        int total = diffs.length;
        // 푼 문제 개수
        int i = 0;
        // 전체 문제 개수만큼 진행
        while(i < total) {
            //System.out.println(level + " : 레벨 " +i + " 번 문제 " + limit + " 잔여시간");
            int usedTime = 0;
            if(i == 0) {
                usedTime = solveTime(diffs[i], times[i], 0, level);
            } else {
                usedTime = solveTime(diffs[i], times[i], times[i-1], level);
            }
            
            limit -= usedTime;
            if(limit < 0) break;
            else {
                i++;
            }
        
        }
        if(i == total) return true;
        // System.out.println("실패");
        return false;
    }
    
    public int solveTime(int diff, int timeCur, int timePrev, int level) {
        if(diff <= level) {
            return timeCur;
        } else {
            return ((diff - level) * (timeCur + timePrev) + timeCur);           
        }
    }
}
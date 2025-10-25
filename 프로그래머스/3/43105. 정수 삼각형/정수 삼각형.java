class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        int[][] dp = new int[triangle.length][];
        
        for(int y = 0; y < n; y++) {
            dp[y] = new int[triangle[y].length];
        }
        
        if(n == 1) return triangle[0][0];
        dp[0][0] = triangle[0][0];
        
        for(int y = 1; y < n; y++) {
            System.out.println();
            for(int x = 0; x < triangle[y].length; x++) {
                
                // 맨 왼쪽
                if(x == 0) {
                    dp[y][x] = triangle[y][x] + dp[y-1][x];
                    //System.out.print(dp[y][x] + " ");
                }
                // 맨 오른쪽 이 둘은 바로 윗 숫자와 현 숫자를 그대로 더한다..
                else if(x == triangle[y].length - 1) {
                    dp[y][x] = triangle[y][x] + dp[y-1][x-1];
                    //System.out.print(dp[y][x] + " ");
                }
                else {
                    // 현 숫자 기준 좌 우를 비교해서 dp에 넣는다.
                    dp[y][x] = triangle[y][x] + Math.max(dp[y-1][x-1], dp[y-1][x]);
                    //System.out.print(dp[y][x] + " ");
                }
            }
        }
        
        for(int x = 0; x < triangle[n-1].length; x++) {
            answer = Math.max(answer, dp[n-1][x]);
        }
        return answer;
    }
}
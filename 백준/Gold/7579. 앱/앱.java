import java.util.*;
import java.lang.*;
import java.io.*;


class Main {
    static int N, M;
    static int[] memory;
    static int[] cost;
    static int[][] dp; // dp[i][j]의 정의 : i번째 앱까지 고려했을 때, 총 비용이 j일 때 확보할 수 있는 최대 메모리
    static int totalCost = 0;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memory = new int[N+1];
        cost = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        dp = new int[N+1][totalCost + 1];

        for(int i = 1; i<= N; i++) {
            for(int j = 0; j <= totalCost; j++) {
                // 앱을 끄지 않는 경우
                dp[i][j] = dp[i-1][j];

                // 앱을 끄는 경우
                if(j >= cost[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - cost[i]] + memory[i]);
                }
            }
        }

        for(int j = 0; j <= totalCost; j++) {
            if(dp[N][j] >= M) {
                System.out.println(j);
                break;
            }
        }
        

    }
}
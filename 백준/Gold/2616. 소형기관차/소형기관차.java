import java.util.*;
import java.io.*;
public class Main {

    static int[][] dp = new int[4][50001];
    static int[] arr;
    static int n, m;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];
        
        // 누적합으로 앞선 값을 누적해서 더한 배열 왜? => 빠르게 총합을 구하기 위해서
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
        }

        m = Integer.parseInt(br.readLine());

        // 소형기관차는 총 3개
        for(int i = 1; i <= 3; i++) {

            // 조합이라 생각해보자 3개를 고른다고 했을 때 시작하는 곳은 정해져 있다. 
            // 소형기관차 길이가 최대 2라면 누적합(index 0은 제외한다고 치고) 1 2가 첫시작 3 4가 2번째 기차의 시작 5 6 이 3번째 기차의 시작이다.
            for(int j = i*m; j <= n; j++) {
                // 현재 기관차의 순서는 i, 즉 i번째 기관차의 현재 칸(j)는 Math.max( 현재 i번째 기관차의 직전 j칸의 값 vs 이전기관차의 앞선 선택값 + 현재 누적합) 이다. 
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-m] + (arr[j] - arr[j-m]) );
            }
        }
        System.out.println(dp[3][n]);



    }
}
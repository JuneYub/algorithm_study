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


        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i-1];
        }

        m = Integer.parseInt(br.readLine());

        for(int i = 1; i <= 3; i++) {

            for(int j = i*m; j <= n; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-m] + (arr[j] - arr[j-m]) );
            }
        }
        System.out.println(dp[3][n]);



    }
}
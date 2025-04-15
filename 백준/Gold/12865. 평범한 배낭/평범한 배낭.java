import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    static int n, k;
    static int[][] product;
    static int[][] dp;
    static int ans;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        //            물건 {W, V}
        product = new int[101][2];
        dp = new int[101][100001];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            // 무게
            product[i][0] = Integer.parseInt(st.nextToken());
            // 가치
            product[i][1] = Integer.parseInt(st.nextToken());
            
        }

        // 물품의 INDEX
        for(int y = 1; y <= n; y++ ) {
            // 1부터 최대 넣을 수 있는 무게까지
            for(int x = 1; x <= k; x++) {

                // 분기가 나눠짐
                // 1. 현재 물건을 가방에 넣을 수 있는 경우
                if(x - product[y][0] >= 0) {

                    //                안넣을때 가치 vs 넣을때 (현재 무게-물건을 제외한 무게) 물건의 가치
                    dp[y][x] = Math.max(dp[y-1][x], dp[y-1][ x-product[y][0] ] + product[y][1] );
                }
                // 2. 현재 물건을 가방에 넣을 수 없는 경우 
                else {
                    dp[y][x] = dp[y-1][x];
                }
                
            }
        }
        System.out.println(dp[n][k]);


        
    }
}
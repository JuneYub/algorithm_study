import java.util.*;
import java.io.*;


public class Main
{

    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        for(int i = 0; i < n; i++) {

            int cnt = 0;

            // 왼쪽
            double leftDegree = (double) 0;
            for(int j = i-1; j  >= 0; j--) {

                // 각도계산
                double degree = ( (double) (arr[i] - arr[j]) / (i - j));
                if(j == i-1 || degree < leftDegree) {
                    leftDegree = degree;
                    cnt++;
                }

            }

            // 오른쪽
            double rightDegree = (double) 0;
            for(int k = i+1; k < n; k++) {

                // 각도계산
                double degree = ( (double) (arr[i] - arr[k]) / (i - k));
                if(k == i+1 || degree > rightDegree) {
                    rightDegree = degree;
                    cnt++;
                }

            }

            ans = Math.max(ans, cnt);

        }

        System.out.println(ans);

    }


}

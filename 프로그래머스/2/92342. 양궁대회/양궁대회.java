import java.util.*;
class Solution {

    public static int[] maxAnswer;
    public static int[] info;
    public static int n;
    public static int maxPoint = -1;

    public int[] solution(int sn, int[] sInfo) {

        n = sn;
        info = sInfo;
        int[] arr = new int[11];
        Arrays.fill(arr, 0);

        dfs(1, n, arr);

        if(maxPoint == -1) return new int[] {-1};
        return maxAnswer;
    }
    // depth는 1부터 시작, remain은 남은 화살 개수를 의미
    public static void dfs(int depth, int remain, int[] arr) {

        if(depth == 11) {
            // 종료조건

            // 어피치와 점수 비교
            int point = 10;
            int myPoint = 0;
            int aPoint = 0;
            if(remain > 0) arr[10] = remain;
            for(int i = 1; i <= 10; i++) {
                if(info[i-1] == 0 && arr[i-1] == 0) {
                    point--;
                    continue;
                }
                else if(info[i-1] < arr[i-1])
                    myPoint += point;
                else
                    aPoint += point;
                point--;
            }

            // 만약 어피치보다 점수가 높으며, 현재 점수중 가장 높은 점수라면 답을 갱신;
            if(myPoint > aPoint) {
                
                int diff = myPoint - aPoint;
                
                if(diff > maxPoint) {
                    maxAnswer = Arrays.copyOf(arr, arr.length);
                    maxPoint = diff;
                } else if (diff == maxPoint) {
                    // 낮은 점수를 더 많이 맞춘 경우 리턴
                    for(int j = 10; j >= 0; j--) {
                        if(arr[j] > maxAnswer[j]) {
                            maxAnswer = Arrays.copyOf(arr, arr.length);
                            maxPoint = diff;
                            break;
                        } else if(arr[j] < maxAnswer[j]) {
                            // 기존 maxAnswer가 tie-break에서 더 유리하므로 교체하지 않음
                            break;
                        }
                    }
                }

            }
            
            arr[10] = 0;
            return;
        }

        // 현재 깊이에서 과녁 점수를 먹을 것이다.
        if(info[depth-1] < remain) {
            // 어피치보다 한발 더 맞춤
            arr[depth-1] = info[depth-1] + 1;
            dfs(depth+1, remain-(info[depth-1]+1), arr);
            // 한발 더 맞춘건 초기화
            arr[depth-1] = 0;
        }
        // 먹지 않을 것이다.
        dfs(depth+1, remain, arr);
    }
}
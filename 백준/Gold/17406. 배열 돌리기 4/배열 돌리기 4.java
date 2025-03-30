import java.util.*;
import java.lang.*;
import java.io.*;

class Oper {
    int r;
    int c;
    int s;

    Oper(int r, int c, int s) {
        this.r = r;
        this.c = c;
        this.s = s;
    }
}

class Main {

    static int n, m, k;
    static int[][] map;
    static int[][] resultMap;
    static Oper[] opers;
    static Oper[] operSeq;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        resultMap = new int[n][m];
        
        opers = new Oper[k];
        operSeq = new Oper[k];
        visited = new boolean[k];
        
        for(int  y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int tmpR = Integer.parseInt(st.nextToken());
            int tmpC = Integer.parseInt(st.nextToken());
            int tmpS = Integer.parseInt(st.nextToken());            
            opers[i] = new Oper(tmpR, tmpC, tmpS);
        }

        permutation(0);
        System.out.println(min);
    }

    // 순열 함수
    public static void permutation(int depth) {
        if(depth == k) {
            // k개의 명령 순서를 모두 정한 경우

            // 원본 맵을 복사한다.
            for(int y = 0; y < n; y++) {
                for(int x = 0; x < m; x++) {
                    resultMap[y][x] = map[y][x];
                }
            }

            // 명령에 맞춰 지도를 돌린다.
            for(int i = 0; i < k; i++) {
                rotation(operSeq[i].r, operSeq[i].c, operSeq[i].s);
            }

            // 돌아간 지도의 결과를 확인한다.
            for(int y = 0; y < n; y++) {
                int tmp = 0;
                for(int x = 0; x < m; x++) {
                    tmp += resultMap[y][x];
                }

                min = Math.min(tmp, min);
            }

            // 결과를 도출했으니 return 해줘야한다.
            return;
        }

        else {
            for(int i = 0; i < k; i++) {
                if(!visited[i]) {
                    visited[i] = true;
                    operSeq[depth] = opers[i];
                    permutation(depth + 1);
                    visited[i] = false;
                }
            }
        }
    }

    // 돌리는 함수
    public static void rotation(int r, int c, int s) {
        // 가장 위쪽
        int top = r - s - 1;
        int bottom = r + s - 1;
        int left = c - s  - 1;
        int right = c + s - 1;

        while(top < bottom && left < right) {
            // 좌측 상단 값을 빼놓는다.
            int tmp = resultMap[top][left];

            // 맨 왼쪽 열에서 한칸씩 위로 옮김 - 고정된 값 left
            for(int y = top; y < bottom; y++) {
                resultMap[y][left] = resultMap[y+1][left]; 
            }
      
            // 아래쪽 행에서 왼쪽으로 한칸씩 옮김 - 고정된 값 bottom
            for(int x = left; x < right; x++) {
                resultMap[bottom][x] = resultMap[bottom][x+1];
            }
            
            // 맨 오른쪽 열에서 한칸씩 아래로 옮김 - 고정된 값 right
            for(int y = bottom; y > top; y--) {
                resultMap[y][right] = resultMap[y-1][right];
            }

            // 위쪽 행에서 오른쪽으로 한칸씩 옮김
            for(int x = right; x > left; x--) {
                resultMap[top][x] = resultMap[top][x-1];
            }

            // 1, 1 지점에 임시 보관한 값을 넣는다.
            resultMap[top][left+1] = tmp;

            // 안쪽으로 한칸 들어간다.
            top++;
            left++;
            bottom--;
            right--;
        }
    }
}
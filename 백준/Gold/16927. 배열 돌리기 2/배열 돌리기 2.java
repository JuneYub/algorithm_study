
import java.util.*;
import java.io.*;

public class Main {


static int n, m, r;
static int[][] map;
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for(int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        rotation();
    }

    public static void rotation() throws IOException {
        int leftTopX = 0;
        int leftTopY = 0;
        int rightBottomX = m-1;
        int rightBottomY = n-1;

        int rotCnt = Math.min(m, n) / 2;

        int[][] copyMap = new int[n][m];
        for(int y = 0; y < n; y++) {
            copyMap[y] = map[y].clone();
        }
        int rotationNum = 0;
        
        for(int i = 0; i < rotCnt; i++) {
        	rotationNum = r % (2*m + 2*n - 4);
            for(int j = 0; j < rotationNum; j++) {
            	
            	int leftTopDot = copyMap[leftTopY][leftTopX];
            	int leftBottomDot = copyMap[rightBottomY][leftTopX];
            	int rightBottomDot = copyMap[rightBottomY][rightBottomX];
            	int rightTopDot = copyMap[leftTopY][rightBottomX];
            	
                // 상단 왼쪽으로 땡기기
                for(int x = leftTopX+1; x <= rightBottomX; x++) {
                    copyMap[leftTopY][x-1] = copyMap[leftTopY][x];
                }
                
                // 좌측 아래로 땡기기
                for(int y = rightBottomY-1; y > leftTopY; y--) {
            		copyMap[y+1][leftTopX] = copyMap[y][leftTopX];
                }
                copyMap[leftTopY+1][leftTopX] = leftTopDot;
                           
                // 하단 오른쪽으로 땡기기
                for(int x = rightBottomX-1; x > leftTopX; x--) {
                    copyMap[rightBottomY][x+1] = copyMap[rightBottomY][x];
                }
                copyMap[rightBottomY][leftTopX+1] = leftBottomDot;

                // 우측 상단으로 옮기기
                for(int y = leftTopY+1; y < rightBottomY; y++) {
                    copyMap[y-1][rightBottomX] = copyMap[y][rightBottomX];
                }
                copyMap[rightBottomY-1][rightBottomX] = rightBottomDot;
            }
            leftTopX++;
            leftTopY++;
            rightBottomX--;
            rightBottomY--;
            m -= 2;
            n -= 2;
        }
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for(int y = 0; y < copyMap.length; y++) {
            for(int x = 0; x < copyMap[0].length; x++) {
                bw.write(copyMap[y][x] + " ");
            }
            bw.newLine();
        }
        bw.flush();

    }

}
import java.util.*;
import java.io.*;

class Position implements Comparable<Position>{
    int num, cnt;
    Position(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }

    public int compareTo(Position p) {
        if(this.cnt == p.cnt) return this.num - p.num;
        else return this.cnt - p.cnt;
    }
}
public class Main
{

    static int r,c,k;
    static int xLength = 3;
    static int yLength = 3;
    static int[][] arr = new int[100][100];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for(int y = 0; y < yLength; y++) {
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < xLength; x++) {
                arr[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while(time < 101) {
            if(arr[r-1][c-1] == k) {
                break;
            }

            time++;

            if(yLength >= xLength) {
                for(int i = 0; i < yLength; i++) {
                    calcuR(i);
                }
            } else {
                for(int i = 0; i < xLength; i++) {
                    calcuL(i);
                }
            }
        }
        if (time == 101) time = -1;
        System.out.println(time);
    }

    public static void calcuR(int key) {
        // 인덱스 key의 행을 정렬한다.

        // map을 통해 카운트한다.
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 100; i++) {
            int idx = arr[key][i];
            if(idx != 0) {
                map.put(idx, map.getOrDefault(idx, 0) + 1);
            }
        }
        // 우선순위 큐를 활용해 정렬한다.
        PriorityQueue<Position> pq = new PriorityQueue<>();
        for(Integer keyInteger : map.keySet()) {
            pq.add(new Position(keyInteger, map.get(keyInteger)));
        }

        // 맵에 새롭게 정렬한 값을 넣는다.
        int newArrIdx = 0;
        while(!pq.isEmpty()) {
            Position p = pq.poll();
            // 0 => 1 ... 98 => 99
            arr[key][newArrIdx++] = p.num;
            // 1 => 2 ... 99 => 100
            arr[key][newArrIdx++] = p.cnt;
            if(newArrIdx >= 100) break;
        }

        // xLength값을 갱신해준다.
        xLength = Math.max(xLength, newArrIdx);

        // 나머지 부분은 0으로 초기화한다.
        for(int i = newArrIdx; i < 100; i++) {
            arr[key][i] = 0;
        }
    }

    public static void calcuL(int key) {
        // 인덱스 key의 열을 정렬한다.

        // map을 통해 카운트한다.
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < 100; i++) {
            if(arr[i][key] != 0) {
                int num = arr[i][key];
                if(num != 0) {
                    map.put(num, map.getOrDefault(num,0) + 1);
                }
            }
        }

        // 우선순위 큐를 활용해 정렬한다.
        PriorityQueue<Position> pq = new PriorityQueue<>();
        for(Integer keyInteger : map.keySet()) {
            pq.add(new Position(keyInteger, map.get(keyInteger)));
        }

        // 맵에 새롭게 정렬한 값을 넣는다.
        int newArrIdx = 0;
        while(!pq.isEmpty()) {
            Position p = pq.poll();
            arr[newArrIdx++][key] = p.num;
            arr[newArrIdx++][key] = p.cnt;
            if(newArrIdx >= 100) break;
        }

        // yLength값을 갱신해준다.
        yLength = Math.max(yLength, newArrIdx);

        // 나머지 부분은 0으로 초기화한다.
        for(int i = newArrIdx; i < 100; i++) {
            arr[i][key] = 0;
        }

    }


}
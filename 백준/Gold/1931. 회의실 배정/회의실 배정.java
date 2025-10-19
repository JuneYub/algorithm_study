import java.util.*;
import java.lang.*;
import java.io.*;

class Meet implements Comparable<Meet> {
    int start, end;

    public Meet(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Meet m) {
        if(this.end == m.end)
            return this.start - m.start;
        return this.end - m.end;
    }
}

class Main {
    public static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        PriorityQueue<Meet> pq = new PriorityQueue<Meet>();
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.add(new Meet(start, end));
        }

        int cnt = 0;
        int endTime = 0;
        while(!pq.isEmpty()) {
            Meet m = pq.poll();
            if(m.start >= endTime) {
                cnt++;
                endTime = m.end;
            }
        }
        System.out.println(cnt);
    }
}
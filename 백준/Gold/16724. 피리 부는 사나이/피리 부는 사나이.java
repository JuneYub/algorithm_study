import java.util.*;
import java.lang.*;
import java.io.*;

class Main {

    static int n, m;
    static char[][] map;
    static int[][] visited;
    static int ans = 0;
    static int curId;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        visited = new int[n][m];
        
        for(int y = 0; y < n; y++) {
            String str = br.readLine();
            for(int x = 0; x < m; x++) {
                map[y][x] = str.charAt(x);
            }
        }

        for(int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if(visited[y][x] != 0) continue;
                curId = ans + 1;
                find(x, y, ans + 1);
            }
        }

        System.out.print(ans);
            
    }

    public static boolean find(int x, int y, int id) {

        if(visited[y][x] == id) {
            ans++;
            return true;
        }
        
        if(visited[y][x] != 0) {
            curId = visited[y][x];
            return false;
        }
        
        visited[y][x] = id;
        int newX = x;
        int newY = y;
        switch(map[y][x]) {

            case 'D' :
                newY = y + 1;
                break;
            case 'U' :
                newY = y -1;
                break;
            case 'L' :
                newX = x -1;
                break;
            case 'R' :
                newX = x+1;
                break;
        }

        if(!find(newX, newY, id)) {
            visited[y][x] = curId;
            return false;
        }
        else {
            return true;
        }
        
    } 

}
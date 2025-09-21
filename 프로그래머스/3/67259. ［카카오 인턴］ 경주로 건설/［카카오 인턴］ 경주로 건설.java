import java.util.*;

class Solution {
    static class Position implements Comparable<Position> {
        int y, x;
        int dir;
        int cnt;
        int cornerCnt;
        int price = 0;
        
        public Position(int y, int x, int dir, int cnt, int cornerCnt) {
            this.y = y;
            this.x = x;
            this.dir = dir;
            this.cnt = cnt;
            this.cornerCnt = cornerCnt;
            this.price = (this.cnt * 100 + this.cornerCnt * 500);
        }
        
        public int compareTo(Position p) {

            return this.price - p.price;
        }
    }
    
    static int[] dirY = {-1, 0, 1, 0};
    static int[] dirX = {0, 1, 0, -1};
    static PriorityQueue<Position> q = new PriorityQueue<>();
    static int[][][] visited;
    static int N;
    static int 직진 = 100;
    static int 코너 = 500;
    public int solution(int[][] board) {
        N = board.length;
        visited = new int[4][N][N];
            
        return bfs(board);
    }
    
    public static int bfs(int[][] board) {
        int answer = Integer.MAX_VALUE;
        
        // 초기 세팅 4방향
        for(int i = 0; i < 4; i++) {
            // Position(int y, int x, int dir, int cnt, int cornerCnt) {
            q.add(new Position(0, 0, i, 0, 0));
        }
        
        while(!q.isEmpty()) {
            
            Position cur = q.poll();
            if(cur.y == N-1 && cur.x == N-1) {
                answer = Math.min(answer, cur.price);
                continue;
            }
            
            if(cur.price > answer) continue;
            
            //System.out.println(cur.y + " " + cur.x + " " + cur.dir + " " + cur.price);
            for(int i = 0; i < 4; i++) {
                
                int newY = cur.y + dirY[i];
                int newX = cur.x + dirX[i];
                if(!inRange(newY,newX)) continue;
                if(board[newY][newX] == 1) continue;
                
                // 직진인지
                Position p;
                if(cur.dir == i)
                    p = new Position(newY, newX, i, cur.cnt+1, cur.cornerCnt);
                // 코너인지
                else
                    p = new Position(newY, newX, i, cur.cnt+1, cur.cornerCnt+1);

                if(visited[i][newY][newX] == 0 || visited[i][newY][newX] >= p.price) {

                    visited[i][newY][newX] = p.price;
                    q.add(p);
                }
            }
        }
        return answer;
    }
    

    
    public static boolean inRange(int y, int x) {
        if (y < 0 || y >= N || x < 0 || x >= N) return false;
        return true;
    }
}
import java.util.*;
import java.io.*;

class Position {
    int x, y;
    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public boolean equals(Object o) {
        Position p = (Position) o;
        if(p.x == this.x && p.y == this.y) return true;
        return false;
    }
    
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
class Solution {
    // 좌우가 상하보다 먼저이다. 상 하 좌 우
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {-1, 1, 0, 0}; 
    public static List<HashMap<Position, Integer>> list = new ArrayList<>();
    public int solution(int[][] points, int[][] routes) {
        
        setList(points, routes);
        
        int ans = 0;
        for(int i = 0; i < list.size(); i++) {

            HashMap<Position, Integer> map = list.get(i);
            for(Integer value : map.values()) {
                
                if(value > 1) {
                    ans++;
                }
            }
            
        }
        return ans;
        
    }
    
    public static void setList(int[][] points, int[][] routes) {
        
        for(int i = 0; i < routes.length; i++) {
            int count = 0;
            int curRoute = 1;
            
            while(curRoute < routes[0].length) {
                int startPoint = routes[i][curRoute-1]-1;
                int endPoint = routes[i][curRoute]-1;
                
                int posX = points[startPoint][1] - 1;
                int posY = points[startPoint][0] - 1;

                int endX = points[endPoint][1] - 1;
                int endY = points[endPoint][0] - 1;
                
                if(count == 0) {
                    if(count >= list.size()) {
                        list.add(new HashMap<>());
                    }
                    
                    // 현재 위치 추가
                    HashMap<Position, Integer> map = list.get(count);
                    Position key = new Position(posX, posY);
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }

                //System.out.println();
                while(true) {

                    // 도착
                    if(posX == endX && posY == endY) {
                        break;
                    }

                    // 도착하지 않았다면 4방향 탐색 후 해당 지점으로 이동
                    int minDistance = Integer.MAX_VALUE;
                    int minIdx = 0;
                    for(int j = 0; j < 4; j++) {
                        int newX = posX + dx[j];
                        int newY = posY + dy[j];

                        int newMinDistance = Math.abs(newX - endX) + Math.abs(newY -endY);
                        if(newMinDistance < minDistance) {
                            minDistance = newMinDistance;
                            minIdx = j;
                        }
                    }

                    // 이동
                    posX = posX + dx[minIdx];
                    posY = posY + dy[minIdx];
                    count++;
                    
                    // hashset이 없으면 추가
                    if (count >= list.size()) {
                        list.add(new HashMap<>());
                    } else if (list.get(count) == null) {
                        list.set(count, new HashMap<>());
                    }

                    // 현재 위치 추가
                    HashMap<Position, Integer> map = list.get(count);
                    Position key = new Position(posX, posY);
                    map.put(key, map.getOrDefault(key, 0) + 1);
                    //System.out.println("count " + count + " " + map.get(new Position(posX, posY)));

                }
                curRoute++;
            }

        }
        
    }

}
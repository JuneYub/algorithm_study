import java.util.*;

class Position implements Comparable<Position> {
    int x, y;
    
    public Position (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int compareTo(Position p) {
        return this.y - p.y;
    }
}

class Solution {
    public int solution(int[][] targets) {
        
        ArrayList<Position> list = new ArrayList<>();
        for(int i = 0; i < targets.length; i++) {
            list.add(new Position(targets[i][0], targets[i][1]));
        }
        
        Collections.sort(list);
        
        int answer = 0;
        int lastIntercept = -1;
        
        for(int i = 0; i < list.size(); i++) {
            if(lastIntercept == -1 || list.get(i).x >= lastIntercept) {
                lastIntercept = list.get(i).y;
                answer++;
            }
        }
        
        return answer;
        
    }
}
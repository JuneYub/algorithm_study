import java.util.*;
import java.lang.*;
import java.io.*;


class Tower {
    int height;
    int index;

    Tower(int height, int index) {
        this.height = height;
        this.index = index;
    }
}

class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        Stack<Tower> stack = new Stack<>();
        
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < n; i++) {

            int nowTowerHeight = Integer.parseInt(st.nextToken());
            
            if(stack.isEmpty()) {
                sb.append("0 ");
                stack.push(new Tower(nowTowerHeight, i+1));
            }
            else {
                while(true) {

                    

                    if(stack.isEmpty()) {
                        sb.append("0 ");
                        stack.push(new Tower(nowTowerHeight, i+1));
                        break;
                    }
                    else {
                        
                      Tower preTower = stack.peek();
                      // System.out.println(preTower.height + " vs " + nowTowerHeight);
                      if(preTower.height > nowTowerHeight) {
                          sb.append(preTower.index + " ");
                          stack.push(new Tower(nowTowerHeight, i+1));
                          break;
                      } else {
                          stack.pop();
                      }
                    }
                }
            }
        } // ============== end of for
        
        System.out.println(sb.toString());
    }
}
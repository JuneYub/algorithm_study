import java.util.*;


class Solution {
    public int[] solution(String[] operations) {
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        for(String operation : operations) {
            StringTokenizer st = new StringTokenizer(operation);
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());
            
            if(command.equals("I")) {
                minHeap.add(value);
                maxHeap.add(value);
            } else if(command.equals("D")) {
                if(minHeap.isEmpty()) continue;
                if(value == 1) {
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                } else if(value == -1) {
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                }
            }
        }
        
        if(minHeap.isEmpty()) {
            return new int[]{0,0};
        } else {
            return new int[]{maxHeap.peek(), minHeap.peek()};
        }
        
    }
}
import java.util.*;

class Solution {
    
    private int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public int solution(String[][] book_time) {

        Arrays.sort(book_time, (a, b) -> toMinutes(a[0]) - toMinutes(b[0]));

        PriorityQueue<Integer> roomHeap = new PriorityQueue<>();

        for (String[] time : book_time) {
            int start = toMinutes(time[0]);
            int end = toMinutes(time[1]) + 10; 

            if (!roomHeap.isEmpty() && roomHeap.peek() <= start) {
                roomHeap.poll(); 
            }

            roomHeap.offer(end); 
        }

        return roomHeap.size();
    }
}
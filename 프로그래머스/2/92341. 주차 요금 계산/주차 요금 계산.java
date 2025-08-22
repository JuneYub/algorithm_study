import java.util.*;

class Car implements Comparable<Car> {
    int num, fee;
    
    public Car (int num, int fee) {
        this.num = num;
        this.fee = fee;
    }
    
    public int compareTo(Car c) {
        return Integer.compare(this.num, c.num);
    }
    
}

class Solution {
    
    public static Map<String, String> map = new HashMap<>();
    public static Map<String, Integer> mapFee = new HashMap<>();
    public static int defaultTime, defaultFee, unitTime, unitFee;
    public int[] solution(int[] fees, String[] records) {
        
        defaultTime = fees[0];
        defaultFee = fees[1];
        unitTime = fees[2];
        unitFee = fees[3];
        
        StringTokenizer st;
        for(String rec : records) {
            String[] strArr = rec.split(" ");
            // 입차된 기록이 없는 경우
            if(map.get(strArr[1]) == null) {
                map.put(strArr[1], strArr[0].replace(":", ""));    
            }
            else {
                String inTime = map.get(strArr[1]);
                String outTime = strArr[0].replace(":", "");
                int parkingTime = caculate(inTime, outTime);
                mapFee.put(strArr[1], (mapFee.getOrDefault(strArr[1], 0) + parkingTime));
                map.remove(strArr[1]);
            }
        }
        
        // 23:59도 계산
        for(String key : map.keySet()) {
            String inTime = map.get(key);
            String outTime = "2359";
            int parkingTime = caculate(inTime, outTime);
            mapFee.put(key, (mapFee.getOrDefault(key, 0) + parkingTime));
        }
        
        //System.out.println("==================");   
        List<Car> list = new ArrayList<>();
        
        for(String key : mapFee.keySet()) {
            list.add(new Car(Integer.parseInt(key), calcuFee(mapFee.get(key)) ));
        }
        Collections.sort(list);
        
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).fee;
        }             
                     
        return result;
    }
    
    public static int caculate(String inTime, String outTime) {
        int inH = Integer.parseInt(inTime.substring(0, 2));
        int inM = Integer.parseInt(inTime.substring(2, 4));
        
        int outH = Integer.parseInt(outTime.substring(0, 2));
        int outM = Integer.parseInt(outTime.substring(2, 4));
        int totalTime = (outH*60+outM) - (inH*60+inM);
        return totalTime;
    }
    
    public static int calcuFee(int totalTime) {
        int totalFee = 0;
        
        //System.out.println("totalTime" + " "+ totalTime);  
        if(totalTime >= defaultTime) {
            //154/10 
            int remainTime = (totalTime - defaultTime);
            int remainFee = 0;
            if(remainTime % unitTime != 0) {
                remainFee = (remainTime/unitTime + 1) * unitFee;
            } else {
                remainFee = (remainTime/unitTime) * unitFee;
            }
            
            totalFee = defaultFee + remainFee;
        } else {
            totalFee = defaultFee;
        }
        return totalFee;
    }
}
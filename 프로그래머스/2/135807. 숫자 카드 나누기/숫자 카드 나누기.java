import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = getArrayGcd(arrayA);
        int gcdB = getArrayGcd(arrayB);
        
        boolean 비를나눌수있나 = false;
        for(int i = 0; i < arrayB.length; i++) {
            if(arrayB[i] % gcdA == 0) {
                비를나눌수있나 = true;
                break;
            }
        }
        
        boolean 에이를나눌수있나 = false;
        for(int i = 0; i < arrayA.length; i++) {
            if(arrayA[i] % gcdB == 0) {
                에이를나눌수있나 = true;
                break;
            }
        }
        
        if(에이를나눌수있나 && 비를나눌수있나) return 0;
        else if(에이를나눌수있나 && !비를나눌수있나) return gcdA;
        else if(!에이를나눌수있나 && 비를나눌수있나) return gcdB;
        else return Math.max(gcdA, gcdB);
        
    }
    
    private int getGcd(int a, int b) {
        while(b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    
    private int getArrayGcd(int[] array) {
        
        int result = array[0];
        for(int i = 1; i < array.length; i++) {
            result = getGcd(result, array[i]);
            if(result == 1) {
                return 1;
            }
        }
        return result;
        
    }
}
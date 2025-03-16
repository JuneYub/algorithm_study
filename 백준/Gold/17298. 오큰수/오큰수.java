import java.util.*;
import java.lang.*;
import java.io.*;

class Ocun {
    int value;
    int index;

    public Ocun(int value, int index) {
        this.value = value;
        this.index = index;
    }
}

class Main {
    static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] result = new int[n];
        Arrays.fill(result, -1);

        StringTokenizer st = new StringTokenizer(br.readLine());
        Stack<Ocun> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());

            if(stack.isEmpty()) {
                stack.push(new Ocun(value, i));
                continue;
            } else {

                while(true) {
                    if(stack.isEmpty()) {
                        stack.push(new Ocun(value, i));
                        break;
                    } else {
                        Ocun preOcun = stack.peek();
                        if(preOcun.value < value) {
                            result[preOcun.index] = value;
                            stack.pop();
                        } else {
                            stack.push(new Ocun(value, i));
                            break;
                        }
                    }
                }
            }
        }

        for(int num : result) {
            sb.append(num + " ");
        }
        System.out.println(sb.toString());
        
    }
}
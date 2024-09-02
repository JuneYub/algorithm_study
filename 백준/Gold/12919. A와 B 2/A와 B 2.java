import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();
        
        System.out.println(canConvert(S, T) ? 1 : 0);
    }
    
    public static boolean canConvert(String S, String T) {
        if (S.length() == T.length()) {
            return S.equals(T);
        }
        
        if (T.charAt(T.length() - 1) == 'A') {
            if (canConvert(S, T.substring(0, T.length() - 1))) {
                return true;
            }
        }
        
        if (T.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(T.substring(1));
            if (canConvert(S, sb.reverse().toString())) {
                return true;
            }
        }
        
        return false;
    }
}
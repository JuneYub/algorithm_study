import java.util.*;
import java.lang.*;
import java.io.*;


class Main {

    static int n, m;
    static List<Integer> list = new ArrayList<>();
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {

        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        dfs(new ArrayList<Integer>(), 0);

        System.out.print(sb.toString());
        
    }

    public static void dfs(List<Integer> answerList, int depth) {
        if(answerList.size() == m) {
            for(int i = 0; i < m; i++) {
                sb.append(answerList.get(i) + " ");
            }
            sb.append("\n");
        }

        int before = -1;
        for(int i = 0; i < list.size(); i++) {
            if(!visited[i] && before != list.get(i)) {
                visited[i] = true;
                answerList.add(list.get(i));
                before = list.get(i);
                dfs(answerList, depth + 1);
                visited[i] = false;
                answerList.remove(answerList.size() - 1);
            }

            
        }

        
        
    }


}
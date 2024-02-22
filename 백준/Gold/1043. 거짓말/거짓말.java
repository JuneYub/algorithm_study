
import java.util.*;
import java.io.*;

public class Main {
	
	static int n, m;
	static int trueCnt;
	static List<Integer> truePeople = new ArrayList<>();
	static List<List<Integer>> party = new ArrayList<>();
	static List<Integer> partyPeople;
	static int parents[];
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		
		for(int i = 1; i < n+1; i++) {
			parents[i] = i;
		}
		
		st = new StringTokenizer(br.readLine());
		trueCnt = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < trueCnt; i++) {
			truePeople.add(Integer.parseInt(st.nextToken()));
		}
		Collections.sort(truePeople);

		for(int i = 0; i < truePeople.size() -1; i++) {
			union(truePeople.get(i), truePeople.get(i+1));
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int partyTrueCnt = Integer.parseInt(st.nextToken());
			partyPeople = new ArrayList<>();
			
			for(int j = 0; j < partyTrueCnt; j++) {
				partyPeople.add(Integer.parseInt(st.nextToken()));
			}
			// 파티 사람들을 정렬한다.
			Collections.sort(partyPeople);
			
			// 유니온
			for(int j = 0; j < partyTrueCnt-1; j++) {
				union(partyPeople.get(j), partyPeople.get(j+1));
			}
			
			// 파티 리스트에 정보 저장
			party.add(partyPeople);
		}
		
		int cnt = 0;
		
		if(truePeople.size() != 0) {
			int 진추사 = truePeople.get(0);
			
			for(int i = 0; i < m; i++) {
				int 파티대표 = party.get(i).get(0);
				if(find(진추사) == find(파티대표)) {
					cnt++;
				}
			}
		}
		
		System.out.println(m-cnt);
		
	}
	
	public static void union(int a, int b) {
		int pA = find(a);
		int pB = find(b);
		
		if(pA != pB) {
			parents[pB] = pA;
		}
	}
	
	public static int find(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = find(parents[a]);
		
	}
	
}







import java.util.*;
import java.io.*;


public class Main
{

	static int n, m, t;
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		t = Integer.parseInt(br.readLine());
		n = Integer.parseInt(br.readLine());
		
		int[] a = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		
		m = Integer.parseInt(br.readLine());
		
		int[] b = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			b[i] = Integer.parseInt(st.nextToken());
		}
		
		// 부분배열을 list로 더해준다.
		List<Long> sumA = getSubArraysSums(a);
		List<Long> sumB = getSubArraysSums(b);
		
		// a의 부분배열의 합의 숫자가 각각 몇개 있는지 기록해준다.
		Map<Long, Long> countA = new HashMap<>();
		for(Long l : sumA) {
			countA.put(l, countA.getOrDefault(l, (long) 0) + 1);
		}
		
		// sumB를 돌면서 각각의 숫자를 t에서 뺐을 때 countA에 몇개 있는지 보고 결과에 더해준다.
		long result = 0;
		for(Long l : sumB) {
			if(countA.containsKey(t - l)) {
				result += countA.get(t-l);
			}
		}
		System.out.println(result);
		
		
		
		
	}
	private static List<Long> getSubArraysSums(int[] arr) {
	
		int n = arr.length;
		List<Long> subArrays = new ArrayList<>();
		
		for(int i = 0; i < n; i++) {
			long sum = 0;
			for(int j = i; j < n; j++) {
				sum += arr[j];
				subArrays.add(sum);
			}
		}
		
		
		return subArrays;
	}
	
} 	

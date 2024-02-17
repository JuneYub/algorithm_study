
import java.util.*;
import java.io.*;

class Node {
	String name;
	String left;
	String right;
	
	Node(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(((Node) obj).name != this.name) {
			return false;
		}
		return true;
		
	}
	
	
}
public class Main {

	static Map<String, Node> map;
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		map = new HashMap<>();
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			Node node = new Node(name);
			
			String left = st.nextToken();
			String right = st.nextToken();
			
			if(!left.equals(".")) {
				node.left = left;
			}
			
			if(!right.equals(".")) {
				node.right = right;
			}
			
			map.put(name, node);
		}
		
		// 전위순회
		dfs(map.get("A"));
		System.out.println();
		// 중위순회
		dfs2(map.get("A"));
		System.out.println();
		// 후위순회
		dfs3(map.get("A"));
	}
	
	public static void dfs(Node node) {
		System.out.print(node.name);
		if(node.left != null) {
			dfs(map.get(node.left));
		}
		if(node.right != null) {
			dfs(map.get(node.right));
		}
	}
	
	public static void dfs2(Node node) {
		
		if(node.left != null) {
			dfs2(map.get(node.left));
		}
		System.out.print(node.name);
		if(node.right != null) {
			dfs2(map.get(node.right));
		}
	}
	
	public static void dfs3(Node node) {
		
		if(node.left != null) {
			dfs3(map.get(node.left));
		}
		if(node.right != null) {
			dfs3(map.get(node.right));
		}
		System.out.print(node.name);
	}
}
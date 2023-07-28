package Week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976_여행_가자 {
	static int[] root;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int plan = Integer.parseInt(br.readLine());
		root = new int[N+1];
		
		for(int i=1; i<root.length; ++i) {
			root[i] = i;
		}
		
		for(int i=1; i<=N; ++i) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=N; ++j) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					union(i, j);
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int res = find(Integer.parseInt(st.nextToken()));
		for(int i=1; i<plan; ++i) {
			if(res != find(Integer.parseInt(st.nextToken()))) {
				System.out.println("NO");
				return;
			}
		}
		System.out.println("YES");
		
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		
		root[y] = x;
	}

	private static int find(int x) {
		if(root[x] == x) return x;
		else return find(root[x]);
	}
}

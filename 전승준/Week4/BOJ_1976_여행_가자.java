package Week4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976_여행_가자 {
	static int[] City;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		City = new int[n+1];
		
		for(int i=1; i<n+1; i++) {
			City[i] = i;
		}
		
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				if(Integer.parseInt(st.nextToken()) == 1) {
					merge(i, j);					
				}
			}
		}
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		
		for(int i=1; i<m; i++) {
			int now = Integer.parseInt(st.nextToken());
			
			if(find(start) != find(now)) {
				System.out.println("NO");
				return;
			}
		}
		
		System.out.println("YES");

	}

	private static int find(int x) {
		if(City[x] == x) 
			return x;
		return City[x] = find(City[x]);
	}
	private static void merge(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return;
		
		City[y] = x;
	}
}

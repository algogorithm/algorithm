package Week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2668_숫자고르기 {
	static boolean[] V;
	static int max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] table = new int[N+1][2];
		V = new boolean[N+1];
		
		for(int i=1; i<table.length; ++i) {
			int input = Integer.parseInt(br.readLine());
			
			if(input == i) {
				V[i] = true;
			}
			else {
				table[i][0] = i;
				table[i][1] = input;				
			}
		}
		
		for(int i=1; i<table.length; ++i) {
			searchDFS(i, table[i][1], 0, table, new boolean[N+1]);
		}
		
		int cnt = 0;
		for(int i=1; i<V.length; ++i) {
			if(V[i]) {
				sb.append("\n"+i);
				++cnt;
			}
		}
		sb.insert(0, cnt);
		System.out.print(sb);
	}

	
	private static void searchDFS(int start, int now, int cnt, int[][] table, boolean[] visited) {
		if(start == now) {
			for(int i=1; i<visited.length; ++i) {
				if(visited[i]) {
					V[i] = true;
				}
			}
			return;
		}
		
		int next = table[now][1];
		
		if(!visited[next]) {
			visited[next] = true;
			searchDFS(start, next, cnt+1, table, visited);
			visited[next] = false;
		}
	}

}

package BAEKJOON;

import java.io.*;
import java.util.*;

public class silver1_1325_효율적인해킹 {
	
	static ArrayList<Integer>[] adj;
//	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] visit;
	static int[] result; 
	static int count = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		result = new int[N+1];
		adj = new ArrayList[N+1];		
		for (int i = 0; i <= N; i++) {
			adj[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adj[b].add(a);
		}
		
		for (int i = 1; i <= N; i++) {
			visit = new boolean[N+1];
			bfs(i);
		}
		
		for (int i = 1; i <= N; i++) {
			if(result[i] == count) {
				sb.append(i + " ");
			}
		}
		
		System.out.println(sb);
	}

	private static void bfs(int i) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.offer(i);
		visit[i] = true;
		
		int cnt = 1;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int j = 0; j < adj[cur].size(); j++) {
				int next = adj[cur].get(j);
				if(!visit[next]) {
					visit[next] = true;
					queue.offer(next);
					cnt++;
				}
			}
		}
		
		result[i] = cnt;
		count = Math.max(count, cnt);
	}

}

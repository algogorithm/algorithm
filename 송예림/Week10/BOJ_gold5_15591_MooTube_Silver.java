package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold5_15591_MooTube_Silver {

	static int N;
	static ArrayList<Video>[] adj;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		adj = new ArrayList[N+1];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken());
			adj[p].add(new Video(q, r));
			adj[q].add(new Video(p, r));
		}
		
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			sb.append(bfs(k, v)+"\n");
		}
		
		System.out.println(sb);
	}

	private static int bfs(int k, int v) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visit = new boolean[N+1];
		int result = 0;
		queue.offer(v);
		visit[v] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for (int i = 0; i < adj[cur].size(); i++) {
				Video c = adj[cur].get(i);
				if(!visit[c.end] && c.usado >= k) {
//					System.out.println(cur + " " + c.end + " " + c.usado);
					result++;
					visit[c.end] = true;
					queue.offer(c.end);
				}
			}
		}
		
		return result;
	}

	static class Video {
		int end, usado;
		public Video(int end, int usado) {
			this.end = end;
			this.usado = usado;
		}
	}
}

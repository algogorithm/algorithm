package BAEKJOON;

import java.io.*;
import java.util.*;

public class gold4_1976_여행가자 {
	static boolean[][] map;
	static int[] visit;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		map = new boolean[N+1][N+1];
		visit = new int[M];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				if(st.nextToken().equals("1")) {
					map[i][j] = true;
					map[j][i] = true;
				}
			}
		}
	
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			visit[i] = Integer.parseInt(st.nextToken());
		}
		
		boolean result = true;
		for (int i = 0; i < visit.length-1; i++) {
			if(!result) break;
			result = bfs(visit[i], visit[i+1]);
		}
		
		System.out.println(result ? "YES" : "NO");
	}

	private static boolean bfs(int start, int end) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] v = new boolean[map.length];
		queue.offer(start);
		v[start] = true;
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			if(cur == end) {
				return true;
			}
			
			for (int i = 1; i < map.length; i++) {
				if(map[cur][i] && !v[i]) {
					v[i] = true;
					queue.offer(i);
				}
			}
		}
		
		return false;
	}

}

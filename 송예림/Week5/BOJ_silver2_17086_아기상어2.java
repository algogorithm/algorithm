package BAEKJOON;

import java.io.*;
import java.util.*;

public class silver2_17086_아기상어2 {
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static boolean[][] map;
	static int N, M, result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N][M];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken()) == 1 ? true : false;
			}
		}
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(!map[r][c]) {
					bfs(new Node(r, c, 0));
				}
			}
		}
		
		System.out.println(result);
	}

	private static void bfs(Node node) {
		Queue<Node> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];
		queue.offer(node);
		visit[node.r][node.c] = true;
		
		while(!queue.isEmpty()) {
			Node cur = queue.poll();
			
			for (int d = 0; d < dr.length; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if(nr>=0 && nr<N && nc>=0 && nc<M && !visit[nr][nc]) {
					if(map[nr][nc]) {
						result = Math.max(result, cur.cnt+1);
						return;
					}
					visit[nr][nc] = true;
					queue.offer(new Node(nr, nc, cur.cnt+1));
				}
			}
		}
		
	}

	static class Node {
		int r, c, cnt;
		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}

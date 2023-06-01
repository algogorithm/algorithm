package BAEKJOON;

import java.io.*;
import java.util.*;

public class silver1_16174_점프왕쩰리_Large {
	static int[] dr = {0, 1};
	static int[] dc = {1, 0};
	static int N;
	static int[][] map;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(0, 0) ? "HaruHaru" : "Hing");
	}

	private static boolean bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();
		boolean[][] visit = new boolean[N][N];
		queue.offer(new int[] {r, c});
		visit[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			if(cur[0] == N-1 && cur[1] == N-1) {
				return true;
			}
			
			for (int d = 0; d < dr.length; d++) {
				int nr = cur[0] + dr[d] * map[cur[0]][cur[1]];
				int nc = cur[1] + dc[d] * map[cur[0]][cur[1]];
				if(nr >= 0 && nr < N && nc >= 0 && nc < N && !visit[nr][nc]) {
					visit[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
		
		return false;
	}
}
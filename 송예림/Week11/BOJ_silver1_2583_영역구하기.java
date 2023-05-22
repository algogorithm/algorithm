package BAEKJOON;

import java.io.*;
import java.util.*;

public class silver1_2583_영역구하기 {
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static int N, M, result = 0;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		map = new boolean[M][N];
		
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int leftC = Integer.parseInt(st.nextToken());
			int leftR = Integer.parseInt(st.nextToken());
			int rightC = Integer.parseInt(st.nextToken());
			int rightR = Integer.parseInt(st.nextToken());
			
			for (int r = leftR; r < rightR; r++) {
				for (int c = leftC; c < rightC; c++) {
					map[r][c] = true;
				}
			}
		}
		
		PriorityQueue<Integer> list = new PriorityQueue<Integer>();
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				if(!map[r][c]) {
					 list.offer(bfs(r, c));
					 result++;
				}
			}
		}
		
		System.out.println(result);
		while(!list.isEmpty()) {
			System.out.print(list.poll() + " ");
		}
	}

	private static Integer bfs(int r, int c) {
		Queue<int[]> queue = new LinkedList<int[]>();
		int area = 1;
		queue.offer(new int[] {r, c});
		map[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			for (int d = 0; d < dr.length; d++) {
				int nr = cur[0] + dr[d];
				int nc = cur[1] + dc[d];
				if(nr >= 0 && nr < M && nc >= 0 && nc < N && !map[nr][nc]) {
					area++;
					map[nr][nc] = true;
					queue.offer(new int[] {nr, nc});
				}
			}
		}
		
		return area;
	}

}

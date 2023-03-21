package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;

public class gold4_14500_테트로미노 {
	// 우하좌상
	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};
	static int N, M, result = 0;
	static int[][] map;
	static boolean[][] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visit = new boolean[N][M];
		
		for (int r = 0; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[r].length; c++) {
				visit[r][c] = true;
				dfs(r, c, 1, map[r][c]); // 위치, 칸 개수, 합
				visit[r][c] = false;
			}
		}
		
		System.out.println(result);
	}

	private static void dfs(int r, int c, int cnt, int sum) {
		// 끝
		if(cnt == 4) {
			result = Math.max(result, sum);
			return;
		}
		
		for (int d = 0; d < dc.length; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(nr>=0 && nr<N && nc>=0 && nc<M && !visit[nr][nc]) {
				// 2번째일때 원점에서 한번 더 진행
				if(cnt == 2) {
					visit[nr][nc] = true;
					dfs(r, c, cnt+1, sum+map[nr][nc]);
					visit[nr][nc] = false;
				}
				visit[nr][nc] = true;
				dfs(nr, nc, cnt+1, sum+map[nr][nc]);
				visit[nr][nc] = false;
			}
		}
	}

}

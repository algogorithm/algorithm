package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gold4_1405_미친로봇 {

	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static double[] per_dir = new double[4];
	static boolean[][] visit = new boolean[30][30];
	static int N;
	static double result = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < 4; i++) {
			per_dir[i] = Integer.parseInt(st.nextToken()) * 0.01;
		}
		
		visit[15][15] = true;
		dfs(15, 15, 0, 1);
		System.out.println(result);
	}

	private static void dfs(int r, int c, int k, double total) {
		if(k == N) {
			result += total;
			return;
		}
		
		for (int i = 0; i < per_dir.length; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if(!visit[nr][nc]) {
				visit[nr][nc] = true;
				dfs(nr, nc, k+1, total*per_dir[i]);
				visit[nr][nc] = false;
			}
		}
	}

}

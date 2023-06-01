package BAEKJOON;

import java.io.*;
import java.util.StringTokenizer;

public class gold5_20002_사과나무 {
	static int[][] map, sum;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		sum = new int[N+1][N+1];
		
		for (int r = 1; r < map.length; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c < map[r].length; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				sum[i][j] = map[i][j] + sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
			}
		}
		
		int result = sum[1][1];
		
		for (int k = 0; k < N; k++) {
			for (int r = 1; r < N-k+1; r++) {
				for (int c = 1; c < N-k+1; c++) {
					int s = sum[r+k][c+k] - sum[r-1][c+k] - sum[r+k][c-1] + sum[r-1][c-1];
					result = Math.max(result, s);
				}
			}
		}
		
		System.out.println(result);
	}

}

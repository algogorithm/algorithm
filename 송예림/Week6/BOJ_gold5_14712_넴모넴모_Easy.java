package BAEKJOON;

import java.io.*;
import java.util.StringTokenizer;

public class silver1_14712_넴모넴모_Easy {
	static int N , M, result = 0;
	static boolean[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new boolean[N+1][M+1];
		
		dfs(0);
		System.out.println(result);
	}

	private static void dfs(int cnt) {
		if(cnt == N * M) {
			result++;
			return;
		}
		
		// cnt가 하나 올라갈 때 마다 오른쪽으로 한칸씩 옮겨짐
		int r = cnt / M + 1;
		int c = cnt % M + 1;
		
		// 현재 자리에도 놓으면 "넴모"
		if(map[r-1][c-1] && map[r-1][c] && map[r][c-1]) {
			dfs(cnt+1);
		} else {
			// 안놓고 넘어가기
			dfs(cnt+1);
			// 놓고 넘어가기
			map[r][c] = true;
			dfs(cnt+1);
			map[r][c] = false;
		}
	}
}
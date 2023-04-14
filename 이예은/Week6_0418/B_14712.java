package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14712 {
	static int N, M, ans, map[][];
	
	static void dfs(int x, int y) {
		if(x < N && y == M) {
			x++;
			y = 0;
		}

		if(x == N) {
			ans++;
			return;
		}

		if(x > 0 && y > 0 && map[x-1][y] == 1 && map[x][y-1] == 1 && map[x-1][y-1] == 1)	dfs(x, y+1);
		else {
			map[x][y] = 1;
			dfs(x, y+1);
			map[x][y] = 0;
			dfs(x, y+1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ans = 0;
		map = new int[N][M];
		dfs(0,0);
		System.out.print(ans);
	}
}
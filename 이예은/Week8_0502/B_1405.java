package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1405 {
	static int r[] = {0,0,1,-1};
	static int c[] = {1,-1,0,0};
	static int N;
	static boolean visit[][];
	static double answer, percent[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		visit = new boolean[30][30];
		percent = new double[4];
		N = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<4; i++) {
			percent[i] = Integer.parseInt(st.nextToken()) * 0.01;
		}
		
		visit[15][15] = true;
		dfs(15, 15, 0, 1);
		System.out.print(answer);
	}
	
	public static void dfs(int x, int y, int idx, double per) {
		if(idx == N) {
			answer += per;
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(percent[i] == 0)	continue;
			int dr = x + r[i];
			int dc = y + c[i];
			
			if(dr > 0 && dr < 30 && dc > 0 && dc < 30 && !visit[dr][dc]) {
				visit[dr][dc] = true;
				dfs(dr, dc, idx+1, per*percent[i]);
				visit[dr][dc] = false;
			}
		}
	}
}
package week8_0502;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1405 {
	//미친 로봇
	static int N;
	static double[] percent;
	static int[] dr = {0, 0, 1, -1};
	static int[] dc = {1, -1, 0, 0};
	static boolean[][] v;
	static double answer;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		percent = new double[4];
		for(int i=0; i<4; i++) {
			percent[i] = Integer.parseInt(st.nextToken()) * 0.01;
		}
		
		//로봇의 이동경로 단순 -> 똑같은 곳 방문x
		v = new boolean[30][30];
		dfs(15, 15, 0, 1);
		
	}


	private static void dfs(int r, int c, int idx, double total) {
		if(idx == N) {
			answer += total;
			return;
		}
		
		v[r][c] = true;
		for(int i=0; i<4; i++) {
			int nr = r+dr[i];
			int nc = c+dc[i];
			
			if(nr>=0 && nr<30 && nc>=0 && nc<30 && !v[nr][nc]) {
				v[nr][nc] = true;
				dfs(nr, nc, idx+1, total*percent[i]);
				v[nr][nc] = false;
			}
		}
	}

}

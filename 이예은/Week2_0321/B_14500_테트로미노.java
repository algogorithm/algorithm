package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14500 {
	static int r[][] = {{1,0,1},{0,0,0},{1,2,3},{1,2,2},{0,0,1},{0,1,2},{0,0,-1},{0,-1,-2},{0,1,2},
			{1,1,2},{0,-1,-1},{0,-1,1},{0,1,1},{0,1,0},{1,0,-1},{0,-1,0},{-1,0,1},{1,1,1},{0,0,1}};
	static int c[][] = {{0,1,1},{1,2,3},{0,0,0},{0,0,1},{1,2,0},{1,1,1},{1,2,2},{1,1,1},{1,0,0},
			{0,1,1},{1,1,2},{1,1,0},{1,1,2},{-1,0,1},{0,-1,0},{-1,0,1},{0,1,0},{0,1,2},{1,2,2}};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int P[][] = new int[N][M];
		int ans = 0;
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++)
				P[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int t=0; t<r.length; t++) {
					boolean flag = true;
					int sum = P[i][j];
					
					for(int d=0; d<3; d++) {
						int dr = i + r[t][d];
						int dc = j + c[t][d];
						if(dr >= 0 && dr < N && dc >= 0 && dc < M)	sum += P[dr][dc];
						else {
							flag = false;
							break;
						}
					}
					
					if(flag && sum > ans) ans = sum;
				}
			}
		}
		
		System.out.print(ans);
	}
}
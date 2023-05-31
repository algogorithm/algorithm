package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_20002 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int map[][] = new int[N][N];
		int sum[][] = new int[N+1][N+1];
		int answer = Integer.MIN_VALUE;
		
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				sum[i][j] = map[i-1][j-1] + sum[i][j-1] + sum[i-1][j] - sum[i-1][j-1];
			}
		}
		
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				int min = Math.min(N-i, N-j);
				for(int k=0; k<=min; k++) {
					int profit = sum[i+k][j+k] - sum[i-1][j+k] - sum[i+k][j-1] + sum[i-1][j-1];
					answer = Math.max(answer, profit);
				}
			}
		}
		
		System.out.print(answer);
	}
}
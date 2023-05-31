package Week12;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20002_사과나무 {
	static int[] dr = {1,1,0};
	static int[] dc = {0,1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] farm = new int[N+1][N+1];
		int[][] prefixSum = new int[N+1][N+1];
		int max = -1000;
		
		for(int i=1; i<=N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int j=1; j<=N; j++) {
				farm[i][j] = Integer.parseInt(st.nextToken());
//				max = Math.max(max, farm[i][j]);
				sum += farm[i][j];
				prefixSum[i][j] = sum;
			}
		}
		
		for(int i=1; i<=N; i++) {
			int sum = 0;
			for(int j=1; j<=N; j++) {
				sum += prefixSum[j][i];
				prefixSum[j][i] = sum;
			}
		}

		for(int i=N; i>=1; i--) {
			for(int j=N; j>=1; j--) {
				int maxLength = Math.min(i, j);
				for(int k=1; k<=maxLength; k++) {
					int area = prefixSum[i][j] - (prefixSum[i-k][j] + prefixSum[i][j-k]) + prefixSum[i-k][j-k];

					max = Math.max(max, area);
				}
			}
		}
		
		System.out.println(max);
	}

}

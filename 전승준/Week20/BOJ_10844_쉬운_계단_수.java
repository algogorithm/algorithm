package Week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10844_쉬운_계단_수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N][10];
		long sum = 0;
		
		for(int i=1; i<10; ++i) {
			dp[0][i] = 1;
		}
		
		for (int i=1; i<N; ++i) {
			for (int j=0; j<=9; j++) {
				if(j == 0) {
					dp[i][j] = dp[i-1][j+1];
				} else if(j == 9) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = (dp[i-1][j-1] + dp[i-1][j+1]) % 1000000000;
				}
			}
		}
		
		for(int i=0; i<10; ++i) {
			sum += dp[N-1][i];
			sum %= 1000000000;
		}

		System.out.println(sum);
	}
}

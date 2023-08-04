package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_10844 {
	static int N;
	static final long mod = 1000000000L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		long DP[][] = new long[N+1][10];
		long ans = 0;
		
		for(int i=1; i<10; i++) {
			DP[1][i] = 1L;
		}
		
		for(int i=2; i<=N; i++) {
			DP[i][0] = DP[i-1][1] % mod;
			
			for(int j=1; j<9; j++) {
				DP[i][j] = (DP[i-1][j-1] + DP[i-1][j+1]) % mod;
			}
			
			DP[i][9] = DP[i-1][8] % mod;
		}
		
		for(int i=0; i<10; i++) {
			ans += DP[N][i];
		}
		
		System.out.print(ans % mod);
	}
}
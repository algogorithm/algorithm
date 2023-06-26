package Week15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2631_줄세우기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int[] input = new int[N];
		int[] dp = new int[N];
		
		for(int i=0; i<N; ++i) {
			input[i] = Integer.parseInt(br.readLine());
		}

		for(int i=0; i<N; ++i) {
			dp[i] = 1;
			for(int j=0; j<i; ++j) {
				if(input[i] > input[j]) {
					dp[i] = Math.max(dp[i], dp[j]+1);
				}
			}
		}
		
		System.out.println(N - Arrays.stream(dp).max().getAsInt());
	}

}

package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_2631 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N];
		int DP[] = new int[N];
		int max = 1;
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		
		for(int i=0; i<N; i++) {
			DP[i] = 1;
			for(int j=0; j<i; j++) {
				if(A[j] < A[i])	DP[i] = Math.max(DP[i], DP[j]+1);
			}
			max = Math.max(max, DP[i]);
		}
		
		System.out.print(N-max);
	}
}
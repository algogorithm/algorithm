package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10836 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A[][] = new int[N][N];
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int zero = Integer.parseInt(st.nextToken());
			int one = Integer.parseInt(st.nextToken());
			int two = Integer.parseInt(st.nextToken());
			
			for(int j=N-1; j>=0; j--) {
				if(zero != 0)	zero--;
				else if(one != 0) {
					one--;
					A[j][0]++;
				}
				else {
					two--;
					A[j][0] += 2;
				}
			}
			
			for(int j=1; j<N; j++) {
				if(zero != 0)	zero--;
				else if(one != 0) {
					one--;
					A[0][j]++;
				}
				else {
					two--;
					A[0][j] += 2;
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(i > 0 && j > 0)	A[i][j] = A[0][j];
				sb.append(A[i][j]+1+" ");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
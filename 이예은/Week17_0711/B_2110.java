package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2110 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int A[] = new int[N];
		
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(A);
		
		int left = 1, right = A[N-1] - A[0] + 1, ans = 0;
		while(left <= right) {
			int mid = (right + left) / 2;
			int cnt = 1, idx = 0;
			
			for(int i=1; i<N; i++) {
				if(A[i] - A[idx] >= mid) {
					cnt++;
					idx = i;
				}
			}
			
			if(cnt < C) right = mid - 1;
			else {
				ans = Math.max(ans, mid);
				left = mid + 1;
			}
		}
		
		System.out.print(ans);
	}
}
package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1253 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int A[] = new int[N];
		int answer = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(A);
		
		if(N > 2) {
			for(int i=0; i<N; i++) {
				int start = 0, end = N-1;
				
				while(start < end) {
					if(start == i) {
						start++;
						continue;
					}
					if(end == i) {
						end--;
						continue;
					}
					
					if(A[start] + A[end] < A[i])	start++;
					else if(A[start] + A[end] > A[i])	end--;
					else {
						answer++;
						break;
					}
				}
			}
		}
		
		System.out.print(answer);	
	}
}
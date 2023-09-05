package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.StringTokenizer;

public class silver2_2512_예산 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int start = 0, end = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, arr[i]);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			int sum = 0;
			for (int i = 0; i < N; i++) {
				if(mid < arr[i]) {
					sum += mid;
				} else {
					sum += arr[i];
				}
			}
			
			if(sum > M) {
				end = mid-1;
			} else if(sum < M){
				start = mid+1;
			} else {
				break;
			}
		}
		
		System.out.println((start+end)/2);
	}
}

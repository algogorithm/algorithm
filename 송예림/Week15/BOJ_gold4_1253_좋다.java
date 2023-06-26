package BAEKJOON;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gold4_1253_좋다 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int result = 0;
		for (int i = 0; i < N; i++) {
			int start = 0, end = N-1;
			while(true) {
				if(start >= end) {
					break;
				}
				
				if(start == i) {
					start++;
					continue;
				}
				if(end == i) {
					end--;
					continue;
				}
				
				if(arr[start] + arr[end] == arr[i]) { // 같을 때
					result++;
//					System.out.println(arr[i]);
					break;
				} else if(arr[start] + arr[end] > arr[i]) { // 합보다 작을 때 -> end--
					end--;
				} else {
					start++;
				}
			}
		}
		
		System.out.println(result);
	}
}

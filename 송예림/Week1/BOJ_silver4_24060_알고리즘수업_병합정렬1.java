package BAEKJOON;

import java.io.*;
import java.util.*;

public class silver4_24060_알고리즘수업_병합정렬1 {
	static int[] A, temp;
	static int K, cnt = 0, result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		A = new int[N];
		temp = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		merge_sort(0, N-1);
		
		System.out.println(result);
//		System.out.println(Arrays.toString(A)+ " " + cnt);
	}
	
	private static void merge_sort(int p, int r) {
		if(p < r) {
			int q = (p+r) / 2;
			merge_sort(p, q); // 전반부 정렬
			merge_sort(q+1, r); // 후반부 정렬
			merge(p, q, r);
		}
	}

	private static void merge(int p, int q, int r) {
		int i = p; int j = q + 1; int t = 0;
		
		while(i <= q && j <= r) {
			if(A[i] <= A[j]) {
				temp[t] = A[i];
				t++; i++;
			} else {
				temp[t] = A[j];
				t++; j++;
			}
		}
		// 왼쪽 배열 부분이 남은 경우
		while(i <= q) {
			temp[t] = A[i];
			t++; i++;
		}
		// 오른쪽 부분이 남은 경우
		while(j <= r) {
			temp[t] = A[j];
			t++; j++;
		}
		
		i = p; t = 0;
		// 결과를 저장
		while(i <= r) {
			A[i] = temp[t];
			cnt++;
			if(cnt == K) {
				result = temp[t];
				return;
			}
			i++; t++;
		}
	}
}

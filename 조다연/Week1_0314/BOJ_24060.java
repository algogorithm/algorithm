package day1_0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_24060 {
	static int arr[];
	static int tmp[];
	static int K;
	static int cnt = 0;
	static int result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//배열 A의 크기
		int N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		tmp = new int[N];
		
		//저장 횟수
		K = Integer.parseInt(st.nextToken());
		
		//배열 A의 원소
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		mergeSort(0,arr.length-1);
		System.out.println(result);
	}

	private static void mergeSort(int start, int end) {
		if(cnt>K) return;
		
		if(start<end) {
			int mid = (start+end)/2;
			mergeSort(start, mid);
			mergeSort(mid+1, end);
			merge(start, mid, end);
		}
	}

	private static void merge(int start, int mid, int end) {
		int p = start;
		int q = mid+1;
		int idx = 0;
		
		while(p <= mid && q <= end) {
			if(arr[p] <= arr[q]) {
				tmp[idx++] = arr[p++];
			} else {
				tmp[idx++] = arr[q++];
			}
		}
		
		//왼쪽 배열이 남은 경우
		while(p<=mid) {
			tmp[idx++] = arr[p++];
		}
		
		//오른쪽 배열이 남은 경우
		while(q<=end) {
			tmp[idx++] = arr[q++];
		}
		
		//결과 저장
		p = start;
		idx = 0;
		while(p<=end) {
			//값이 저장될 때 cnt++
			cnt++;
			
			//cnt 값이 K와 같아지면 저장된 값을 result로
			if(cnt == K) {
				result = tmp[idx];
				break;
			}
			
			//결과 원본 배열에 저장
			arr[p++] = tmp[idx++];
		}
	}

}

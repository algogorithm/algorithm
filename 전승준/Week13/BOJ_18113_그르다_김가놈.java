package Week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18113_그르다_김가놈 {
	static int[] prepGimbap;
	static int N, K, M, RES = -1;
	static int MAX_GIMBAP_SIZE = (int) Math.pow(10, 9);
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		prepGimbap = new int[N];
		
		for(int i=0; i<N; i++) {
			int curGimbap = Integer.parseInt(br.readLine());
			
			if(curGimbap >= 2*K) curGimbap -= 2*K;
			else if(curGimbap > K) curGimbap -= K;
			else continue;
			
			prepGimbap[i] = curGimbap;
		}
		
		Arrays.sort(prepGimbap);
		
		binarySearch(1, MAX_GIMBAP_SIZE);
		
		System.out.println(RES);
		
	}
	private static void binarySearch(int lo, int hi) {
		int mid = (lo + hi) / 2;
		
		if(lo > hi) return;
		
		int cnt = 0;
		for(int currGimbap : prepGimbap) {
			cnt += currGimbap / mid;
		}
		
		if(cnt < M) binarySearch(lo, mid-1);
		else {
			RES = mid;
			binarySearch(mid+1, hi);
		}
	}

}

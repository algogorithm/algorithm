package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_18113 {
	static ArrayList<Integer> kimbap;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		kimbap = new ArrayList<Integer>();
		int left = 1, right = 0, answer = -1;
		
		for(int i=0; i<N; i++) {
			int kb = Integer.parseInt(br.readLine());
			if(kb >= 2*K) {
				kimbap.add(kb - 2*K);
				if(kb - 2*K > right) right = kb - 2*K;
			} else if(kb > K) {
				kimbap.add(kb - K);
				if(kb - K > right) right = kb - K;
			}
		}
		
		while(left <= right) {
			int mid = (left + right)/2;
			if(count(mid) >= M) {
				answer = Math.max(answer, mid);
				left = mid + 1;
			}
			else	right = mid - 1;
		}
		
		System.out.print(answer);
	}

	private static int count(int p) {
		int cnt = 0;
		for(int k : kimbap) {
			cnt += k / p;
		}
		return cnt;
	}

}
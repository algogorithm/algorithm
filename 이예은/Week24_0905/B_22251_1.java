package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_22251_1 {
	static int N, K, P, X, answer, floor[];
	static final boolean number[][] = {{true, true, true, true, true, true, false}, {false, true, true, false, false, false, false}, 
			{true, true, false, true, true, false, true}, {true, true, true, true, false, false, true},
			{false, true, true, false, false, true, true}, {true, false, true, true, false, true, true},
			{true, false, true, true, true, true, true}, {true, true, true, false, false, false, false},
			{true, true, true, true, true, true, true}, {true, true, true, true, false, true, true}};
	
	static boolean check(int num[]) {
		int cnt = 0;
		for(int i=0; i<K; i++) {
			for(int j=0; j<7; j++) {
				if(number[floor[i]][j] != number[num[i]][j])	cnt++;
			}
			
			if(cnt > P)	return false;
		}
		return true;
	}
	
	static int[] getNum(int X) {
		int num[] = new int[K];
		for(int i=0; i<K; i++) {
			num[i] = X % 10;
			X /= 10;
		}
		return num;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		floor = getNum(X);
		answer = 0;
		
		for(int i=1; i<=N; i++) {
			if(check(getNum(i)))	answer++;
		}
		
		System.out.print(answer-1);
	}
}
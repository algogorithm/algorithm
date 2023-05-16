package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class gold5_2138_전구와스위치 {
	static boolean[] r;
	static int N, result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		String res = br.readLine();
		boolean[] b1 = new boolean[N];
		boolean[] b2 = new boolean[N];
		r = new boolean[N];
		for (int i = 0; i < b2.length; i++) {
			b1[i] = str.charAt(i) == '0' ? false : true;
			b2[i] = str.charAt(i) == '0' ? false : true;
			r[i] = res.charAt(i) == '0' ? false : true;
		}

		// 첫번째 off
		change(1, 0, b1);
		// 첫번째 on
		change(1, 1, onoff(0, b2));
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static boolean[] onoff(int idx, boolean[] b) {
		for (int i = -1; i < 2; i++) {
			if(idx+i >= 0 && idx+i < N)
				b[idx+i] = b[idx+i] ? false : true;
		}
		return b;
	}

	private static void change(int idx, int cnt, boolean[] b) {
		if(idx == N) {
			if(b[idx-1] == r[idx-1]) {
				result = Math.min(result, cnt);
			}
			return;
		}
		
		if(b[idx-1] == r[idx-1]) {
			change(idx+1, cnt, b);
		} else {
			change(idx+1, cnt+1, onoff(idx, b));
		}
	}
}
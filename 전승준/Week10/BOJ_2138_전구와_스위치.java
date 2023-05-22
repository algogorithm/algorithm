package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2138_전구와_스위치 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		char[] firstOff = br.readLine().toCharArray();
		char[] firstOn = Arrays.copyOf(firstOff, N);
		char[] out = br.readLine().toCharArray();
		int cntOff = 0, cntOn = 1;
		
		push(firstOn, 0);
		
		for(int i=1; i<N; i++) {
			if(firstOff[i-1] != out[i-1]) {
				push(firstOff, i);
				cntOff++;
			}
			if(firstOn[i-1] != out[i-1]) {
				push(firstOn, i);
				cntOn++;
			}
			
		}
		
		int res = Integer.MAX_VALUE;
		if(firstOff[N-1] == out[N-1]) {
			res = Math.min(cntOff, res);
		} else if(firstOn[N-1] == out[N-1]){
			res = Math.min(cntOn, res);
		} else {
			res = -1;
		}
		System.out.println(res);
	}
	
	private static void push(char[] in, int index) {
		if(index > 0) in[index-1] = (in[index-1] == '0') ? '1' : '0';
		
		in[index] = (in[index] == '0') ? '1' : '0';
		
		if(index < N-1) in[index+1] = (in[index+1] == '0') ? '1' : '0';
	}

}

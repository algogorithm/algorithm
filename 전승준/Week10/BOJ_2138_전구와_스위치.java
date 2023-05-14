package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2138_전구와_스위치 {
	static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		char[] in = br.readLine().toCharArray();
		char[] out = br.readLine().toCharArray();
		int cnt = 0;

		for(int i=1; i<N; i++) {
			boolean isChanged = false;

			if(in[i-1] != out[i-1]) {
				push(in, i-1);
				for(int d=0; d<N; d++) {
					System.out.print(in[i]);
				}System.out.println();
				isChanged = true;
			}
			
			/*
			if(i==0 && (in[i] != out[i] || in[i+1] != out[i+1])) {
				push(in, 0);
				isChanged = true;
			} else if(i==N-1 && (in[i-1] != out[i-1] || in[i] != out[i])) {
				push(in, N-1);
				isChanged = true;
			} else if (in[i-1] != out[i-1] || in[i] != out[i] || in[i+1] != out[i+1]) {
				push(in, i);
				isChanged = true;
			}
			*/
			
			if(isChanged) cnt++;
		}
		
		if(in[N-1] != out[N-1]) cnt = -1;
			
		System.out.println(cnt);
	}
	
	private static void push(char[] in, int index) {
		if(index > 0) in[index-1] = (in[index-1] == '0') ? '1' : '0';
		
		in[index] = (in[index] == '0') ? '1' : '0';
		
		if(index < N-1) in[index+1] = (in[index+1] == '0') ? '1' : '0';
	}

}

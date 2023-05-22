package Week10;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2138_전구와_스위치_ArrayIndexOutOfBounds {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[] in = br.readLine().toCharArray();
		char[] out = br.readLine().toCharArray();
		int cnt = 0;

		for(int i=0; i<N; i++) {
			boolean isChanged = false;
			
			if(i==0 && (in[i] != out[i] || in[i+1] != out[i+1])) {
				in[i] = in[i] == '0' ? '1' : '0';
				in[i+1] = in[i+1] == '0' ? '1' : '0';
				isChanged = true;
			} else if(i==N-1 && (in[i-1] != out[i-1] || in[i] != out[i])) {
				in[i-1] = in[i-1] == '0' ? '1' : '0';
				in[i] = in[i] == '0' ? '1' : '0';
				isChanged = true;
			} else if (in[i-1] != out[i-1] || in[i] != out[i] || in[i+1] != out[i+1]) {
				in[i-1] = in[i-1] == '0' ? '1' : '0';
				in[i] = in[i] == '0' ? '1' : '0';
				in[i+1] = in[i+1] == '0' ? '1' : '0';
				isChanged = true;
			}
			
			if(isChanged) cnt++;
		}
			
		System.out.println(cnt);
	}
}

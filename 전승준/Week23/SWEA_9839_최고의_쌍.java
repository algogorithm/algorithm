package Week23;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_9839_최고의_쌍 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; ++tc) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			int[] num = new int[N];
			int answer = -1;
			
			for(int i=0; i<N; ++i) {
				num[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(num);
			
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(i == j) continue;
					
					int mul = num[i] * num[j];
					if(checkAscendingNumber(mul)) {
						answer = Math.max(answer, mul);
					}
				}
			}
			
			System.out.println("#"+tc+" "+answer);
		}
	}

	private static boolean checkAscendingNumber(int result) {
		String str = Integer.toString(result);
		
		for(int i=1; i<str.length(); ++i) {
			if(str.charAt(i-1) - str.charAt(i) != -1) {
				return false;
			}
		}
		return true;
	}
}
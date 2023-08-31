package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class D3_9839_최고의쌍 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		L:for (int tc = 1; tc <= TC; tc++) {
			sb.append("#" + tc + " ");
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			for (int j = N-1; j > 0; j--) {
				for (int k = j-1; k >= 0; k--) {
					if(check(arr[j] * arr[k])) {
						sb.append(arr[j] * arr[k] + "\n");
						continue L;
					}
				}
			}
			
			sb.append("-1\n");
		}
		System.out.print(sb);
	}

	private static boolean check(int n) {
		String num = Integer.toString(n);
		
		for (int i = 1; i < num.length(); i++) {
			if((num.charAt(i)-'0') - 1 != num.charAt(i-1) - '0') {
				return false;
			}
		}
		
		return true;
	}

}
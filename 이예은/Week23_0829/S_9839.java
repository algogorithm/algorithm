package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class S_9839 {
	static int N, num[], ans;
	
	static void combination(int idx, int cnt, boolean visit[], int n) {
		if(cnt == 2) {
			if(check(n))	ans = Math.max(ans, n);	
			return;
		}
		
		for(int i = idx; i < N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				n *= num[i];
				combination(i, cnt+1, visit, n);
				visit[i] = false;
				n /= num[i];
			}
		}
	}
	
	static boolean check(int n) {
		boolean flag = true;
		int x = n % 10;
		n /= 10;

		while(n > 0) {
			if(--x != n % 10) {
				flag = false;
				break;
			}
			n /= 10;
		}

		return flag; 
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			N = Integer.parseInt(br.readLine());
			num = new int[N];
			ans = -1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			combination(0, 0, new boolean[N], 1);
			
			sb.append('#').append(tc).append(' ').append(ans).append('\n');
		}
		
		System.out.print(sb);
	}
}
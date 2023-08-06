package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1062_1 {
	static int N, K, ans;
	static String[] word;
	static boolean alpha[];
	
	private static void teach(int idx, int cnt) {
		if(cnt == K) {
			ans = Math.max(ans, read());
			return;
		}
		
		for(int i=idx; i<26; i++) {
			if(!alpha[i]) {
				alpha[i] = true;
				teach(i, cnt+1);
				alpha[i] = false;
			}
		}
	}

	private static int read() {
		int cnt = 0;
		for(String s : word) {
			int i = s.length();
			while(--i >= 0) {
				if(!alpha[s.charAt(i) - 'a'])	break;
			}
			if(i == -1)	cnt++;
		}
		return cnt;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		word = new String[N];
		alpha = new boolean[26];
		ans = 0;
		
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			word[i] = s.substring(4, s.length()-4);
		}
		
		if(K == 26)	ans = N;
		else if(K >= 5) {
			K -= 5;
			alpha['a'-'a'] = true;
			alpha['n'-'a'] = true;
			alpha['t'-'a'] = true;
			alpha['i'-'a'] = true;
			alpha['c'-'a'] = true;
			
			teach(0, 0);
		}
		
		System.out.print(ans);
	}
}
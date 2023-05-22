package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1759 {
	static int L, C;
	static char pw[];
	static StringBuilder sb;
	
	public static void makePW(int n, int idx, char s[], boolean visit[]) {
		if(n == L) {
			if(check(s))	sb.append(s).append("\n");
			return;
		}
		
		for(int i=idx; i<C; i++) {
			if(!visit[i]) {
				visit[i] = true;
				s[n] = pw[i];
				makePW(n+1, i, s, visit);
				visit[i] = false;
			}
		}
	}
	
	public static boolean check(char s[]) {
		int a = 0, b = 0;
		for(int i=0; i<L; i++) {
			if(s[i] == 'a' || s[i] == 'e' || s[i] == 'i' || s[i] == 'o' || s[i] == 'u')	a++;
			else	b++;
		}
		return a > 0 && b > 1 ? true : false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		pw = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<C; i++) {
			pw[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(pw);
		
		makePW(0, 0, new char[L], new boolean[C]);
		System.out.print(sb);		
	}
}
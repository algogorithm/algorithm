package BaekJoon;

import java.io.*;

public class B_2138 {
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		char s1[] = s.toCharArray(); // 누른 경우
		char s2[] = s.toCharArray(); // 안 누른 경우
		char d[] = br.readLine().toCharArray();
		
		s1[0] = s1[0] == '0' ? '1' : '0';
		s1[1] = s1[1] == '0' ? '1' : '0';

		int answer = Math.min(turn(s1, d)+1, turn(s2, d));
		
		System.out.print(answer > 100000 ? -1 : answer);
	}
	
	public static int turn(char[] s, char d[]) {
		int cnt = 0;
		
		for(int i=1; i<N; i++) {
			if(s[i-1] != d[i-1]) {
				cnt++;
				s[i-1] = s[i-1] == '0' ? '1' : '0';
				s[i] = s[i] == '0' ? '1' : '0';
				if(i+1 < N) s[i+1] = s[i+1] == '0' ? '1' : '0';
			}
		}
		
		return s[N-1] == d[N-1] ? cnt : 100001;
	}
}
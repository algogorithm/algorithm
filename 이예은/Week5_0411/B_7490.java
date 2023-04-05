package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_7490 {
	static StringBuilder sb = new StringBuilder();
	
	public static void check(int n, int cnt, String str, int p, int m, int b) {
		str += cnt++;
		
		if(cnt == n+1) {
			String s = str.replaceAll(" ", "");
			int sum = 0;
			
			String plus[] = s.split("\\+");
			for(int i=0; i<plus.length; i++) {
				String minus[] = plus[i].split("-");
				sum += Integer.parseInt(minus[0]);
				for(int j=1; j<minus.length; j++) {
					sum -= Integer.parseInt(minus[j]);
				}
			}

			if(sum == 0)	sb.append(str).append("\n");
			return;
		}
		
		if(p < n-2)	check(n, cnt, str+" ", p+1, m, b);
		if(m < n-2) check(n, cnt, str+"+", p, m+1, b);
		if(b < n-2) check(n, cnt, str+"-", p, m, b+1);
		
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			int n = Integer.parseInt(br.readLine());
			check(n, 1, "", 0, 0, 0);
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
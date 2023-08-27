package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class S_15758 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			String str[] = br.readLine().split(" ");
			StringBuilder s = new StringBuilder();
			StringBuilder t = new StringBuilder();
			
			for(int i=0; i<str[1].length(); i++) {
				s.append(str[0]);
			}
			
			for(int i=0; i<str[0].length(); i++) {
				t.append(str[1]);
			}
			
			sb.append('#').append(tc).append(' ').append(s.toString().equals(t.toString()) ? "yes\n" : "no\n");
		}
		
		System.out.print(sb);
	}
}
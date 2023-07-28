package BAEKJOON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class gold5_12919_A와B2_2 {
	static int result = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String A = br.readLine();
		String B = br.readLine();
		
		dfs(0, B, A);
		
//		System.out.println(B.substring(B.length()-1) + " " + B.charAt(B.length()-1));
		
		System.out.println(result);
	}
	private static void dfs(int head, String a, String b) {
		if(a.length() == b.length()) {
			if(head == 0 && a.equals(b)) {
				result = 1;
			}
			else if(head != 0) {
				for (int i = 0; i < a.length(); i++) {
					if(a.charAt(a.length() - 1 - i) != b.charAt(i)) {
						return;
					}
				}
				result = 1;
			}
			return;
		}
		
		if(head == 0) {
			if(a.charAt(a.length()-1) == 'A') {
				dfs(head, a.substring(0, a.length()-1), b);
			}
			if(a.charAt(head) == 'B') {
				dfs(a.length()-2, a.substring(1), b);
			}
		} else {
			if(a.charAt(0) == 'A') {
				dfs(head-1, a.substring(1), b);
			}
			if(a.charAt(head) == 'B') {
				dfs(0, a.substring(0, a.length()-1), b);
			}
		}
	}

}

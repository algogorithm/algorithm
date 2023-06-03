package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_12904 {
	static String A;
	static int answer;
	
	static void change(StringBuilder B, int length) {
		if(length == A.length()) {
			if(A.equals(B.toString()))	answer = 1;
			return;
		}
		
		if(B.charAt(length-1) == 'A') {
			StringBuilder nsb = new StringBuilder(B);
			change(nsb.deleteCharAt(length-1), length-1);
		}
		
		if(B.charAt(length-1) == 'B') {
			StringBuilder nsb = new StringBuilder(B);
			change(nsb.deleteCharAt(length-1).reverse(), length-1);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		A = br.readLine();
		StringBuilder B = new StringBuilder(br.readLine());
		
		change(B, B.length());
		System.out.print(answer);
	}
}
package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_12919_1 {
	static String S;

	static boolean game(StringBuilder T) {
		if(T.length() == S.length()) {
			if(S.equals(T.toString()))	return true;
			return false;
		}
		
		if(T.charAt(0) == 'B') {
			T.deleteCharAt(0).reverse();
			if(game(T))	return true;
			T.append('B').reverse();
		}
		
		if(T.charAt(T.length()-1) == 'A') {
			T.deleteCharAt(T.length()-1);
			if(game(T))	return true;
			T.append('A');
		}
		
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine();
		StringBuilder T = new StringBuilder().append(br.readLine());
		
		System.out.println(game(T) ? 1 : 0);
	}
}
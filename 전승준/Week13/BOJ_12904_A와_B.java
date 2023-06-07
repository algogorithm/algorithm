package Week13;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_12904_A와_B {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String S = br.readLine();
		String T = br.readLine();
		
		while(S.length() <= T.length()) {
			if(T.charAt(T.length()-1) == 'A') {
				T = T.substring(0, T.length() - 1);
			} else {
				T = T.substring(0, T.length() - 1);
				T = reverseString(T);
			}
			
			if(S.equals(T)) {
				System.out.println(1);
				return;
			}
		}
		
		System.out.println(0);
	}

	private static String reverseString(String t) {
		String reverse = "";
		
		for(int i=t.length()-1; i>=0; i--) {
			reverse += t.charAt(i);
		}

		return reverse;
	}

	
}

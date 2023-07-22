package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_17609 {
	
	public static boolean check(int l, int r, String word) {
		int f = l, b = r;
		while(f <= b) {
			if(word.charAt(f) != word.charAt(b)) return false;
			else {
				f++;
				b--;
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		while(T-- > 0) {
			String word = br.readLine();
			int l = 0, r = word.length()-1, c = 0;
			
			while(l <= r) {
				if(word.charAt(l) == word.charAt(r)) {
					l++;
					r--;
				} else {
					c++;
					if(word.charAt(l+1) == word.charAt(r) && check(l+1, r, word))	break;
					if(word.charAt(l) == word.charAt(r-1) && check(l, r-1, word))	break;
					
					c++;
					break;
				}
			}
			
			sb.append(c).append("\n");
		}
		
		System.out.print(sb);
	}
}
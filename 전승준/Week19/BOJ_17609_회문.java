package Week19;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_17609_회문 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int now=0; now<T; ++now) {
			String str = br.readLine();
			
			sb.append(checkString(str, 0, str.length()-1, false)+"\n");
		}
		System.out.println(sb);
	}

	private static int checkString(String str, int s, int e, boolean isDeleted) {
		if(s >= e) {
			if(!isDeleted) return 0;
			else return 1;
		}
		
		if(str.charAt(s) == str.charAt(e)) {
			return checkString(str, s+1, e-1, isDeleted);
		} else if(!isDeleted) {
			return Math.min(checkString(str, s+1, e, true), checkString(str, s, e-1, true));
		} else return 2;
	}
}

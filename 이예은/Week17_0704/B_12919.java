package BaekJoon;

import java.io.*;

public class B_12919 {

	static int ans;
	public static void game(String S, String T) {
		if(ans==1) //이미 만들 수 있다고 확인 됐다면 리턴
			return;
		
		if(S.length()==T.length()) { //길이가 같을 두 문자열이 같은지 확인
			if(S.equals(T)) //같다면 출력값은 1
				ans = 1;
			return;
		}
			
		if(T.charAt(T.length()-1)=='A') { // 문자열의 맨 뒤가 A인 경우 빼주기
			game(S,T.substring(0,T.length()-1));
		}
		
		if(T.charAt(0)=='B') { //문자열의 맨 앞이 B인 경우 빼고 뒤집기
			StringBuffer sb = new StringBuffer(T.substring(1,T.length()));
			game(S, sb.reverse().toString());
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String S = br.readLine();
		String T = br.readLine();
		ans = 0;
		
		game(S,T);
		System.out.println(ans);
	}
}
package week17_0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {
	//A와 B 2
	static int answer;
	static String S;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//S를 T로
		S = br.readLine();
		StringBuilder T = new StringBuilder(br.readLine());
		
		answer = 0;
		change(T);
		
		//S를 T로 바꿀 수 있으면 1, 없으면 0
		System.out.println(answer);
	}

	private static void change(StringBuilder T) {
		//문자열 길이 같아지면 비교
		if(S.length() == T.length()) {
			if(S.equals(T.toString())) {
				answer = 1;
				return;
			}
			return;
		}
		
		if(T.charAt(T.length()-1) == 'A') {
			//문자열 A 삭제
			change(T.delete(T.length()-1, T.length()));
			T.append('A');
		} 

		if(T.charAt(0) =='B') {
			//문자열 뒤집고 B 삭제
			change(T.delete(0, 1).reverse());
			T.append('B').reverse();
		} 

		return;
	}
}

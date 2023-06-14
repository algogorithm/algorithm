package week13_0613;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12904 {
	//A와 B
	static int answer;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//S를 T로
		String S = br.readLine();
		StringBuilder T = new StringBuilder(br.readLine());
		
		answer = 0;
		change(S,T);
		
		//S를 T로 바꿀 수 있으면 1, 없으면 0
		System.out.println(answer);
	}

	private static void change(String S, StringBuilder T) {
		//길이가 같아지면 종료
		if(T.length() == S.length()) {
			if(T.toString().equals(S)) {
				answer = 1;
				return;
			}
			return;
		}
		
		//맨 뒤가 A라면 그냥 삭제
		if(T.charAt(T.length()-1) == 'A') {
			T.delete(T.length()-1, T.length());
		} else { 
			//맨 뒤가 B라면 삭제하고 뒤집어주기
			//if(T.charAt(T.length()-1) == 'B')
			T.delete(T.length()-1, T.length()).reverse();
		}
		
		change(S, T);
	}

}

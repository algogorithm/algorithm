package week17_0711;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_12919 {
	//A�� B 2
	static int answer;
	static String S;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//S�� T��
		S = br.readLine();
		StringBuilder T = new StringBuilder(br.readLine());
		
		answer = 0;
		change(T);
		
		//S�� T�� �ٲ� �� ������ 1, ������ 0
		System.out.println(answer);
	}

	private static void change(StringBuilder T) {
		//���ڿ� ���� �������� ��
		if(S.length() == T.length()) {
			if(S.equals(T.toString())) {
				answer = 1;
				return;
			}
			return;
		}
		
		if(T.charAt(T.length()-1) == 'A') {
			//���ڿ� A ����
			change(T.delete(T.length()-1, T.length()));
			T.append('A');
		} 

		if(T.charAt(0) =='B') {
			//���ڿ� ������ B ����
			change(T.delete(0, 1).reverse());
			T.append('B').reverse();
		} 

		return;
	}
}

package week16_0704;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935 {
	//���ڿ� ����

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> st = new Stack<>();
		
		char[] origin = br.readLine().toCharArray();
		char[] bomb = br.readLine().toCharArray();
		
		for(int i=0; i<origin.length; i++) {
			st.push(origin[i]);
			
			//
			if(st.size() >= bomb.length) {
				boolean flag = true;
				
				for(int j=0; j<bomb.length; j++) {
					char c1 = st.get(st.size() - bomb.length + j);
					char c2 = bomb[j];
					
					if(c1 != c2) { //���� ���ڿ��� ���� ������ 
						flag = false;
						break;
					}
				}
				
				if(flag) { //���� ���ڿ��̸� ���ֱ�
					for(int j=0; j<bomb.length; j++) {
						st.pop();
					}
				}
			}
		}
		
		//��� ������ ���� �� ���� ���ڿ� ���
		//�����ִ� ���ڿ� ������ FRULA ���
		if(st.size() != 0) {
			StringBuilder sb = new StringBuilder();
            for(char c : st) {
                sb.append(c);
            }
            System.out.println(sb.toString());
		} else {
			System.out.println("FRULA");
		}
		
	}

}

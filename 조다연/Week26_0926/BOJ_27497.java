package week26_0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_27497 {
	//���ĺ� ���

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		Deque<Character> deque = new ArrayDeque<>();
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int btn = Integer.parseInt(st.nextToken());
			
			if(btn==3) { //���� ���߿� �߰��� ��� ����
				if(!stack.isEmpty()) {
					//���ڿ��� �� �ڿ� ���� ��찡 �ƴ� ���� 
					//�� �տ� �ִ°� �� ������
					if(stack.pop()==1) deque.pollLast();
					else deque.pollFirst();
				}
			} else { 
				stack.push(btn);
				
				//1 : ���ڿ� �� �ڿ� �߰�
				//2 : ���ڿ� �� �տ� �߰�
				if(btn==1) deque.addLast(st.nextToken().charAt(0));
				else deque.addFirst(st.nextToken().charAt(0));
			}
		}
		
		while(!deque.isEmpty()) {
			sb.append(deque.poll());
		}
		
		//�ϼ��� ���ڿ� ���
		//�ϼ��� ���ڿ��� �� ���ڿ��� ��� 0�� ���
		System.out.println(sb.length()==0? 0 : sb);

	}

}

package week26_0919;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_27497 {
	//알파벳 블록

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
			
			if(btn==3) { //가장 나중에 추가된 블록 제거
				if(!stack.isEmpty()) {
					//문자열을 맨 뒤에 넣은 경우가 아닌 경우는 
					//맨 앞에 있는게 젤 나중임
					if(stack.pop()==1) deque.pollLast();
					else deque.pollFirst();
				}
			} else { 
				stack.push(btn);
				
				//1 : 문자열 맨 뒤에 추가
				//2 : 문자열 맨 앞에 추가
				if(btn==1) deque.addLast(st.nextToken().charAt(0));
				else deque.addFirst(st.nextToken().charAt(0));
			}
		}
		
		while(!deque.isEmpty()) {
			sb.append(deque.poll());
		}
		
		//완성된 문자열 출력
		//완성된 문자열이 빈 문자열인 경우 0을 출력
		System.out.println(sb.length()==0? 0 : sb);

	}

}

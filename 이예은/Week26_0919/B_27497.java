package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_27497 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Character> deque = new ArrayDeque<Character>();
		Stack<Integer> stack = new Stack<Integer>();
		StringBuilder sb = new StringBuilder();
		
		while(N-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int button = Integer.parseInt(st.nextToken());
			
			if(button == 3) {
				if(!stack.isEmpty()) {
					if(stack.pop() == 1)	deque.pollLast();
					else	deque.pollFirst();
				}
			} else {
				stack.push(button);
				if(button == 1)	deque.addLast(st.nextToken().charAt(0));
				else	deque.addFirst(st.nextToken().charAt(0));
			}
		}
		
		while(!deque.isEmpty()) {
			sb.append(deque.poll());
		}
		
		System.out.print(sb.length() == 0 ? 0 : sb);
	}
}
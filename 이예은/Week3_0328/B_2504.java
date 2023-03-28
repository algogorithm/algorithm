package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class B_2504 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] str = br.readLine().toCharArray();
		Stack<Character> stack1 = new Stack<Character>();
		Stack<Integer> stack2 = new Stack<Integer>();
		boolean flag = false;
		int ans = 0;
		
		for(int i=0; i<str.length; i++) {
			if(str[i] == '(' || str[i] == '[')	stack1.push(str[i]);
			else if (!stack1.isEmpty()) {
				int n = 0;
				while(!stack1.isEmpty() && stack1.peek() == 'N') {
					stack1.pop();
					n += stack2.pop();
				}
				
				if(!stack1.isEmpty() && stack1.peek() == '(' && str[i] == ')') {
					stack1.pop();
					stack1.push('N');
					if(n==0)	stack2.push(2);
					else	stack2.push(n*2);
				} else if (!stack1.isEmpty() && stack1.peek() == '[' && str[i] == ']') {
					stack1.pop();
					stack1.push('N');
					if(n==0)	stack2.push(3);
					else	stack2.push(n*3);
				} else {
					flag = true;
					break;
				}
			} else {
				flag = true;
				break;
			}
		}
		
		while(!flag && !stack1.isEmpty()) {
			if(stack1.pop() != 'N') flag = true;
		}
		
		while(!flag && !stack2.isEmpty()) {
			ans += stack2.pop();
		}
		
		System.out.print(ans);
	}
}
package Week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_2504_괄호의_값 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		Stack<Character> stack = new Stack<>();
		int value = 1, result = 0;
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) == '(') {
				stack.add('(');
				value *= 2;
				
			} else if(str.charAt(i) == '[') {
				stack.add('[');
				value *= 3;
				
			} else if (str.charAt(i) == ')'){
				if(stack.isEmpty() || stack.peek() != '(') {
					result = 0;
					break;
				} else if(str.charAt(i-1) == '(') {
					result += value;					
				}
				
				value /= 2;
				stack.pop();
				
			} else if(str.charAt(i) == ']') {
				if(stack.isEmpty() || stack.peek() != '[') {
					result = 0;
					break;
				} else if(str.charAt(i-1) == '[') {
					result += value;					
				}
				
				value /= 3;
				stack.pop();
				
			}
		}
		
		if(!stack.isEmpty()) result = 0;
		System.out.println(result);
	}

}

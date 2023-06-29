package Week16;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9935_문자열_폭발 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();
		String target = br.readLine();
		Stack<Character> stack = new Stack<>();
		
		for(int i=0; i<input.length(); ++i) {
			stack.push(input.charAt(i));
			
			if(stack.size() >= target.length()) {
				boolean isSame = true;
				int idx = target.length()-1;
				
				for(int j=stack.size()-1; j>=stack.size()-target.length(); --j) {
					if(stack.get(j) != target.charAt(idx--)) {
						isSame = false;
						break;
					}
				}
				
				if(isSame) {
					for(int j=0; j<target.length(); ++j) {
						stack.pop();
					}
				}
			}
			
		}

		if(stack.isEmpty()) sb.append("FRULA");
		else for(char ch : stack) sb.append(ch);
		
		System.out.println(sb);
	}

}

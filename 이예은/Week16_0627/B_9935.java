package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class B_9935 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char str[] = br.readLine().toCharArray();
		char bomb[] = br.readLine().toCharArray();
		Stack<Character> stack = new Stack<Character>();
		int slength = str.length, blength = bomb.length;
		
		for(int i=0; i<slength; i++) {
			stack.add(str[i]);
			
			if(stack.size() >= blength && stack.peek() == bomb[blength-1]) {
				boolean flag = true;
				Stack<Character> tmp = new Stack<Character>();
				for(int j=blength-1; j>=0; j--) {
					if(stack.peek() == bomb[j])	tmp.add(stack.pop());
					else {
						flag = false;
						break;
					}
				}
				
				if(!flag) {
					while(!tmp.isEmpty()) {
						stack.add(tmp.pop());
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while(!stack.isEmpty()) {
			sb.append(stack.pop());
		}
		
		System.out.print(sb.length() == 0 ? "FRULA" : sb.reverse());
	}
}
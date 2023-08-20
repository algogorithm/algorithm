package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class B_2716 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		while(N-- > 0) {
			String tree = br.readLine();
			int max = 0, depth = 0;
			
			for(int i=0; i<tree.length(); i++) {
				if(tree.charAt(i) == '[')	max = Math.max(++depth, max);
				else	depth--;
			}
			
			sb.append((int)Math.pow(2, max)).append("\n");
		}
		
		System.out.print(sb);
	}
}
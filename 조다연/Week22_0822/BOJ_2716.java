package week22_0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2716 {
	//원숭이 매달기

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int t=0; t<N; t++) {
			String tree = br.readLine();
			int depth = 0, maxDepth = 0;
			
			for(int i=0; i<tree.length(); i++) {
				if(tree.charAt(i)=='[') {
					depth++;
					maxDepth = Math.max(maxDepth, depth);
				} else {
					depth--;
				}
			}
			
			int answer = (int) Math.pow(2, maxDepth);
			System.out.println(answer);
		}
	}

}

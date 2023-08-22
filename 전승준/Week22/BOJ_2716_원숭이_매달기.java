package Week22;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2716_원숭이_매달기 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=0; tc<T; ++tc) {
			String tree = br.readLine();
			int depth = 0;
			int max_depth = 0;
			
			for(int i=0; i<tree.length(); ++i) {
				if(tree.charAt(i) == '[') ++depth;
				else --depth;
				
				max_depth = Math.max(max_depth, depth);
			}
			
			System.out.println((int) Math.pow(2, max_depth));
		}
	}

}

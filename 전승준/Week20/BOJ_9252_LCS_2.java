package Week20;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9252_LCS_2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String first = br.readLine();
		String second = br.readLine();
		int[][] dp = new int[first.length()+1][second.length()+1];
		
		for(int i=1; i<=first.length(); ++i) {
			char ch = first.charAt(i-1);
			for(int j=1; j<=second.length(); ++j) {
				if(ch == second.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		
		System.out.println(dp[first.length()][second.length()]);
		System.out.println(checkSameString(first, dp));
	}

	
	private static String checkSameString(String str, int[][] dp) {
		int i=dp.length-1, j=dp[0].length-1;
		Stack<Character> stack = new Stack<>();
		
		while(i > 0 && j > 0) {
			if(dp[i][j] == dp[i-1][j]) {
				--i;
			} else if(dp[i][j] == dp[i][j-1]) {
				--j;
			} else {
				stack.add(str.charAt(i-1));
				--i; --j;
			}
		}
		
		String result = "";
		while(!stack.isEmpty()) {
			result += stack.pop();
		}
		
		return result;
	}
}
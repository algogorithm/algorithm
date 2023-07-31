package week20_0801;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_9252 {
	//LCS2
	static int[][] dp;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s1 = br.readLine();
		String s2 = br.readLine();
		int l1 = s1.length();
		int l2 = s2.length();
		
		dp = new int[l1 + 1][l2 + 1];

        for(int i = 1; i <= l1; i++) {
            for(int j = 1; j <= l2; j++) {
                // (i-1)번째 문자 == (j-1)번째 문자
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    //이전 열(i-1)과 이전 행(j-1)의 값 중 큰 것으로 갱신
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        ToString(s1, l1, l2);
        System.out.println(dp[l1][l2]);
        System.out.println(sb);

	}

	private static void ToString(String s, int i, int j) {
		 Stack<Character> st = new Stack<>();
         while (i > 0 && j > 0) {
             if (dp[i][j] == dp[i - 1][j]) {
                 i--;
             } else if (dp[i][j] == dp[i][j - 1]) {
                 j--;
             } else {
                 st.push(s.charAt(i-1));
                 i--;
                 j--;
             }
         }
         
         while (!st.isEmpty()) {
             sb.append(st.pop());
         }
	}
}

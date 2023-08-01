package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class gold4_9252_LCS2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1]; // 첫째줄 비워두기! 범위 안벗어나게!

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        sb.append(dp[str1.length()][str2.length()]).append("\n");

        // 끝에서부터 탐색
        Stack<Character> stk = new Stack<>();
        int r = str1.length(), c = str2.length();
        while (r > 0 && c > 0) {
            if(dp[r-1][c] == dp[r][c]){
                r--;
            } else if(dp[r][c-1] == dp[r][c]){
                c--;
            } else { // 둘 다 같지않으면 추가해야함
                stk.push(str1.charAt(r-1));
                r--; c--;
            }
        }

        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        System.out.println(sb);
    }
}
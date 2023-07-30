package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class lcs2 {
    public static String findLCS(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m+1][n+1];

        // 다이내믹 프로그래밍 테이블 채우기
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1] + 1;
                else
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
            }
        }
        // LCS 추적
        int i = m, j = n;
        StringBuilder lcs = new StringBuilder();
        while (i > 0 && j > 0) {
            if (s1.charAt(i-1) == s2.charAt(j-1)) {
                lcs.insert(0, s1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i-1][j] >= dp[i][j-1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.toString();
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s1 = bf.readLine();
        String s2 = bf.readLine();

        String lcs = findLCS(s1, s2);
        System.out.println(lcs.length());
        System.out.println(lcs);
    }
}
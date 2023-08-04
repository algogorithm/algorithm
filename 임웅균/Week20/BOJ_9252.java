package Week20;

import java.io.*;
import java.util.Stack;

public class BOJ_9252 {
    /*
        참고 블로그
        https://velog.io/@emplam27/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EA%B7%B8%EB%A6%BC%EC%9C%BC%EB%A1%9C-%EC%95%8C%EC%95%84%EB%B3%B4%EB%8A%94-LCS-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Longest-Common-Substring%EC%99%80-Longest-Common-Subsequence
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        // 이 배열은 각 위치에서 가장 긴 증가하는 부분 수열의 길이를 저장한다.
        int[][] dp = new int[A.length + 1][B.length + 1];
        for (int r = 1; r <= A.length; r++) {
            for (int c = 1; c <= B.length; c++) {
                if (A[r - 1] == B[c - 1]) {
                    dp[r][c] = dp[r - 1][c - 1] + 1;
                } else {
                    dp[r][c] = Math.max(dp[r - 1][c], dp[r][c - 1]);
                }
            }
        }
        bw.write(String.valueOf(dp[A.length][B.length]));
        bw.newLine();
        // LCS출력
        Stack<Character> stack = new Stack<>();
        int r = A.length - 1;
        int c = B.length - 1;
        while (r >= 0 && c >= 0) {
            if (A[r] == B[c]) {
                stack.push(A[r]);
                r--;
                c--;
            } else {
                if (dp[r][c + 1] > dp[r + 1][c]) {
                    r--;
                } else {
                    c--;
                }
            }
        }
        while (!stack.empty()) {
            bw.write(stack.pop());
        }
        bw.newLine();
        bw.close();
    }
}

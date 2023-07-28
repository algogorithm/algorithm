package Week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1958 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        char[] C = br.readLine().toCharArray();
        int[][][] dp = new int[A.length + 1][B.length + 1][C.length+1];
        for(int i=1; i<=A.length; i++){
            for(int j=1; j<=B.length; j++){
                for(int k=1; k<=C.length; k++){
                    if(A[i-1] == B[j-1] && B[j-1] == C[k-1]) {
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    }
                    else {
                        dp[i][j][k] = Math.max(dp[i-1][j][k], Math.max(dp[i][j-1][k], dp[i][j][k-1]));
                    }
                }
            }
        }
        System.out.println(dp[A.length][B.length][C.length]);
    }
}

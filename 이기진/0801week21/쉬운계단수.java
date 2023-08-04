package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 쉬운계단수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int dp[][] = new int [101][10];
        for(int i=1; i<10; i++){
            dp[1][i]=1;
        }
        for(int i=2; i<=n; i++) {
            for(int j=0; j<10; j++) {
                if(j == 0)
                    dp[i][j] = dp[i-1][j+1];
                else if(j == 9)
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j+1];
                dp[i][j] %= 1000000000;
            }
        }
        long sum=0;
        for(int i =0; i<10; i++){
            sum = sum+dp[n][i];
        }
        System.out.println(sum%1000000000);
    }
}

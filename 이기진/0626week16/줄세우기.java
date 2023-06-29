package d202306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        int[] dp = new int[n];
        int answer = 0;
        Arrays.fill(dp,1);

        for(int i = 0 ; i < n; i ++){
            nums[i] = Integer.parseInt(br.readLine());
        }

        for(int i = 1 ; i<n; i++){
            for(int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    answer = Math.max(dp[i],answer);
                }
            }
        }

        System.out.println(n-answer);

    }
}
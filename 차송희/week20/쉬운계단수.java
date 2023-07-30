import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//쉬운 계단 수
public class BJ10884 {
    static final int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp[i][j]: 길이가 i이고 끝자리가 j인 계단 수의 개수
        int[][] dp = new int[N + 1][10];

        // 길이가 1인 계단 수는 모두 1로 초기화
        for (int i = 1; i <= 9; i++) {
            dp[1][i] = 1;
        }

        // 길이가 2부터 N인 계단 수를 구함
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][1];
                } else if (j == 9) {
                    dp[i][j] = dp[i - 1][8];
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
                }
            }
        }

        // 길이가 N인 계단 수의 총 개수 계산
        int totalCount = 0;
        for (int i = 0; i <= 9; i++) {
            totalCount = (totalCount + dp[N][i]) % MOD;
        }

        System.out.println(totalCount);
    }
}

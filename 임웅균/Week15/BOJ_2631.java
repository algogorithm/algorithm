package Week15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_2631 {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        // 이 배열은 각 위치에서 가장 긴 증가하는 부분 수열의 길이를 저장한다.
        int[] dp = new int[N];
        // 첫 번째 원소는 자기 자신이므로,
        // 가장 긴 증가하는 부분 수열의 길이는 최소 1임
        dp[0] = 1;
        // 결과를 저장할 변수
        int ans = 0;
        // 수열의 두 번째 원소부터 시작해서 마지막 원소까지 반복
        for(int i=1;i<N;i++){
            // 현재 위치의 최대 길이는 1
            dp[i] = 1;
            // 현재 위치 이전의 모든 위치를 확인
            for(int j=0;j<i;j++){
                // 현재 위치의 원소 값이 이전 위치의 원소 값보다 클 경우
                if(input[i]>input[j])
                    // 현재 위치의 최대 길이를 갱신(현재까지의 최대 길이 vs 이전 위치의 최대 길이+1)
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
            // 전체 최대 길이를 갱신(현재까지의 전체 최대 길이 vs 현재 위치의 최대 길이)
            ans = Math.max(ans, dp[i]);
        }
        // 원래 수열의 길이에서 가장 긴 증가하는 부분 수열의 길이를 뺀 값
        System.out.println(N-ans);
    }
}

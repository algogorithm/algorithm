import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20002_사과나무 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] prefixSum = new int[N + 1][N + 1];

        for (int r = 1; r <= N; ++r) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int c = 1; c <= N; ++c) {
                prefixSum[r][c] = prefixSum[r][c - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int r = 1; r <= N; ++r) {
            for (int c = 1; c <= N; ++c) {
                prefixSum[r][c] += prefixSum[r - 1][c];
            }
        }

        System.out.println(solve(N, prefixSum));
    }

    public static int solve(int N, int[][] prefixSum) {
        int answer = Integer.MIN_VALUE;
        for (int k = 1; k <= N; ++k) {
            for (int r = 1; r <= N - k + 1; ++r) {
                for (int c = 1; c <= N - k + 1; ++c) {
                    answer = Math.max(answer, (prefixSum[r + k - 1][c + k - 1] - prefixSum[r + k - 1][c - 1]) - (prefixSum[r - 1][c + k - 1] - prefixSum[r - 1][c - 1]));
                }
            }
        }
        return answer;
    }
}

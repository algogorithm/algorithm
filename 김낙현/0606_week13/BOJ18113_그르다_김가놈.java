import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ18113_그르다_김가놈 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> bobLength = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            int length = Integer.parseInt(br.readLine());

            if (length <= K) continue;

            length = (length < 2 * K) ? length - K : length - 2 * K;
            bobLength.add(length);
        }

        int left = 1;
        int right = 1_000_000_000;

        int answer = -1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isPossible(bobLength, M, mid)) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean isPossible(List<Integer> bobLength, int M, int P) {
        int cnt = 0;

        for (int length : bobLength) {
            cnt += (length / P);
        }

        return cnt >= M;
    }
}

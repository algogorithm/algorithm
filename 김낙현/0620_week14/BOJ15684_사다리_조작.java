import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15684_사다리_조작 {
    static boolean[][] ladder;
    static int N, M, H;
    static int answer = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        ladder = new boolean[H + 1][N + 1];

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ladder[r][c] = true;
        }

        if (M == 0) {
            System.out.println(0);
        } else {
            simulation(1, 0);
            System.out.println(answer);
        }
    }

    private static void simulation(int row, int count) {
        if (answer != -1 && count >= answer) {
            return;
        }

        if (check()) {
            answer = count;
            return;
        }

        if (count == 3) {
            return;
        }

        // 사다리 놓기
        for (int c = 1; c < N; ++c) {
            for (int r = row; r <= H; ++r) {
                if (!ladder[r][c] && !ladder[r][c - 1] && !ladder[r][c + 1]) {
                    ladder[r][c] = true;
                    simulation(r, count + 1);
                    ladder[r][c] = false;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= N; ++i) {
            int c = i;
            for (int r = 1; r <= H; ++r) {
                if (ladder[r][c]) {
                    ++c;
                } else if (ladder[r][c - 1]) {
                    --c;
                }
            }
            if (c != i) {
                return false;
            }
        }
        return true;
    }
}

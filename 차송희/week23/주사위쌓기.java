import java.io.*;
import java.util.*;

public class 주사위쌓기 {
    static int N, Max;
    static int[][] dice;
    static int[] op = { 5, 3, 4, 1, 2, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Max = -1;

        for (int i = 0; i < 6; i++) {
            int max = 0;
            for (int j = 0; j < 6; j++) {
                if (j == i || j == op[i])
                    continue;
                max = Math.max(max, dice[0][j]);
            }
            solve(dice[0][i], max, 1);
        }
        System.out.println(Max);
    }

    private static void solve(int top, int sum, int cnt) {
        if (cnt == N) {
            Max = Math.max(sum, Max);
            return;
        }
        int idx = 0;
        for (int i = 0; i < 6; i++) {
            if (dice[cnt][i] == top) {
                idx = i;
                break;
            }
        }
        int nextTop = op[idx];
        int m = 0;
        for (int j = 0; j < 6; j++) {
            if (j == nextTop || j == idx)
                continue;
            m = Math.max(m, dice[cnt][j]);
        }
        solve(dice[cnt][nextTop], sum + m, cnt + 1);
    }
}

import java.io.*;
import java.util.*;

public class BOJ10836_여왕벌 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] growth = new int[2 * m - 1];

        Arrays.fill(growth, 1);

        for (int day = 1; day <= n; ++day) {
            st = new StringTokenizer(br.readLine(), " ");
            int zeroCnt = Integer.parseInt(st.nextToken());
            int oneCnt = Integer.parseInt(st.nextToken());
            int twoCnt = Integer.parseInt(st.nextToken());

            for (int i = 0; i < growth.length; ++i) {
                if (zeroCnt > 0) {
                    --zeroCnt;
                } else if (oneCnt > 0) {
                    --oneCnt;
                    growth[i] += 1;
                } else if (twoCnt > 0) {
                    --twoCnt;
                    growth[i] += 2;
                } else {
                    System.out.println("Something Wrong!");
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            sb.append(growth[m + i - 1]).append(" ");
        }
        sb.append("\n");
        for (int i = m - 2; i >= 0; --i) {
            sb.append(growth[i]).append(" ");
            for (int j = 1; j < m; ++j) {
                sb.append(growth[m + j - 1]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

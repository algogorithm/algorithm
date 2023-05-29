import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_20002 {
    static int N;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()) {
                map[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }
        // 점화식을 통해 계산하기 위해 크기를 N+1로 지정해준다.
        int[][] sumMap = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                sumMap[r][c] = map[r - 1][c - 1] + sumMap[r - 1][c] + sumMap[r][c - 1] - sumMap[r - 1][c - 1];
            }
        }
        int max = -10000;
        // K는 1보다 크거나 같고 N보다 작거나 같은 정수라구!
        // 그리고 sumMap의 사이즈는 N+1이기에 r과 c를 N까지 반복해준다.
        // 또한 k = 1이기 때문에 실질적으로 원본 배열의 0,0부터 탐색하는것이다.
        for(int k = 1; k <= N; k++){
            for(int r = k; r <= N; r++){
                for(int c = k; c <= N; c++){
                    int sum = sumMap[r][c] - sumMap[r - k][c] - sumMap[r][c - k] + sumMap[r - k][c - k];
                    max = Math.max(sum, max);
                }
            }
        }
        System.out.println(max);
    }
}

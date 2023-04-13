import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14712_넴모넴모 {

    static boolean map[][];
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new boolean[n+1][m+1];

        dfs(1, 1);

        System.out.println(answer);
    }

    static public void dfs(int r, int c) {
        if (r >= map.length) {
            ++answer;
            return;
        }

        boolean newLine = (c == map[0].length - 1);
        int nextR = (newLine) ? r+1 : r;
        int nextC = (newLine) ? 1 : c + 1;

        // 현재 위치에 놓을 수 없음
        if (map[r - 1][c - 1] && map[r - 1][c] && map[r][c - 1]) {
            dfs(nextR, nextC);
        } else {
            // 현재 위치에 놓음
            map[r][c] = true;
            dfs(nextR, nextC);

            // 현재 위치에 놓지 않음
            map[r][c] = false;
            dfs(nextR, nextC);
        }
    }
}

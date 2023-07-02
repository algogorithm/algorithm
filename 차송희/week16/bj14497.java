import java.io.*;
import java.util.*;

public class BJ14497 {
    static StringBuilder sb = new StringBuilder();
    static char[][] arr;
    static boolean[][] v;
    static int[][] pos = new int[2][2];
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, 1, -1 };

    static int n, m, res;
    static boolean find;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new char[n][m];

        st = new StringTokenizer(br.readLine());
        // 주난 위치, 범인 위치
        for (int i = 0; i < 2; i++) {
            pos[i][0] = Integer.parseInt(st.nextToken());
            pos[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        int step = 0;
        while (!find) {
            v = new boolean[n][m];
            solve(pos[0][0] - 1, pos[0][1] - 1);
            step++;
        }
        res = step;
        System.out.println(res);
    }

    private static void solve(int y, int x) {
        if (y == pos[1][0] - 1 && x == pos[1][1] - 1) {
            find = true;
            return;
        }
        if (v[y][x])
            return;
        v[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny < 0 || nx < 0 || nx >= m || ny >= n || v[ny][nx])
                continue;
            if (arr[ny][nx] == '1') {
                arr[ny][nx] = '0';
                v[ny][nx] = true;
                continue;
            }
            solve(ny, nx);
        }
    }
}

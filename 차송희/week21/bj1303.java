//전쟁-전투

import java.util.Scanner;

public class BJ1303 {
    public class Main {
        static int N, M;
        static char[][] map;
        static boolean[][] v;
        static int[] dx = { 1, -1, 0, 0 };
        static int[] dy = { 0, 0, 1, -1 };

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            N = sc.nextInt();
            M = sc.nextInt();
            map = new char[M][N];
            v = new boolean[M][N];

            for (int i = 0; i < M; i++) {
                String row = sc.next();
                for (int j = 0; j < N; j++) {
                    map[i][j] = row.charAt(j);
                }
            }

            int white = 0, blue = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (!v[i][j]) {
                        int cnt = dfs(i, j, map[i][j]);
                        if (map[i][j] == 'W') {
                            white += cnt * cnt;
                        } else {
                            blue += cnt * cnt;
                        }
                    }
                }
            }

            System.out.println(white + " " + blue);
        }

        static int dfs(int x, int y, char team) {
            if (x < 0 || x >= M || y < 0 || y >= N || v[x][y] || map[x][y] != team) {
                return 0;
            }

            v[x][y] = true;
            int cnt = 1;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                cnt += dfs(nx, ny, team);
            }

            return cnt;
        }
    }
}

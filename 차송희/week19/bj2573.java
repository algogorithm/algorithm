import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BJ2573 {
    static int[][] map;
    static boolean[][] visited;
    static int n, m;
    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int year = 0;
        while (true) {
            int iceCnt = getIceCnt();
            if (iceCnt == 0) { // 빙산이 모두 녹은 경우
                System.out.println(0);
                break;
            } else if (iceCnt >= 2) { // 빙산이 분리되었을 경우
                System.out.println(year);
                break;
            }

            visited = new boolean[n][m];
            year++;
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }
            meltMap();
        }
    }

    static int getIceCnt() {
        int cnt = 0;
        visited = new boolean[n][m];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }
        return cnt;
    }

    static void bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int r = current.x;
            int c = current.y;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m) {
                    if (map[nr][nc] > 0 && !visited[nr][nc]) {
                        queue.add(new Point(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }

    static void meltMap() {
        int[][] tmp = new int[n][m];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                if (map[i][j] > 0) {
                    int meltCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nr = i + dr[k];
                        int nc = j + dc[k];
                        if (map[nr][nc] == 0) {
                            meltCnt++;
                        }
                    }
                    tmp[i][j] = Math.max(0, map[i][j] - meltCnt);
                }
            }
        }
        map = tmp;
    }
}


package d202307;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class 성곽 {

    static int n, m;
    static int[][] map;

    static int cnt, maxSize = Integer.MIN_VALUE;

    static boolean[][] checked;

    static int[] dx = { 0, -1, 0, 1 };
    static int[] dy = { -1, 0, 1, 0 };

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 방의 개수 + 가장 넓은 방의 넓이 계산
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!checked[i][j]) {
                    checked[i][j] = true;
                    maxSize = Math.max(maxSize, bfs(i, j));
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
        System.out.println(maxSize);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int bit = 1; bit < 16; bit <<= 1) {
                    if ((map[i][j] & bit) != 0) {
                        checked = new boolean[n][m];
                        map[i][j] -= bit;
                        checked[i][j] = true;
                        maxSize = Math.max(maxSize, bfs(i, j));
                        map[i][j] += bit;
                    }
                }
            }
        }

        System.out.println(maxSize);
    }

    static int bfs(int x, int y) {
        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(x, y));
        int size = 1;

        while (!q.isEmpty()) {
            Node current = q.poll();

            int v = map[current.x][current.y];

            int bit = 1;

            for (int d = 0; d < 4; d++) {
                if ((v & bit) == 0) {
                    int nx = current.x + dx[d];
                    int ny = current.y + dy[d];

                    if (nx >= 0 && nx < n  && ny >= 0 && ny < m && !checked[nx][ny]) {
                        checked[nx][ny] = true;
                        q.add(new Node(nx, ny));
                        size++;
                    }
                }

                bit <<= 1;
            }
        }

        return size;
    }


}

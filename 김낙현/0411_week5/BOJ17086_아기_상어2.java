import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ17086_아기_상어2 {

    static final int[] DR = {-1, -1, -1,
        -0, +0,
        +1, +1, +1};
    static final int[] DC = {-1, +0, +1,
        -1, +1,
        -1, +0, +1};
    static int[][] map;

    static class Shark {

        int r = 0;
        int c = 0;

        Shark(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int r = 0; r < n; ++r) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < m; ++c) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = -1;
        for (int r = 0; r < n; ++r) {
            for (int c = 0; c < m; ++c) {
                if (map[r][c] == 1) {
                    continue;
                }
                answer = Math.max(getSafeDistance(r, c), answer);
            }
        }

        System.out.println(answer);
    }

    public static int getSafeDistance(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{r, c, 0});
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int dir = 0; dir < DR.length; ++dir) {
                int nextR = cur[0] + DR[dir];
                int nextC = cur[1] + DC[dir];
                int nextDist = cur[2] + 1;

                if (nextR < 0 ||
                    nextR >= map.length ||
                    nextC < 0 ||
                    nextC >= map[0].length ||
                    visited[nextR][nextC]) {
                    continue;
                }
                if (map[nextR][nextC] == 1) {
                    return nextDist;
                }

                if (map[nextR][nextC] == 0) {
                    visited[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC, nextDist});
                }
            }
        }
        return -1;
    }
}

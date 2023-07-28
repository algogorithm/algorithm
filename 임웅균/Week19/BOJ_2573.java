package Week19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 빙산의 위치를 저장한 큐
        Queue<Point> iceQ = new LinkedList<>();
        int[][] map = new int[N][M];
        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; m++) {
                map[n][m] = Integer.parseInt(st.nextToken());
                if (map[n][m] != 0) {
                    iceQ.add(new Point(n, m));
                }
            }
        }
        // 정답
        int answer = 1;
        while (!iceQ.isEmpty()) {
            int[][] tempMap = deepClone(map);
            // 빙산 녹이기
            int qSize = iceQ.size();
            for (int i = 0; i < qSize; i++) {
                Point p = iceQ.poll();
                for(int d = 0; d < 4; d++){
                    if(tempMap[p.r+dr[d]][p.c+dc[d]] == 0){
                        map[p.r][p.c]--;
                    }
                }
                if(map[p.r][p.c]<0){
                    map[p.r][p.c] = 0;
                }
                if (map[p.r][p.c] != 0) {
                    iceQ.add(p);
                }
            }

            // 빙산 개수 확인하기
            boolean[][] visited = new boolean[N][M];
            int icebergCount = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] > 0 && !visited[i][j]) {
                        bfs(i, j, map, visited);
                        icebergCount++;
                    }
                }
            }
            // 빙산이 두 덩어리 이상으로 분리되었으면 결과 출력
            if (icebergCount >= 2) {
                break;
            }
            answer++;
//            System.out.println();
//            for(int[] a : map){
//                System.out.println(Arrays.toString(a));
//            }
        }
        if(iceQ.isEmpty()){
            answer = 0;
        }
        System.out.println(answer);
    }

    private static void bfs(int row, int col, int[][] map, boolean[][] visited) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row, col));
        visited[row][col] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];
                if (isArrOut(nr, nc) || map[nr][nc] == 0 || visited[nr][nc]) {
                    continue;
                }
                queue.add(new Point(nr, nc));
                visited[nr][nc] = true;
            }
        }
    }

    private static boolean isArrOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= M;
    }
    private  static int[][] deepClone(int[][] map){
        int[][] temp = new int[N][M];
        for(int n = 0; n < N; n++){
            temp[n] = map[n].clone();
        }
        return temp;
    }
}

package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class silver1_1303_전쟁_전투 {
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int N, M, result_W = 0, result_B = 0;
    static char[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        visit = new boolean[M][N];

        for (int r = 0; r < M; r++) {
            String str = br.readLine();
            for (int c = 0; c < N; c++) {
                map[r][c] = str.charAt(c);
            }
        }

        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if(!visit[r][c]){
                    if(map[r][c] == 'W'){
                        result_W += bfs(r, c);
                    } else {
                        result_B += bfs(r, c);
                    }
                }
            }
        }

        System.out.println(result_W + " " + result_B);
    }

    private static int bfs(int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {r, c});
        visit[r][c] = true;
        int cnt = 1;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            for (int d = 0; d < dr.length; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if(nr >= 0 && nr < M && nc >= 0 && nc < N && !visit[nr][nc] && map[r][c] == map[nr][nc]){
                    visit[nr][nc] = true;
                    cnt++;
                    queue.offer(new int[] {nr, nc});
                }
            }
        }

        return cnt * cnt;
    }
}

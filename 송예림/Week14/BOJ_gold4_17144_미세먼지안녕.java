package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class gold4_17144_미세먼지안녕 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int R, C, T, result = 0;
    static ArrayList<int[]> airFresh = new ArrayList<>();
    static int[][] map, tmp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] == -1){
                    airFresh.add(new int[] {r, c});
                }
            }
        }

        for (int t = 0; t < T; t++) {
            //  미세먼지 확산
            addDust();
            // 공기청정기 작동
            turnAir();
        }

        countDust();
        System.out.println(result);
    }

    private static void print(int[][] map) {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println("==========");
    }

    private static void countDust() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(map[r][c] != -1){
                    result += map[r][c];
                }
            }
        }
    }

    private static void turnAir() {
        // 위쪽 공기 (반시계)
        int[] ddr = {0, -1, 0, 1};
        int[] ddc = {1, 0, -1, 0};
        int[] cur = {airFresh.get(0)[0], airFresh.get(0)[1]+1};
        int d = 0;
        map[cur[0]][cur[1]] = 0;

        while(true){
            int nr = cur[0] + ddr[d];
            int nc = cur[1] + ddc[d];
            if(nr == airFresh.get(0)[0] && nc == airFresh.get(0)[1]){
                break;
            }
            if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
                map[nr][nc] = tmp[cur[0]][cur[1]];
                cur[0] = nr;
                cur[1] = nc;
            } else {
                d++;
            }
        }

        // 아래쪽 공기 (시계)
        ddr = new int[] {0, 1, 0, -1};
        ddc = new int[] {1, 0, -1, 0};
        cur = new int[] {airFresh.get(1)[0], airFresh.get(1)[1]+1};
        d = 0;
        map[cur[0]][cur[1]] = 0;

        while(true){
            int nr = cur[0] + ddr[d];
            int nc = cur[1] + ddc[d];
            if(nr == airFresh.get(1)[0] && nc == airFresh.get(1)[1]){
                break;
            }
            if(nr >= 0 && nr < R && nc >= 0 && nc < C) {
                map[nr][nc] = tmp[cur[0]][cur[1]];
                cur[0] = nr;
                cur[1] = nc;
            } else {
                d++;
            }
        }
    }

    private static void addDust() {
        tmp = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if(map[r][c] > 0) {
                    int cnt = 0;
                    int minus = map[r][c] / 5;
                    for (int d = 0; d < dr.length; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr >= 0 && nr < R && nc >= 0 && nc < C && map[nr][nc] != -1){
                            tmp[nr][nc] += minus;
                            cnt++;
                        }
                    }
                    tmp[r][c] += map[r][c] - minus * cnt;
                } else if (map[r][c] == -1) {
                    tmp[r][c] = -1;
                }
            }
        }

        // 맵 복사
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                map[r][c] = tmp[r][c];
            }
        }
    }
}

package d202306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 미세먼지안녕 {
    static int r, c, t;
    static int map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        map = new int[r][c];
        ArrayList<int[]> al = new ArrayList<>();
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < c; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    al.add(new int[] { i, j });
                }
            }
        }
        // print(map);
        for (int i = 0; i < t; i++) {
            expand();
            upperwind(al.get(0)[0], al.get(0)[1]);
            underwind(al.get(1)[0], al.get(1)[1]);
        }
        int sum =0;
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                sum+=map[i][j];
            }
        }
        System.out.println(sum+2);
    }

    static void print(int[][] map) {
        for (int tmp[] : map) {
            for (int ta : tmp) {
                System.out.print(ta + " ");
            }
            System.out.println();
        }
    }

    static void expand() {
        int dr[] = { 0, -1, 0, 1 };
        int dc[] = { 1, 0, -1, 0 };
        int[][] tmp = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (map[i][j] != 0) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nrow = i + dr[d];
                        int ncol = j + dc[d];
                        if (nrow >= 0 && nrow < r && ncol >= 0 && ncol < c && map[nrow][ncol] != -1) {
                            tmp[nrow][ncol] += map[i][j] / 5;
                            cnt++;
                        }
                    }
                    tmp[i][j] += map[i][j] - map[i][j] / 5 * cnt;
                }
            }
        }
        for (int i = 0; i < r; i++) {
            map[i] = tmp[i].clone();
        }
    }

    static void upperwind(int row, int col) {
        // 위 우 아래 좌
        int dr[] = { 0, -1, 0, 1 };
        int dc[] = { 1, 0, -1, 0 };
        int cr = row;
        int cc = col + 1;
        int dir = 0;
        int[][] tmp = new int[r][c];
        for (int i = 0; i < r; i++) {
            tmp[i] = map[i].clone();
        }
        while (dir != 4) {
            int nrow = cr + dr[dir];
            int ncol = cc + dc[dir];

            if (nrow >= 0 && nrow <= row && ncol >= 0 && ncol < c) {
                if (tmp[nrow][ncol] == -1) {
                    break;
                }
                tmp[nrow][ncol] = map[cr][cc];
                cr = nrow;
                cc = ncol;
            } else {
                dir++;
            }
        }
        tmp[row][col+1]=0;
        for (int i = 0; i < r; i++) {
            map[i] = tmp[i].clone();
        }
    }

    static void underwind(int row, int col) {
        int dr[] = { 0, 1, 0, -1 };
        int dc[] = { 1, 0, -1, 0 };
        int cr = row;
        int cc = col + 1;
        int dir = 0;
        int[][] tmp = new int[r][c];
        for (int i = 0; i < r; i++) {
            tmp[i] = map[i].clone();
        }
        while (dir != 4) {
            int nrow = cr + dr[dir];
            int ncol = cc + dc[dir];
            if (nrow >= row && nrow < r && ncol >= 0 && ncol < c) {
                if (tmp[nrow][ncol] == -1) {
                    break;
                }
                tmp[nrow][ncol] = map[cr][cc];
                cr = nrow;
                cc = ncol;
            } else {
                dir++;
            }
        }
        tmp[row][col+1]=0;
        for (int i = 0; i < r; i++) {
            map[i] = tmp[i].clone();
        }
    }
}

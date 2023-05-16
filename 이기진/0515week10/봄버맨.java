package d202305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 봄버맨 {

    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;

        }
    }

    static int r, c, n;
    static char[][] map;
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static ArrayList<Point> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        list = new ArrayList<>();

        for (int i = 0; i < r; i++) {
            String s = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == 'O') {
                    list.add(new Point(i, j));
                }
            }
        }
        if (n == 1) {
            print(map);
            return;
        }
        if (n % 2 == 0) {
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    map[i][j] = 'O';
                }
            }
            print(map);
            return;
        }

        n = n % 4 + 4;
        while (n >= 3) {

            list = new ArrayList<>();
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (map[i][j] == 'O') {
                        list.add(new Point(i, j));
                    }
                }
            }
            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    map[i][j] = 'O';
                }
            }

            for (int i = 0; i < list.size(); i++) {
                map[list.get(i).r][list.get(i).c] = '.';
                for (int d = 0; d < 4; d++) {
                    int nr = list.get(i).r + dr[d];
                    int nc = list.get(i).c + dc[d];
                    if (nr >= 0 && nr < r && nc >= 0 && nc < c) {
                        map[nr][nc] = '.';
                    }
                }
            }
            n -= 2;

        }
        print(map);

    }

    private static void print(char[][] map) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }
}
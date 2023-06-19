package d202306;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 사다리조작 {


    private static int n;
    private static int m;
    private static int h;
    private static int answer = -1;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int [n+2][m+2];

        for (int i = 0; i < m; i++) {
             st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
        }

        for (int i = 0; i <= 3; i++) {
            dfs(1, 0, i);
            if (answer != -1) {
                break;
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int row, int count, int limit) {
        if (count == limit) {
            if (check()) {
                answer = limit;
            }
            return;
        }

        for (int i = row; i <= h; i++) {
            for (int j = 1; j < n; j++) {
                if (map[i][j] == 0 && map[i][j - 1] == 0 && map[i][j + 1] == 0) {
                    map[i][j] = 1;
                    dfs(i, count + 1, limit);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static boolean check() {
        for (int i = 1; i <= n; i++) {
            int current = i;
            for (int j = 1; j <= h; j++) {
                if (map[j][current] == 1) {
                    current++;
                } else if (map[j][current - 1] == 1) {
                    current--;
                }
            }
            if (current != i) {
                return false;
            }
        }
        return true;
    }
}
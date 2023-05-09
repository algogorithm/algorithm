package d202305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 여왕벌 {
    static int n;
    static int grow[][];
    static int map[][];
    static int dr[] = { 0, -1, -1 };// 왼쪽(L), 왼쪽 위(D), 위쪽(U)
    static int dc[] = { -1, 0, -1 };

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        int day = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], 1);
        }
        //printmap(map);
        grow = new int[day][3];
        for (int i = 0; i < day; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 3; j++) {
                grow[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < day; i++) {
            growteduri(i);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(map[i][j] + " ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

	/* 실패
	private static void check(int row, int col, int[][] g) {
		int ans = -1;
		//System.out.println("row"+row+"col"+col+"------------");
		//printmap(g);
		for (int i = 0; i < 3; i++) {
			int nrow = row + dr[i];
			int ncol = col + dc[i];

			if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < n) {
				ans = Math.max(ans, g[nrow][ncol]);
			}
			g[row][col]=ans;
		}
	}
	*/

    private static void check() {
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < n; j++)
                map[i][j] = Math.max(map[i-1][j], Math.max(map[i-1][j-1], map[i][j-1]));
        }
    }
    private static void growteduri(int current) {

        // 왼쪽 아래부터 시작
        int cnt = 0;
        for (int i = n - 1; i >= 0; i--) {
            map[i][0] += getheight(current, cnt);
            cnt++;
        }
        for (int i = 1; i < n; i++) {
            map[0][i] += getheight(current, cnt);
            cnt++;
        }

        //System.out.println("테두리 --------------");
        //printmap(g);
		/*
		for (int a = 1; a < n; a++) {
			for (int b = 1; b < n; b++) {
				check(a, b, g);
			}
		}*/
        check();
        //System.out.println("하루뒤 --------------");
        //printmap(g);
    }

    private static int getheight(int current, int cnt) {
        for (int i = 0; i < grow[0].length; i++) {
            if (grow[current][i] > 0) {
                grow[current][i]--;
                //System.out.println(Arrays.toString(grow[current]));
                return i;
            }
        }
        return 0;
    }

    static void printmap(int[][] map) {
        for (int tmp[] : map) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}

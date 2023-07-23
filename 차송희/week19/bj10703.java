import java.io.*;
import java.util.*;

public class BJ10703 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][s];

        for (int i = 0; i < r; i++) {
            String row = br.readLine();
            map[i] = row.toCharArray();
        }

        int downCnt = 100000;

        for (int j = 0; j < s; j++) {
            for (int i = r - 1; i >= 0; i--) {
                if (map[i][j] == 'X') {
                    downCnt = Math.min(downCnt, getCnt(map, i, j));
                    break;
                }
            }
        }

        for (int i = r - 1; i >= 0; i--) {
            for (int j = 0; j < s; j++) {
                if (map[i][j] != 'X')
                    continue;

                map[i][j] = '.';
                map[i + downCnt][j] = 'X';
            }
        }

        for (int i = 0; i < r; i++) {
            System.out.println(map[i]);
        }

    }

    static int getCnt(char[][] map, int row, int col) {

        int cnt = 0;
        for (int i = row + 1; i < map.length; i++) {
            if (map[i][col] == '.')
                cnt++;

            else
                break;
        }

        return cnt;
    }
}

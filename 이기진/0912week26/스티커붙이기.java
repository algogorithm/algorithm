package d202309;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스티커붙이기 {
    static int map[][], n, m, K, result;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());
            int strow = Integer.parseInt(st.nextToken());
            int stcol = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[strow][stcol];
            for (int j = 0; j < strow; j++) {
                st = new StringTokenizer(bf.readLine());
                for (int k = 0; k < stcol; k++) {
                    sticker[j][k] = Integer.parseInt(st.nextToken());
                }
            }
            for (int rt = 0; rt < 4; rt++) {
                if (checkattach(sticker))
                    break;
                sticker = rotate(sticker);
            }
        }
        System.out.println(result);
    }

    static boolean checkattach(int[][] sticker) {
        for (int i = 0; i < n - sticker.length + 1; i++)
            for (int j = 0; j < m - sticker[0].length + 1; j++)
                if (possible(sticker, i, j)) {

                    for (int a = 0; a < sticker.length; a++)
                        for (int b = 0; b < sticker[0].length; b++)
                            if (sticker[a][b] == 1) {
                                map[i + a][j + b] = sticker[a][b];
                                result++;
                            }


                    return true;
                }

        return false;
    }

    static boolean possible(int[][] sticker, int row, int col) {
        for (int i = 0; i < sticker.length; i++)
            for (int j = 0; j < sticker[0].length; j++)
                if (sticker[i][j] == 1 && map[row + i][col + j] == 1)
                    return false;
        return true;
    }

    static int[][] rotate(int[][] sticker) { 
        int[][] newSticker = new int[sticker[0].length][sticker.length];

        for (int i = 0; i < sticker.length; i++)
            for (int j = 0; j < sticker[0].length; j++)
                newSticker[j][sticker.length - i - 1] = sticker[i][j];

        return newSticker;
    }



}
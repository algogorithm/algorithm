package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class 주사위쌓기 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        int[][] dice = new int[N][6];
        for (int tc = 0; tc < N; tc++) {
            String[] str = bf.readLine().split(" ");
            for (int i = 0; i < 6; i++) {
                dice[tc][i] = Integer.parseInt(str[i]);
            }
        }

        int up = 0; // 각 주사위의 맨 위 숫자를 저장할 배열
        int down = 0;
        int result=0;
        int sum = 0;
        for (int j = 0; j < 6; j++) {
            up = dice[0][j];
            if (j == 1 || j == 2)
                down = dice[0][j + 2];
            else if (j == 3 || j == 4)
                down = dice[0][j - 2];
            else if (j == 0)
                down = dice[0][5];
            else
                down = dice[0][0];
            if((down+up)==11) sum=4;
            else if(up==6 || down==6) sum=5;
            else sum=6;
            for (int i=1 ; i<N ; i++) {
                for (int k = 0; k < 6; k++) {
                    if (dice[i][k] == up) {
                        down = dice[i][k];
                        if (k == 1 || k == 2)
                            up = dice[i][k + 2];
                        else if (k == 3 || k == 4)
                            up = dice[i][k - 2];
                        else if (k == 0)
                            up = dice[i][5];
                        else
                            up = dice[i][0];
                        break;
                    }
                }
                int q = 0;
                int max = 0;
                while (q < 6) { //옆면 중 가장 큰 수 찾기
                    if (dice[i][q] != up && dice[i][q] != down && max < dice[i][q]) {
                        max = dice[i][q];
                    }
                    q++;
                }
                sum += max;
            }
            result = Math.max(result, sum);
        }
        System.out.println(result);
    }
}
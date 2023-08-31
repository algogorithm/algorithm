package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gold5_2116_주사위쌓기 {
    static int N, max = 0;
    static int[][] dice;
    static int[] ftf = {5, 3, 4, 1, 2, 0};
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dice = new int[N][6];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 맨 아래 주사위 아랫면, 옆면 최댓값 구해서 재귀 돌리기
        for (int i = 0; i < 6; i++) {
            int sideMax = 0;
            for (int j = 0; j < 6; j++) {
                if(i != j && ftf[i] != j){
                    sideMax = Math.max(sideMax, dice[0][j]);
                }
            }
            nextDice(dice[0][ftf[i]], sideMax, 1);
        }

        System.out.println(max);
    }

    private static void nextDice(int bottom, int tmpMax, int idx) {
        if(idx == N){
            max = Math.max(max, tmpMax);
            return;
        }

        int bottomIdx = 0;
        for (int i = 0; i < 6; i++) {
            if(dice[idx][i] == bottom){
                bottomIdx = i;
                break;
            }
        }

        int sideMax = 0;
        for (int i = 0; i < 6; i++) {
            if(i != bottomIdx && ftf[bottomIdx] != i){
                sideMax = Math.max(sideMax, dice[idx][i]);
            }
        }
        nextDice(dice[idx][ftf[bottomIdx]], tmpMax + sideMax, idx+1);
    }
}

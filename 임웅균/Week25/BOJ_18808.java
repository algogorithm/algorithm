package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18808 {
    static int N,M,K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 세로길이
        N = Integer.parseInt(st.nextToken());
        // 가로길이
        M = Integer.parseInt(st.nextToken());
        // 스티커 개수
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];
            for(int r = 0; r < R; r++){
                st = new StringTokenizer(br.readLine());
                for(int c = 0; c < C; c++){
                    sticker[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            // 스티커 붙였는지 확인하는 변수
            boolean isSticked = false;
            int rotationCnt = 0;

            while(rotationCnt < 4){
                // 스티커가 붙었다면
                if(isSticked){
                    break;
                }

                // 책의 모든 위치를 검사
                for (int r = 0; r <= N - sticker.length; r++) {
                    for (int c = 0; c <= M - sticker[0].length; c++) {
                        if (checking(sticker, r, c)) {
                            stick(sticker, r, c);
                            isSticked = true;
                            break;
                        }
                    }
                    if (isSticked) {
                        break;
                    }
                }
                // 스티커 90도 회전
                sticker = rotate(sticker);
                rotationCnt++;

            }
        }
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                answer += map[r][c];
            }
        }
        System.out.println(answer);

    }

    // 스티커 회전 함수
    private static int[][] rotate(int[][] sticker) {
        int R = sticker.length;
        int C = sticker[0].length;
        int[][] result = new int[C][R];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                result[c][R - r - 1] = sticker[r][c];
            }
        }
        return result;
    }

    // 스티커 붙이는 함수
    private static void stick(int[][] sticker, int R, int C) {
        for(int r = 0; r < sticker.length; r++){
            for(int c = 0; c < sticker[0].length; c++){
                map[R+r][C+c] += sticker[r][c];
            }
        }
    }

    // 스티커를 붙일 수 있는지 확인하는 함수
    private static boolean checking(int[][] sticker, int R, int C) {
        for(int r = 0; r < sticker.length; r++){
            for(int c = 0; c < sticker[0].length; c++){
                if(sticker[r][c] + map[R + r][C + c] > 1){
                    return false;
                }
            }
        }
        return true;
    }

}

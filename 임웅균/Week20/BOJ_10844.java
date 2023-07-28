package Week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {
    static final int MOD = 1000000000;
    public static void main(String[] args) throws IOException {
        // DP 저장법을 확인한 문제.
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        // 길이가 i이고, 마지막 자릿수가 j인 계단 수의 개수를 저장
        int[][] arr = new int[N+1][10];
        long answer = 0;
        // 1의자리수 초기화
        // 0으로 시작하는 수는 계단수가 아니므로 제외
        for(int i = 1; i < 10; i++){
            arr[1][i] = 1;
        }
        // 나머지 초기화
        for(int i = 2; i <= N; i++){
            for(int j = 0; j < 10; j++){
                // 계단수는 마지막 숫자기준 -1 또는 +1만 가능하다.
                // 0은 -1이 불가능하기에 +1만 가져온다.
//                if(j == 0){
//                    arr[i][j] = arr[i-1][j+1] % MOD;
//                }
//                // 9는 +1이 불가능하기에 -1만 가져온다.
//                else if(j == 9){
//                    arr[i][j] = arr[i-1][j-1] % MOD;
//                }
//                // 0과 9를 제외한 나머지는 -1과 +1 모두 가능하기에 모두 가져온다.
//                else{
//                    arr[i][j] = (arr[i - 1][j - 1] + arr[i - 1][j + 1]) % MOD;
//                }
                arr[i][j] = (
                        (j == 0 ? 0 : arr[i - 1][j - 1]) +
                        (j == 9 ? 0 : arr[i - 1][j + 1])
                ) % MOD;

            }
        }
        for(int i = 0; i <= 9; i++){
            answer += arr[N][i];
        }
        System.out.println(answer % MOD);
    }

}

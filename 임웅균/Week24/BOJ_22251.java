package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_22251 {
    /*
        0
      1   2
        3
      4   5
        6
     */
    static final boolean[][] DISPLAY_NUMS = {
            {true, true, true, false, true, true, true}, // 0
            {false, false, true, false, false, true, false}, // 1
            {true, false, true, true, true, false, true}, // 2
            {true, false, true, true, false, true, true}, // 3
            {false, true, true, true, false, true, false}, // 4
            {true, true, false, true, false, true, true}, // 5
            {true, true, false, true, true, true, true}, // 6
            {true, false, true, false, false, true, false}, // 7
            {true, true, true, true, true, true, true}, // 8
            {true, true, true, true, false, true, true}, // 9
    };

    static int[][] ledSwitchCnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // N층까지 이용 가능한 엘리베이터
        int N = Integer.parseInt(st.nextToken());
        // 엘리베이터 디스플레이에 표시되는 숫자의 자릿수
        int K = Integer.parseInt(st.nextToken());
        // 디스플레이에서 반전시킬 수 있는 최대 LED 개수
        int P = Integer.parseInt(st.nextToken());
        // 현재 엘리베이터가 실제로 멈춰있는 층
        int X = Integer.parseInt(st.nextToken());
        // 한 수가 다른 수로 바뀔때 몇개의 LED를 변경하면 되는지 저장하는 배열
        ledSwitchCnt = new int[10][10];
        // 0부터 9까지 한번 해보기
        for(int n = 0; n < 10; n++){
            for(int m = 0; m < 10; m++){
                ledSwitchCnt[n][m] = findLedSwitchCnt(n, m);
                ledSwitchCnt[m][n] = ledSwitchCnt[n][m];
            }
        }
        // 이제 1부터 N까지 P개의 스위치 변경을 통해 수를 변경할 수 있는지 확인
        String stringN = "";
        int result = 0;
        String stringX = fitN(K, X);
        for(int n = 1; n <= N; n++){
            // 현재층은 제외
            if(n == X) continue;
            // 자리수(K) 맞춰주기
            stringN = fitN(K, n);
            // P번의 변경을 통해 X가 stringN으로 바뀔 수 있는지 확인
            result += xToStringN(stringX, stringN, P) ? 1 : 0;
        }
        System.out.println(result);
    }

    private static boolean xToStringN(String x, String stringN, int P) {
        int cnt = 0;
        for(int i = 0; i < x.length(); i++){
            int currXN = x.charAt(i) - '0';
            int currStringNN = stringN.charAt(i) - '0';
            cnt += ledSwitchCnt[currXN][currStringNN];
            if(cnt > P) return false;
        }
        return cnt <= P;
    }

    private static String fitN(int k, int n) {
        StringBuilder sb = new StringBuilder();
        String stringN = Integer.toString(n);
        for(int i = 0; i < k-stringN.length(); i++){
            sb.append("0");
        }
        sb.append(n);
        return sb.toString();
    }

    private static int findLedSwitchCnt(int n, int m){
        int result = 0;
        // n이 m이되려면 몇개의 led를 움직여야하는지 판단.
        for(int i = 0; i < DISPLAY_NUMS[0].length; i++){
            result += DISPLAY_NUMS[n][i] != DISPLAY_NUMS[m][i] ? 1 : 0;
        }
        return result;
    }
}
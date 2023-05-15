package d202305;

import java.io.*;

public class 전구와스위치 {
    private static int[] resultLight;
    private static int N;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] light1 = new int[N]; // 첫번째 전구를 누르지 않은 버전
        int[] light2 = new int[N]; // 첫번째 전구를 누른 버전
        resultLight = new int[N];

        String lightInput = br.readLine();
        String resultInput = br.readLine();
        for (int i = 0; i < N; i++) {
            light1[i] = lightInput.charAt(i) - '0';
            light2[i] = lightInput.charAt(i) - '0';
            resultLight[i] = resultInput.charAt(i) - '0';
        }

        moveSwitch(1, light1, 0);

        push(0, light2);
        moveSwitch(1, light2, 1);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void moveSwitch(int idx, int[] light, int pushCount) {
        if (idx == N) {
            if (light[idx - 1] == resultLight[idx - 1]) {
                answer = Math.min(answer, pushCount);
            }

            return;
        }
        if (light[idx - 1] != resultLight[idx - 1]) {
            push(idx, light);
            moveSwitch(idx + 1, light, pushCount + 1);
        } else {
            moveSwitch(idx + 1, light, pushCount);
        }
    }

    private static void push(int idx, int[] light) {
        if (idx - 1 >= 0) {
            light[idx - 1] = changelight(light[idx - 1]);
        }

        light[idx] = changelight(light[idx]);

        if (idx + 1 < N) {
            light[idx + 1] = changelight(light[idx + 1]);
        }
    }

    // 전구 상태 변환
    private static int changelight(int light) {
        if(light==1){
            return 0;
        }
        else{
            return 1;
        }
    }
}
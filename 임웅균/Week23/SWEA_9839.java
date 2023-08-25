package Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SWEA_9839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            System.out.printf("#%d ", t);
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
            Collections.sort(list, Collections.reverseOrder());
            int maxValue = -1;
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    int mulValue = list.get(i) * list.get(j);
                    if (checkSequence(mulValue)) {
                        maxValue = Math.max(maxValue, mulValue);
                    }
                }
            }
            System.out.println(maxValue);
        }
    }

    private static boolean checkSequence(int num) {
        int prevDigit = num % 10;
        num /= 10;
        while (num > 0) {
            int curDigit = num % 10;
            if (curDigit != prevDigit - 1) {
                return false;
            }
            prevDigit = curDigit;
            num /= 10;
        }
        return true;
    }
}
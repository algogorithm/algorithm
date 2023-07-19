package d202307;

import java.io.*;
import java.util.*;

public class 비슷한단어 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        arr = new String[N];
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        String str1 = arr[0], str2 = arr[1];
        int level = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int calc = calc(arr[i], arr[j]);
                if (level < calc) {
                    level = calc;
                    str1 = arr[i];
                    str2 = arr[j];
                }
            }
        }

        System.out.println(str1);
        System.out.println(str2);
    }

    private static int calc(String str1, String str2) {
        int cnt = 0;
        int len = Math.min(str1.length(), str2.length());
        for (int i = 0; i < len; i++) {
            if (str1.charAt(i) == str2.charAt(i)) {
                cnt++;
            } else {
                break;
            }
        }
        return cnt;
    }
}
package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class gold4_5052_전화번호목록 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        L:for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            String[] arr = new String[n];
            for (int i = 0; i < n; i++) {
                arr[i] = br.readLine();
            }

            Arrays.sort(arr);

            for (int i = 1; i < arr.length; i++) {
                if(arr[i].startsWith(arr[i-1])){
                    System.out.println("NO");
                    continue L;
                }
            }
            System.out.println("YES");
        }
    }
}

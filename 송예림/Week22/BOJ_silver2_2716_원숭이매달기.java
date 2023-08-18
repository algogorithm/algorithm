package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class silver2_2716_원숭이매달기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            if(str.length() == 0){
                sb.append(1 + "\n");
                continue;
            }

//            Stack<Character> stk = new Stack<>();
            int cnt = 0, result = 0;
            for (int j = 0; j < str.length(); j++) {
                if(str.charAt(j) == ']'){
                    cnt--;
                } else {
                    cnt++;
                    result = Math.max(result, cnt);
                }
            }

            sb.append((int)Math.pow(2, result) + "\n");
        }

        System.out.println(sb);
    }
}

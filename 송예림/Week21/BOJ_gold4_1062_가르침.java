package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class gold4_1062_가르침 {
    static int N, K, result = 0;
    static boolean[] alpha = new boolean[26];
    static String[] word;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // anta tica 적어도 5개 이상이여야함
        if(K < 5){
            System.out.println(0);
            return;
        } else if(K == 26){
            System.out.println(N);
            return;
        }

        word = new String[N];
        for (int i = 0; i < N; i++) {
            word[i] = br.readLine();
        }

        // a c i n t
        alpha['a'-'a'] = alpha['c'-'a'] = alpha['i'-'a'] = alpha['n'-'a'] = alpha['t'-'a'] = true;

        comb(1, 0);

        System.out.println(result);
    }

    private static void comb(int idx, int kl) {
        // 조합 다 만들면 개수 체크
        if(kl == K-5){
            int cnt = 0;
            L:for (int i = 0; i < word.length; i++) {
                // 앞, 뒤 단어 똑같음
                for (int j = 4; j < word[i].length()-4; j++) {
                    if(!alpha[word[i].charAt(j)-'a']){
                        continue L;
                    }
                }
                cnt++;
            }

            result = Math.max(result, cnt);
            return;
        }

        for (int i = idx; i < alpha.length; i++) {
            if(!alpha[i]){
                alpha[i] = true;
                comb(i, kl+1);
                alpha[i] = false;
            }
        }
    }
}

//가르침

import java.util.Scanner;

public class BJ1062 {
    static int N, K;
    static boolean[] learned = new boolean[26];
    static String[] words;
    static int maxRead = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
        }

        if (K < 5) {
            System.out.println(0);
            return;
        }

        learned['a' - 'a'] = true;
        learned['n' - 'a'] = true;
        learned['t' - 'a'] = true;
        learned['i' - 'a'] = true;
        learned['c' - 'a'] = true;

        solve(0, 5);

        System.out.println(maxRead);
    }

    static void solve(int start, int count) {
        if (count == K) {
            int readCount = 0;
            for (String word : words) {
                boolean canRead = true;
                for (char c : word.toCharArray()) {
                    if (!learned[c - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) {
                    readCount++;
                }
            }
            maxRead = Math.max(maxRead, readCount);
            return;
        }

        for (int i = start; i < 26; i++) {
            if (!learned[i]) {
                learned[i] = true;
                solve(i + 1, count + 1);
                learned[i] = false;
            }
        }
    }

}

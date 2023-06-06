package d202306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 그르다김가놈 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());// 김밥의 개수
        int K = Integer.parseInt(st.nextToken());// 꼬다리의 길이
        int M = Integer.parseInt(st.nextToken());// 김밥조각의 최소개수

        ArrayList<Integer> kim = new ArrayList<>();
        int left = 1;
        int right = 0;
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(bf.readLine());
            right=Math.max(tmp, right);
            if (tmp <= K)
                continue;
            else if (tmp < 2 * K) {
                kim.add(tmp - K);
            } else {
                kim.add(tmp - 2 * K);
            }
        }

        int middle = 0;
        int ans = 0;
        int max=right;
        while (left <= right) {
            middle = (left + right) / 2;
            int tmpm = 0;
            for (int i = 0; i < kim.size(); i++) {
                tmpm += kim.get(i) / middle;
            }

            if (tmpm < M) {
                right = middle - 1;

            } else if (tmpm >= M) {

                left = middle + 1;
                ans = Math.max(ans, middle);
            }

        }
        if (ans==0) {
            System.out.println("-1");
        } else {
            System.out.println(ans);
        }

    }

}
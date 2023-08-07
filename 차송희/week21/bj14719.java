//빗물

import java.util.Scanner;

public class BJ14719 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        int[] blocks = new int[W];

        for (int i = 0; i < W; i++) {
            blocks[i] = sc.nextInt();
        }

        int answer = 0;
        for (int i = 1; i < W - 1; i++) {
            int leftMax = 0;
            for (int j = 0; j < i; j++) {
                leftMax = Math.max(leftMax, blocks[j]);
            }

            int rightMax = 0;
            for (int j = i + 1; j < W; j++) {
                rightMax = Math.max(rightMax, blocks[j]);
            }

            int minMax = Math.min(leftMax, rightMax);
            if (minMax > blocks[i]) {
                answer += minMax - blocks[i];
            }
        }

        System.out.println(answer);
    }

}

import java.io.*;
import java.util.*;

public class BOJ1027_고층_건물 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] height = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < n; ++i) {
            height[i] = Integer.parseInt(st.nextToken());
        }

        int answer = 0;
        for (int i = 0; i < n; ++i) {
            int localAnswer = 0;

            if (i < n - 1) {
                ++localAnswer;
                double maxInclination = (double) height[i + 1] - (double) height[i];
                for (int j = i + 2; j < n; ++j) {
                    double inclination = (double) (height[j] - height[i]) / (double) (j - i);
                    if (maxInclination < inclination) {
                        maxInclination = inclination;
                        ++localAnswer;
                    }
                }
            }

            if (i > 0) {
                ++localAnswer;
                double minInclination = (double) height[i] - (double) height[i - 1];
                for (int j = i - 2; j >= 0; --j) {
                    double inclination = (double) (height[i] - height[j]) / (double) (i - j);
                    if (minInclination > inclination) {
                        minInclination = inclination;
                        ++localAnswer;
                    }
                }
            }
            answer = Math.max(localAnswer, answer);
        }

        System.out.println(answer);
    }
}

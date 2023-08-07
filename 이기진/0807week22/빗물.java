package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 빗물 {

    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        arr = new int[w];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < w; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 1; i < w; i++) {
            int current = arr[i];
            int left = leftMaxHeight(i);
            int right = rightMaxHeight(i);

            if (current < left && current < right) {
                sum += Math.min(left, right) - current;
            }
        }

        System.out.println(sum);
    }

    private static int rightMaxHeight(int index) {
        int max = 0;
        for (int i = arr.length - 1; i >= index; i--) {
            max = Math.max(max, arr[i]);
        }

        return max;
    }

    private static int leftMaxHeight(int end) {
        int max = 0;
        for (int i = 0; i < end; i++) {
            max = Math.max(max, arr[i]);
        }

        return max;
    }
}
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1253_좋다 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] num = new int[N];
        for(int i = 0; i < N; ++i) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);

        int answer = 0;

        for(int i = 0; i < num.length; ++i) {
            int left = 0;
            int right = num.length - 1;

            while(left < right) {
                if(left == i) {
                    ++left;
                    continue;
                }
                if(right == i) {
                    --right;
                    continue;
                }

                int sum = num[left] + num[right];

                if(sum == num[i]) {
                    ++answer;
                    break;
                }
                else if(sum > num[i]) --right;
                else ++left;
            }
        }

        System.out.println(answer);
    }
}

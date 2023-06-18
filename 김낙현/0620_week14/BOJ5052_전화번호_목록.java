import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ5052_전화번호_목록 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());

        tc:
        for(int tc = 1; tc <= t; ++tc) {
            int n = Integer.parseInt(br.readLine());
            String[] numbers = new String[n];

            for(int i = 0; i < n; ++i) {
                numbers[i] = br.readLine();
            }

            Arrays.sort(numbers);

            for(int i = 0; i < n - 1; ++i) {
                if(numbers[i].length() >= numbers[i+1].length()) continue;

                if(numbers[i+1].regionMatches(0, numbers[i], 0, numbers[i].length())) {
                    System.out.println("NO");
                    continue tc;
                }
            }
            System.out.println("YES");
        }
    }
}

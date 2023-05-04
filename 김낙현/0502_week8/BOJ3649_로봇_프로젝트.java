import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ3649_로봇_프로젝트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        NextCase:
        while (true) {
            String strX = br.readLine();
            if(strX == null || strX.isEmpty()) break;

            int x = Integer.parseInt(strX) * 10_000_000;
            int n = Integer.parseInt(br.readLine());

            int[] lego = new int[n];

            for (int i = 0; i < n; ++i) {
                lego[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(lego);

            int left = 0;
            int right = lego.length - 1;

            while(left < right) {
                int sum = lego[left] + lego[right];
                if(sum > x) {
                    --right;
                } else if(sum < x) {
                    ++left;
                } else {
                    System.out.printf("yes %d %d\n", lego[left], lego[right]);
                    continue NextCase;
                }
            }
            System.out.println("danger");
        }
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1722_순열의_순서 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int problemType = Integer.parseInt(st.nextToken());

        long[] fact = factorial(n);
        switch (problemType) {
            case 1:
                long k = Long.parseLong(st.nextToken());
                System.out.println(getKthPermutation(n, k, fact));
                break;
            case 2:
                int[] permutation = new int[n];
                for (int i = 0; i < n; ++i) {
                    permutation[i] = Integer.parseInt(st.nextToken());
                }
                System.out.println(getOrderOf(permutation, fact));
                break;
            default:
                System.out.println("Something Wrong!");
        }
    }

    public static String getKthPermutation(int n, long k, long[] fact) {
        StringBuilder answer = new StringBuilder();

        int used = 0;
        for (int i = 0; i < n; ++i) {
            for (int num = 1; num <= n; ++num) {
                if((used & (1 << num)) != 0) continue;

                if(k > fact[n - 1 - i]) {
                    k -= fact[n - 1 - i];
                } else {
                    answer.append(num).append(" ");
                    used |= (1 << num);
                    break;
                }
            }
        }

        return answer.toString();
    }

    public static String getOrderOf(int[] permutation, long[] fact) {
        int used = 0;
        long order = 1;
        for (int i = 0; i < permutation.length; ++i) {
            order += (long) getNumberCntOfLowerThan(permutation[i], used) * fact[permutation.length - 1 - i];
            used |= (1 << permutation[i]);
        }
        return String.valueOf(order);
    }

    public static int getNumberCntOfLowerThan(int num, int used) {
        int cnt = 0;
        for (int i = 1; i < num; ++i) {
            if ((used & (1 << i)) == 0) ++cnt;
        }
        return cnt;
    }

    public static long[] factorial(int n) {
        long[] fact = new long[n + 1];
        fact[0] = 1;
        fact[1] = 1;
        for (int i = 1; i <= n; ++i) {
            fact[i] = (long) i * fact[i - 1];
        }
        return fact;
    }
}

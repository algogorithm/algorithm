import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 무한문자열 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        for (int i = 1; i <= num; i++) {
            String answer = "no";
            String[] str = br.readLine().split(" ");
            String a = str[0];
            String b = str[1];

            int gcd = gcd(a.length(), b.length());
            int lcm = (a.length() * b.length()) / gcd;
            String A = a;
            String B = b;
            while (A.length() != lcm) {
                A += a;
            }
            while (B.length() != lcm) {
                B += b;
            }

            if (A.equals(B)) {
                answer = "yes";
            }

            sb.append("#" + i + " " + answer + "\n");
        }

        System.out.print(sb);
    }

    // 최대 공약수
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}

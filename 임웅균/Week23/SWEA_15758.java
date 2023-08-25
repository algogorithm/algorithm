package Week23;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_15758 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int t = 0; t < T; t++){
            System.out.printf("#%d ",(t+1));
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();
            if(a.length() == b.length()){
                System.out.println(a.equals(b) ? "yes": "no");
                continue;
            }
            int lcmLength = lcm(a.length(), b.length());
            String extendedA = extendString(a, lcmLength);
            String extendedB = extendString(b, lcmLength);

            System.out.println(extendedA.equals(extendedB) ? "yes" : "no");
        }
    }
    // 문자열 s를 길이가 최소공배수 n이 될 때까지 반복
    private static String extendString(String s, int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i % s.length()));
        }
        return sb.toString();
    }

    // a,b의 최소공배수 
    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    // a,b의 최대공약수
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class D3_15758_무한문자열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int test = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= test; tc++) {
            st = new StringTokenizer(br.readLine());
            String S = st.nextToken();
            String T = st.nextToken();

            sb.append("#"+tc+" ");

            if(S.equals(T)){
                sb.append("yes\n");
                continue;
            }

            // 최소공배수 = (n1*n2)/최대공약수
            // 4 6 -> 12
            int max = 0;
            for (int i = 1; i <= S.length() && i <= T.length(); i++) {
                if (S.length()%i == 0 && T.length()%i == 0){
                    max = i;
                }
            }

            max = (S.length() * T.length()) / max;

            String rS = S, rT = T;
            while (rS.length() != max){
                rS += S;
            }

            while (rT.length() != max){
                rT += T;
            }

            if(rS.equals(rT)){
                sb.append("yes\n");
            } else {
                sb.append("no\n");
            }
        }

        System.out.println(sb);
    }
}

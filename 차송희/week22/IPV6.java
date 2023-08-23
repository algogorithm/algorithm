import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj3107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String IPV6 = br.readLine().trim();

        int groups = 0;
        for (int i = 0; i < IPV6.length();) {
            if (IPV6.charAt(i) != ':') {
                groups++;
                while (i < IPV6.length() && IPV6.charAt(i) != ':')
                    i++;
            } else {
                i++;
            }
        }

        int doubleColon = IPV6.indexOf("::");
        if (doubleColon != -1) {
            StringBuilder snippet = new StringBuilder();

            if (doubleColon != 0) {
                snippet.append(':');
            }
            for (int i = 0; i < 8 - groups; ++i) {
                snippet.append("0000");
                if (i + 1 != 8 - groups)
                    snippet.append(':');
            }

            if (doubleColon != IPV6.length() - 2) {
                snippet.append(':');
            }

            IPV6 = IPV6.substring(0, doubleColon) + snippet.toString() + IPV6.substring(doubleColon + 2);
        }

        StringBuilder out = new StringBuilder();
        boolean first = true;
        String[] tokens = IPV6.split(":");
        for (String token : tokens) {
            if (token.length() != 4) {
                token = "0".repeat(4 - token.length()) + token;
            }

            if (first) {
                first = false;
            } else {
                out.append(":");
            }

            out.append(token);
        }
        System.out.println(out);
    }
}

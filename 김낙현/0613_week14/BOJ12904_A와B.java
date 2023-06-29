import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ12904_A와B {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        boolean reversed = false;
        int left = 0;
        int right = t.length() - 1;
        while (s.length() < right - left + 1) {
            char lastChar = (reversed) ? t.charAt(left++) : t.charAt(right--);
            reversed ^= (lastChar == 'B');
        }

        System.out.println(((reversed) ? new StringBuilder(t.substring(left, right + 1)).reverse().toString() : t.substring(left, right + 1)).equals(s) ? 1 : 0);
    }
}

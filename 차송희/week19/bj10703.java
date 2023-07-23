import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ10703 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String input = br.readLine();
            int result = isPalindrome(input);
            System.out.println(result);
        }
    }

    private static int isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                if (isRange(str, left + 1, right) || isRange(str, left, right - 1)) {
                    return 1;
                } else {
                    return 2;
                }
            }

            left++;
            right--;
        }

        return 0;
    }

    private static boolean isRange(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

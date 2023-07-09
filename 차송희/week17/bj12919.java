import java.util.*;

public class BJ12919 {
    static boolean found = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String S = scanner.nextLine();
        String T = scanner.nextLine();

        dfs(S, T);

        System.out.println(found ? 1 : 0);

        scanner.close();
    }

    public static void dfs(String S, String T) {
        if (S.length() == T.length()) {
            if (S.equals(T)) {
                found = true;
            }
            return;
        }

        if (T.charAt(T.length() - 1) == 'A') {
            dfs(S, T.substring(0, T.length() - 1));
        }

        if (T.charAt(0) == 'B') {
            String reversedT = new StringBuilder(T.substring(1)).reverse().toString();
            dfs(S, reversedT);
        }
    }
}

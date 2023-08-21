import java.util.Scanner;

public class bj2716 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < N; i++) {
            int len = 0;
            char[] S = new char[151];
            String input = scanner.nextLine();
            for (char c : input.toCharArray()) {
                S[len++] = c;
            }

            int level = 0, maxLevel = 0;
            for (int j = 0; j < len; j++) {
                if (S[j] == '[') {
                    level++;
                    if (level > maxLevel)
                        maxLevel = level;
                } else {
                    level--;
                }
            }

            int result = 1;
            for (int j = 0; j < maxLevel; j++) {
                result *= 2;
            }

            System.out.println(result);
        }
    }
}

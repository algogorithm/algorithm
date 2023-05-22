import java.util.*;
import java.io.*;

public class BOJ1759_암호_만들기 {
    static final Set<Character> VOWEL = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int L = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[] charElements = new char[C];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; ++i) {
            charElements[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(charElements);
        printPasswordPool(L, 0, charElements, "");
    }

    public static void printPasswordPool(int passwordLength, int idx, char[] charElement, String password) {
        if (password.length() == passwordLength && isValid(password)) {
            System.out.println(password);
            return;
        }

        for (int i = idx; i < charElement.length; ++i) {
            printPasswordPool(passwordLength, i + 1, charElement, password + charElement[i]);
        }
    }

    public static boolean isValid(String password) {
        boolean hasVowel = false;
        int consonantCnt = 0;
        for (char c : password.toCharArray()) {
            hasVowel = hasVowel ? true : VOWEL.contains(c);
            consonantCnt = VOWEL.contains(c) ? consonantCnt : consonantCnt + 1;
        }

        return hasVowel && consonantCnt >= 2;
    }
}

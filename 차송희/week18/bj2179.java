import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BJ2179 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> arr = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = scanner.next();
            if (!arr.contains(str)) {
                arr.add(str);
            }
        }

        int a = 0;
        int b = 0;
        int max = 0;

        for (int i = 0; i < n - 1; i++) {
            String str1 = arr.get(i);
            for (int j = i + 1; j < n; j++) {
                String str2 = arr.get(j);
                int len = Math.min(str1.length(), str2.length());
                int count = 0;
                for (int k = 0; k < len; k++) {
                    if (str1.charAt(k) != str2.charAt(k)) {
                        break;
                    }
                    count++;
                }
                if (count > max) {
                    max = count;
                    a = i;
                    b = j;
                }
            }
        }

        System.out.println(arr.get(a));
        System.out.println(arr.get(b));

    }
}


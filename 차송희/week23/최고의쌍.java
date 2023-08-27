import java.util.*;

public class 최고의쌍 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            int num = sc.nextInt();
            int[] arr = new int[num];
            int max = -1;

            for (int i = 0; i < num; i++) {
                arr[i] = sc.nextInt();
            }

            if (num == 1) {
                max = arr[0];
            } else {
                for (int i = 0; i < num - 1; i++) {
                    for (int j = i + 1; j < num; j++) {
                        int temp = arr[i] * arr[j];
                        if (isConsecutive(temp)) {
                            max = Math.max(max, temp);
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + max);
        }
    }

    private static boolean isConsecutive(int n) {
        String str = Integer.toString(n);
        for (int k = 0; k < str.length() - 1; k++) {
            if (Integer.parseInt(str.substring(k, k + 1)) + 1 != Integer.parseInt(str.substring(k + 1, k + 2))) {
                return false;
            }
        }
        return true;
    }
}

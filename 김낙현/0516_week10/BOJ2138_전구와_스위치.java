import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ2138_전구와_스위치 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Boolean[] curStatus1 = br.readLine().chars().mapToObj(i -> i == '0').toArray(Boolean[]::new);
        Boolean[] curStatus2 = toggle(curStatus1.clone(), 0);
        Boolean[] targetStatus = br.readLine().chars().mapToObj(i -> i == '0').toArray(Boolean[]::new);

        int cnt1 = 0, cnt2 = 1;
        for (int i = 1; i < targetStatus.length; ++i) {
            if (curStatus1[i - 1] != targetStatus[i - 1]) {
                curStatus1 = toggle(curStatus1, i);
                ++cnt1;
            }
            if (curStatus2[i - 1] != targetStatus[i - 1]) {
                curStatus2 = toggle(curStatus2, i);
                ++cnt2;
            }
        }
        if (!Arrays.equals(curStatus1, targetStatus)) cnt1 = -1;
        if (!Arrays.equals(curStatus2, targetStatus)) cnt2 = -1;

        System.out.println((cnt1 * cnt2 == 0) ? 0 : (cnt1 * cnt2 > 0) ? Math.min(cnt1, cnt2) : Math.max(cnt1, cnt2));
    }

    public static Boolean[] toggle(Boolean[] curStatus, int idx) {
        if (idx > 0) curStatus[idx - 1] = !curStatus[idx - 1];
        curStatus[idx] = !curStatus[idx];
        if (idx < curStatus.length - 1) curStatus[idx + 1] = !curStatus[idx + 1];
        return curStatus;
    }
}

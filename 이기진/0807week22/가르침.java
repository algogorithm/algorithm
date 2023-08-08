package d202308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가르침 {
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        ans = 0;
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Character> cl = new ArrayList<>();
        ArrayList<String> sl = new ArrayList<>();
        if (k < 5) {
            System.out.println(0);
        } else {
            for (int i = 0; i < n; i++) {
                String s = bf.readLine();
                s = s.replace("a", "");
                s = s.replace("n", "");
                s = s.replace("t", "");
                s = s.replace("c", "");
                s = s.replace("i", "");
                sl.add(s);
                for (int j = 0; j < s.length(); j++) {
                    if (!cl.contains(s.charAt(j))) {
                        cl.add(s.charAt(j));
                    }
                }
            }
            ArrayList<Character> current = new ArrayList<Character>();
            if (k - 5 > cl.size()) {
                combination(sl, cl, cl.size(), current, 0, 0);
            } else {
                combination(sl, cl, k - 5, current, 0, 0);
            }
            System.out.println(ans);
        }
    }

    static void combination(ArrayList<String> sl, ArrayList<Character> cl, int k, ArrayList<Character> current,
                            int start, int idx) {
        if (idx == k) {
            int cnt = 0;
            for (int i = 0; i < sl.size(); i++) {
                String sb = sl.get(i);
                int flag = 1;
                for (int j = 0; j < sb.length(); j++) {
                    if (current.contains(sb.charAt(j)))
                        continue;
                    else {
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {
                    cnt++;
                }
            }
            ans = Math.max(ans, cnt);
            return;
        }
        for (int i = start; i < cl.size(); i++) {
            current.add(cl.get(i));
            combination(sl, cl, k, current, i + 1, idx + 1);
            current.remove(current.size() - 1);
        }
    }
}
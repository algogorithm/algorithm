
/*
 * 백준 2631 줄세우기
 * https://www.acmicpc.net/problem/2631
 * 
 * 
 */
import java.util.*;

public class BJ2631 {

    static ArrayList<Integer> list;
    static boolean[] v;
    static int[] num;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        num = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = sc.nextInt();
        }

        list = new ArrayList<>();
        v = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = true;
            dfs(i, i);
            v[i] = false;
        }

        Collections.sort(list);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void dfs(int start, int target) {
        if (v[num[start]] == false) {
            v[num[start]] = true;
            dfs(num[start], target);
            v[num[start]] = false;
        }
        if (num[start] == target)
            list.add(target);
    }
}

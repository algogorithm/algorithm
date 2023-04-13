import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1043_거짓말 {

    static int[] parents;

    static void makeSet(int n) {
        parents = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            parents[i] = i;
        }
    }

    static int find(int a) {
        return (a == parents[a]) ? a : (parents[a] = find(parents[a]));
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        } else if (a < b) {
            parents[b] = a;
        } else {
            parents[a] = b;
        }
    }

    public static void main(String[] args) throws Exception {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        makeSet(n);

        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int k = Integer.parseInt(st.nextToken());

        if (k == 0) {
            answer = m;
        } else {
            for (int i = 0; i < k; ++i) {
                union(0, Integer.parseInt(st.nextToken()));
            }

            int[] leaderOfParty = new int[m];
            for (int i = 0; i < leaderOfParty.length; ++i) {
                st = new StringTokenizer(br.readLine(), " ");
                int sizeOfParty = Integer.parseInt(st.nextToken());
                leaderOfParty[i] = Integer.parseInt(st.nextToken());
                for (int j = 1; j < sizeOfParty; ++j) {
                    union(leaderOfParty[i], Integer.parseInt(st.nextToken()));
                }
            }

            for (int i = 0; i < leaderOfParty.length; ++i) {
                if (find(leaderOfParty[i]) != 0) {
                    ++answer;
                }
            }
        }

        System.out.println(answer);
    }
}

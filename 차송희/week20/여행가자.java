import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//여행가자
public class BJ1976 {
    static int N;
    static int M;
    static int[][] map;
    static int[] travel;
    static boolean[] v;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        travel = new int[M];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            travel[i] = Integer.parseInt(st.nextToken());
        }

        if (solve()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean solve() {
        for (int i = 0; i < M - 1; i++) {
            int start = travel[i];
            int dest = travel[i + 1];
            boolean[] v = new boolean[N + 1];
            Queue<Integer> Q = new LinkedList<>();
            Q.add(start);
            v[start] = true;

            while (!Q.isEmpty()) {
                int current = Q.poll();
                if (current == dest) {
                    break;
                }

                for (int next = 1; next <= N; next++) {
                    if (map[current][next] == 1 && !v[next]) {
                        Q.add(next);
                        v[next] = true;
                    }
                }
            }
            if (!v[dest]) {
                return false;
            }
        }

        return true;
    }
}

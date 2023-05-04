import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1405_미친_로봇 {
    static final int[] DR = {+0, -0, +1, -1};
    static final int[] DC = {+1, -1, +0, -0};
    static double[] p_x = new double[4];

    static boolean[][] visited = new boolean[30][30];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < p_x.length; ++i) {
            p_x[i] = Double.parseDouble(st.nextToken()) / 100.f;
        }

        int sR = 15;
        int sC = 15;
        visited[sR][sC] = true;
        System.out.println(dfs(n, 0, sR, sC, 1));
    }

    public static double dfs(int n, int i, int r, int c, double prob) {
        if (n == i) {
            return prob;
        }

        double sumProb = 0.f;

        for (int dir = 0; dir < p_x.length; ++dir) {
            int nextR = r + DR[dir];
            int nextC = c + DC[dir];
            if (p_x[dir] == 0 || visited[nextR][nextC]) continue;
            visited[nextR][nextC] = true;
            sumProb += dfs(n, i + 1, nextR, nextC, prob * p_x[dir]);
            visited[nextR][nextC] = false;
        }

        return sumProb;
    }
}

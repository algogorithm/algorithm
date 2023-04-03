import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1976_여행_가자 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        boolean[][] reachable = new boolean[n+1][n+1];
        for(int i = 1; i <= n; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            for(int j = 1; j <= n; ++j) {
                reachable[i][j] = (Integer.parseInt(st.nextToken()) == 1) ? true : false;
                if(i == j) reachable[i][j] = true;
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] route = new int[m];
        for(int i = 0; i < m; ++i) {
            route[i] = Integer.parseInt(st.nextToken());
        }

        // Floyd-Warshall
        for(int k = 1; k <= n; ++k) {
            for(int i = 1; i <= n; ++i) {
                for(int j = 1; j <= n; ++j) {
                    if(reachable[i][k] && reachable[k][j]) reachable[i][j] = true;
                }
            }
        }

        for(int cur = route[0], i = 1;  i < route.length; ++i) {
            int next = route[i];
            if(!reachable[cur][next]) {
                System.out.println("NO");
                return;
            }
            cur = next;
        }
        System.out.println("YES");
    }
}

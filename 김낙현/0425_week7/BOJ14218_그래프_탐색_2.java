import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14218_그래프_탐색_2 {
    static List<Integer>[] adjCity;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjCity = new List[n + 1];

        for (int i = 0; i < adjCity.length; ++i) {
            adjCity[i] = new LinkedList<>();
        }

        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine(), " ");

            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());

            adjCity[city1].add(city2);
            adjCity[city2].add(city1);
        }

        int q = Integer.parseInt(br.readLine());

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[1] = 0;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < q; ++i) {
            st = new StringTokenizer(br.readLine());

            int city1 = Integer.parseInt(st.nextToken());
            int city2 = Integer.parseInt(st.nextToken());

            // 도로 정비
            adjCity[city1].add(city2);
            adjCity[city2].add(city1);

            dist = getShortestPath(city1, city2, dist);

            for (int j = 1; j < dist.length; ++j) {
                sb.append(dist[j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static int[] getShortestPath(int from, int to, int[] dist) {
        boolean[] fixed = new boolean[adjCity.length];
        fixed[1] = fixed[0] = true;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1, 0});

        while (!q.isEmpty()) {
            int[] curCity = q.poll();
            for (int next : adjCity[curCity[0]]) {
                if (fixed[next]) continue;
                q.add(new int[]{next, curCity[1] + 1});
                dist[next] = curCity[1] + 1;
                fixed[next] = true;
            }
        }
        return dist;
    }
}

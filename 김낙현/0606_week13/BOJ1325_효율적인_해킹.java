import java.io.*;
import java.util.*;

public class BOJ1325_효율적인_해킹 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] graph = new ArrayList[N + 1];
        boolean[] checked = new boolean[N + 1];
        int[] cnt = new int[N + 1];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[B].add(A);
        }

        int max = 0;
        for (int i = 1; i <= N; ++i) {
            boolean[] visited = new boolean[graph.length];
            Queue<Integer> queue = new LinkedList<>();

            queue.add(i);
            visited[i] = true;

            cnt[i] = 1;
            while (!queue.isEmpty())
            {
                int cur = queue.poll();
                for (int next : graph[cur])
                {
                    if (visited[next]) continue;

                    ++cnt[i];
                    visited[next] = true;
                    queue.add(next);
                }
            }

            max = Math.max(max, cnt[i]);
        }

        StringBuilder answer = new StringBuilder();
        for(int i = 1; i <= N; ++i) {
            if(max == cnt[i]) answer.append(i).append(" ");
        }

        System.out.println(answer);
    }
}

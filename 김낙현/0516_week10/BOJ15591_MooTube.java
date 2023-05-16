import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ15591_MooTube {
    static class Node {
        int id;
        int USADO;

        Node(int id, int USADO) {
            this.id = id;
            this.USADO = USADO;
        }
    }

    static List<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new LinkedList<>();
        }

        for (int i = 1; i <= N - 1; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            graph[p].add(new Node(q, r));
            graph[q].add(new Node(p, r));
        }

        for (int i = 0; i < Q; ++i) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            System.out.println(getReachableNumOfNodeWithIn(k, v));
        }
    }

    public static int getReachableNumOfNodeWithIn(int targetUSADO, int from) {
        int cnt = 0;
        boolean[] visited = new boolean[graph.length + 1];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(from, 0));
        visited[from] = true;

        while (!q.isEmpty()) {
            Node status = q.poll();
            if (status.USADO >= targetUSADO) ++cnt;

            for (Node adjNode : graph[status.id]) {
                if (visited[adjNode.id]) continue;
                visited[adjNode.id] = true;
                q.add(new Node(adjNode.id, (status.USADO == 0) ? adjNode.USADO : Math.min(adjNode.USADO, status.USADO)));
            }
        }

        return cnt;
    }
}

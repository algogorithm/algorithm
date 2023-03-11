import java.io.*;
import java.util.*;

public class BOJ1707_이분_그래프 {
    static ArrayList<Integer>[] graph;
    // 0 : 미방문, 1 : 그룹1, 2 : 그룹2
    static int[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph = new ArrayList[v + 1];
            visited = new int[v + 1];

            for (int i = 0; i < graph.length; ++i) {
                graph[i] = new ArrayList<>();
            }

            // 그래프 생성
            for (int i = 0; i < e; ++i) {
                st = new StringTokenizer(br.readLine(), " ");
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from].add(to);
                graph[to].add(from);
            }

            boolean isBipartite = true;
            // 이분 그래프 판별 : DFS 돌면서 다음 노드는 현재 노드와 다른 그룹으로 넣는다. 이미 방문해서 특정 그룹에 속해있는 노드인데 같은 그룹으로 정해지면 이분 그래프가 될 수 없다.
            for (int vtx = 1; vtx < visited.length; ++vtx) {
                if (visited[vtx] != 0) continue;
                if(!dfs(vtx, 1)) {
                    isBipartite = false;
                    break;
                }
            }
            System.out.println(isBipartite?"YES":"NO");
        }
    }

    public static boolean dfs(int start, int previousGroup) {
        visited[start] = (previousGroup == 2) ? 1 : 2;
        for (int next : graph[start]) {
            if(visited[next] == visited[start]) return false;
            if(visited[next] == 0 && !dfs(next, visited[start])) return false;
        }
        return true;
    }
}
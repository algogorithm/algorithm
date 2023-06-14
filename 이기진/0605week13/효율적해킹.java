package d202306;

import java.io.*;
import java.util.*;
import java.lang.*;

public class 효율적해킹 {

    static ArrayList<Integer>[] graph;
    static int n, m;
    static boolean[] visited;
    static int[] ans;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++) {
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[n+1];
        ans = new int[n+1];

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(bf.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph[s].add(e);
        }

        for(int i = 1; i < n+1; i++) {
            bfs(i);
            visited = new boolean[n+1];
        }

        int ans_max = 0;

        for(int i = 1; i < n+1; i++) {
            ans_max = Math.max(ans_max, ans[i]);
        }

        for(int i = 1; i < n+1; i++) {
            if(ans[i] == ans_max) {
                System.out.print(i + " ");
            }
        }

    }

    static void bfs(int node) {
        Queue<Integer> q = new LinkedList<>();
        visited[node] = true;
        q.offer(node);
        while (!q.isEmpty()) {
            int next = q.poll();
            for(int i : graph[next]) {
                if(!visited[i]) {
                    visited[i] = true;
                    ans[i]++;
                    q.offer(i);
                }
            }
        }
    }

}

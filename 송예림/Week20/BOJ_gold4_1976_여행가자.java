package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class gold4_1976_여행가자 {
    static int N, M;
    static boolean[][] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new boolean[N+1][N+1];
        int[] visit = new int[M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if(Integer.parseInt(st.nextToken()) == 1){
                    graph[i][j] = true;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            visit[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < M; i++) {
            if(!bfs(visit[i-1], visit[i])){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static boolean bfs(int start, int end) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] v = new boolean[N+1];
        queue.offer(start);
        v[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (cur == end){
                return true;
            }

            for (int i = 1; i <= N; i++) {
                if (graph[cur][i] && !v[i]){
                    queue.offer(i);
                    v[i] = true;
                }
            }
        }
        return false;
    }
}
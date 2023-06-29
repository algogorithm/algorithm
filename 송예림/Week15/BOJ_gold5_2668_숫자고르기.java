package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class gold5_2668_숫자고르기 {
    static int N;
    static int[] adjList;
    static boolean[] visit;
    static ArrayList<Integer> result = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        adjList = new int[N+1];
        for (int i = 1; i <= N; i++) {
            adjList[i] = Integer.parseInt(br.readLine());
        }

        visit = new boolean[N+1];
        for (int i = 1; i <= N; i++) {
            visit[i] = true;
            dfs(i, i);
            visit[i] = false;
        }

        sb.append(result.size()+"\n");
        for (int i : result) {
            sb.append(i+"\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int cur, int start) {
        if(adjList[cur] == start){
            result.add(start);
            return;
        }

        if(!visit[adjList[cur]]){
            visit[adjList[cur]] = true;
            dfs(adjList[cur], start);
            visit[adjList[cur]] = false;
        }
    }
}

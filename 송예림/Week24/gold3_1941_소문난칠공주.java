package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class gold3_1941_소문난칠공주 {
    static int[] dr = {1, 0}, dc = {0, 1};
    static int[][] map = new int[5][5]; // 이다솜 - 2, 임도연 - 1
    static int result = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int r = 0; r < 5; r++) {
            String str = br.readLine();
            for (int c = 0; c < 5; c++) {
                if(str.charAt(c) == 'S')
                    map[r][c] = 2;
                else
                    map[r][c] = 1;
            }
        }

        // 돌리면서 합 11 이상이면 ++
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                bfs(new Node(r, c, 1, map[r][c]));
            }
        }
    }

    private static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while(!queue.isEmpty()){
            Node cur = queue.poll();

            if(cur.cnt > 7) continue;

            if(cur.cnt == 7 && cur.sum >= 11){
                result++;
                continue;
            }

            for (int d = 0; d < dr.length; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if(nr >= 0 && nr < 5 && nc >= 0 && nc < 5){

                }
            }
        }
    }

    static class Node {
        int r, c, cnt, sum;

        public Node(int r, int c, int cnt, int sum) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.sum = sum;
        }
    }
}

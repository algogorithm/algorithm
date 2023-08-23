package Week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1261 {
    static int N,M, result;
    static int[][] map;
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());;
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        if(N == 1 && M == 1){
            System.out.println(0);
            return;
        }
        map = new int[N][M];
        for(int r = 0; r < N; r++){
            String temp = br.readLine();
            for(int c = 0; c < M; c++){
                map[r][c] = Character.getNumericValue(temp.charAt(c));
            }
        }
//        for(int[] a : map){
//            System.out.println(Arrays.toString(a));
//        }
        result = Integer.MAX_VALUE;
        boolean[][] v = new boolean[N][M];
        v[0][0] = true;
        bfs(0,0,0, v);
        System.out.println(result);
    }

    private static void bfs(int r, int c, int wallCnt, boolean[][] v){
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.add(new Node(r,c,0));
        while(!q.isEmpty()){
            Node node = q.poll();
            for(int d = 0; d < 4; d++) {
                int nr = node.r + dr[d];
                int nc = node.c + dc[d];
                int breakCnt = node.breakCnt;
                if (nr < 0 || nc < 0 || nr >= N || nc >= M) {
                    continue;
                }
                if(nr == N-1 && nc == M-1){
                    // 도착
                    result = Math.min(result, breakCnt);
                    continue;
                }
                if(v[nr][nc] == false){
                    v[nr][nc] = true;
                    q.add(new Node(nr,nc,breakCnt + (map[nr][nc] == 1 ? 1 : 0)));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int r;
        int c;
        int breakCnt;

        public Node(int r, int c, int breakCnt) {
            this.r = r;
            this.c = c;
            this.breakCnt = breakCnt;
        }

        @Override
        public int compareTo(Node o) {
            if(this.breakCnt < o.breakCnt) {
                return -1;
            } else if(this.breakCnt > o.breakCnt) {
                return 1;
            }
            return 0;
        }


    }

}

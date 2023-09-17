package Week25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_18405 {
    static int N, K, S, X, Y, result;
    static int[][] map;
    static PriorityQueue<Virus> q = new PriorityQueue<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        // 바이러스 종류는 K이하
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int r = 0; r < N; r++){
        st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                if(map[r][c] != 0){
                    q.add(new Virus(r, c, map[r][c], 0));
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        // s초 이후에
        S = Integer.parseInt(st.nextToken());
//        (X,Y)좌표에 있는 바이러스 종류 출력(없으면 0 출력)
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
        // 정답
        result = 0;
        bfs();
        System.out.println(map[X][Y]);
    }

    private static void bfs() {
        while(!q.isEmpty()){
            Virus virus = q.poll();
            if(virus.time == S){
                break;
            }
            for(int d = 0; d < 4; d++){
                int nr = virus.r + dr[d];
                int nc = virus.c + dc[d];
                if(isArrRangeOut(nr, nc))
                    continue;
                if(map[nr][nc] == 0){
                    map[nr][nc] = virus.type;
                    q.add(new Virus(nr, nc, virus.type, virus.time + 1));
                }
            }
        }
    }

    private static boolean isArrRangeOut(int nr, int nc) {
        return nr < 0 || nc < 0 || nr >= N || nc >= N;
    }

    static class Virus implements Comparable<Virus>{
        int r, c, type, time;

        public Virus(int r, int c, int type, int time) {
            this.r = r;
            this.c = c;
            this.type = type;
            this.time = time;
        }
        @Override
        public int compareTo(Virus o) {
            if (this.time == o.time) {
                return Integer.compare(this.type, o.type);
            }
            return Integer.compare(this.time, o.time);
        }
    }
}

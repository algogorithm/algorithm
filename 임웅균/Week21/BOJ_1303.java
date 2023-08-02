package Week21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1303 {
    static int N,M;
    static char[][] arr;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        boolean[][] v = new boolean[N][M];
        for(int i = 0; i < N; i++){
            arr[i] = br.readLine().toCharArray();
        }
//        for(char[] a : arr){
//            System.out.println(Arrays.toString(a));
//        }
        int wScore = 0, bScore = 0;
        for(int r = 0; r < N; r++){
            for(int c = 0; c < M; c++){
                if(v[r][c] != true){
                    v[r][c] = true;
                    int score = bfs(r, c, arr, v);
                    if(arr[r][c] == 'W'){
                        wScore += Math.pow(score, 2);
                    }
                    else{
                        bScore += Math.pow(score, 2);
                    }
                }
            }
        }
        System.out.println(wScore + " " + bScore);
    }

    private static int bfs(int R, int C, char[][] arr, boolean[][] v) {
        int score = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{R,C});
        char baseLetter = arr[R][C];
        while(!q.isEmpty()){
            int[] curr = q.poll();
            for(int d = 0; d < 4; d++){
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];
                if(isArrOut(nr,nc) || v[nr][nc] || arr[nr][nc] != baseLetter){
                    continue;
                }
                v[nr][nc] = true;
                score++;
                q.add(new int[]{nr,nc});
            }
        }
        return score;
    }
    private static boolean isArrOut(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}

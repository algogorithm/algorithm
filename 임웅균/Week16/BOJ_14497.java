package Week16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_14497 {
    static int N, M;
    static int[] junan;
    static int[] target;
    static char[][] map;
    static int[] deltas = {0,-1,0,1,0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        junan = new int[2];
        target = new int[2];
        map = new char[N][M];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++){
            if(i < 2){
                junan[i] = Integer.parseInt(st.nextToken()) -1;
            }
            else{
                target[i-2] = Integer.parseInt(st.nextToken()) -1;
            }
        }
        for(int i = 0; i < N; i++){
            map[i] = br.readLine().toCharArray();
        }
        // bfs로 탐색
        int[][] visited = new int[N][M];
        // 친구가 없는 곳에서는 다음 파동으로 넘기기 위해 deque의 앞에 다음 위치를 넣어준다.
        Deque<int[]> dq = new ArrayDeque<>();
        dq.addLast(junan);
        visited[junan[0]][junan[1]] = 1;
        // 탐색시 현재위치
        int[] curr;
        int r, c, nr, nc, result = 0;
        while(!dq.isEmpty()){
            curr = dq.pollFirst();
            r = curr[0];
            c = curr[1];
            if(Arrays.equals(curr, target)){
                // 찾음
                result = Math.max(visited[r][c]-1, result);
                break;
            }
            for(int d = 0; d < 4; d++){
                nr = r + deltas[d];
                nc = c + deltas[d+1];
                // 다음 위치가 범위를 초과했거나, 이미 탐색한 곳이면 넘어가기
                if(isArrOut(nr, nc) || visited[nr][nc] > 0)
                    continue;
                // 만약 다음 위치가 친구가 없는곳이면, 덱의 앞에 넣어서 재탐색해준다.
                if(map[nr][nc] == '0'){
                    visited[nr][nc] = visited[r][c];
                    dq.addFirst(new int[]{nr, nc});
                }
                // 다음 위치가 친구가 있는곳이니, 덱의 맨 뒤에 넣어 추후 탐색해준다.
                else{
                    visited[nr][nc] = visited[r][c] + 1;
                    dq.addLast(new int[]{nr, nc});
                }
            }
        }
        System.out.println(result);
    }
    static boolean isArrOut(int r, int c){
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}

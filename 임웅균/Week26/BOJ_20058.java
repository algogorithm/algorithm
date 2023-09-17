package Week26;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20058 {
    static int[][] map, visited;
    static int N, Q, L, powL, totalIce;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static Queue<int[]> meltingQ = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        totalIce = 0;
        map = new int[(int) Math.pow(2,N)][(int) Math.pow(2,N)];
        for(int r = 0; r < map.length; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < map.length; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
                totalIce += map[r][c];
            }
        }
        // L받기
        st = new StringTokenizer(br.readLine());
        // Q번만큼 파이어스톰 돌리기
        while(Q-- > 0){
            L = Integer.parseInt(st.nextToken());
            powL = (int) Math.pow(2,L);
            fireStorm();
        }
        // 전체 덩어리의 합 출력
        System.out.println(totalIce);
        // 가장 큰 덩어리가 차지하는 칸의 개수 출력(bfs)
        visited = new int[(int) Math.pow(2,N)][(int) Math.pow(2,N)];
        int idx = 1;
        int maxSize = 0;
        for(int r = 0; r < map.length; r++){
            for(int c = 0; c < map.length; c++){
                if(map[r][c] != 0 && visited[r][c] == 0){
                    maxSize = Math.max(bfs(r,c,idx++), maxSize);
                }
            }
        }
        System.out.println(maxSize);
    }

    private static int bfs(int R, int C, int idx) {

        int size = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{R,C});
        visited[R][C] = idx;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            for(int d = 0; d < 4; d++){
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];
                if(nr < 0 || nc < 0 || nr >= map.length || nc >= map.length){
                    continue;
                }
                if(visited[nr][nc] != 0 || map[nr][nc] == 0){
                    continue;
                }
                visited[nr][nc] = idx;
                size++;
                q.add(new int[]{nr,nc});
            }
        }
        return size;
    }


    private static void fireStorm() {
        for(int r = 0; r < map.length; r+=powL){
            for(int c = 0; c < map.length; c+=powL){
                // 배열돌리기
                rotateArray(r,c);
            }
        }
        // 전체 탐색하면서 음이 있는 칸 3개 또는 그 이상과 인접해있지 않은 칸은 얼음의 양 1 줄이기
        for(int r = 0; r < map.length; r++){
            for(int c = 0; c < map.length; c++){
                // 4방향 탐색
                int iceCnt = 0;
                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr < 0 || nc < 0 || nr >= map.length || nc >= map.length){
                        continue;
                    }
                    // 얼음이 있으면 iceCnt +1
                    iceCnt += map[nr][nc] > 0 ? 1 : 0;
                }
                if (map[r][c] > 0 && iceCnt < 3) {  // 얼음이 있고, 줄어들어야 하는 조건을 추가
                    meltingQ.add(new int[]{r, c});
                }

            }
        }
        // 전체탐색을 마쳤으니 얼음 한번에 지워주기
        while (!meltingQ.isEmpty()) {
            int[] curr = meltingQ.poll();
            if (map[curr[0]][curr[1]] > 0) {  // 얼음이 이미 녹아서 0이 아닌 경우만 녹인다.
                map[curr[0]][curr[1]]--;
                totalIce--;
            }
        }
    }

    private static void rotateArray(int r, int c) {
        int[][] rotated = new int[powL][powL];

        // 회전된 배열 생성
        for(int i = r; i < r + powL; i++) {
            for(int j = c; j < c + powL; j++) {
                rotated[j-c][r + powL - 1 - i] = map[i][j];
            }
        }

        // 원래 배열에 회전된 배열 복사
        for(int i = r; i < r + powL; i++) {
            for(int j = c; j < c + powL; j++) {
                map[i][j] = rotated[i-r][j-c];
            }
        }
    }
}
package Week18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2234 {
    static int[][] map, areaMap;
    static int[] result;
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = new int[3];
        map = new int[M][N];
        areaMap = new int[M][N];
        for(int r = 0; r < M; r++){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        // 0. 이 성에 있는 방의 개수
        // 1. 가장 넓은 방의 넓이
        // bfs를 돌면서 각 위치별 영역을 지정해주고, 영역별 사이즈도 저장해준다.
        int areaNumber = 0;
        List<Integer> areaSizeList = new ArrayList<>();
        for(int r = 0; r < M; r++){
            for(int c = 0; c < N; c++){
                if(areaMap[r][c] == 0){
                    int currAreaSize = bfs(r, c, ++areaNumber);
                    areaSizeList.add(currAreaSize);
                    result[1] = Math.max(result[1], currAreaSize);
                }
            }
        }
        result[0] = areaNumber;

        // 2. 하나의 벽을 제거하여 얻을 수 있는 가장 넓은 방의 크기
        for(int r = 0; r < M; r++){
            for(int c = 0; c < N; c++){
                for(int d = 0; d < 4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    // 다음 위치가 배열 범위를 초과하는지 확인
                    // 현재 위치와 다음 위치가 같은 영역인지 확인
                    if(isArrOut(nr,nc) || areaMap[r][c] == areaMap[nr][nc]){
                        continue;
                    }
                    // areaSizeList에서 현재 위치 영역과 다음 위치 영역의 크기를 합쳐 정답 갱신
                    result[2] = Math.max(result[2], areaSizeList.get(areaMap[r][c]-1) + areaSizeList.get(areaMap[nr][nc]-1));
                }
            }
        }
        for(int i : result){
            System.out.println(i);
        }
    }

    private static boolean isArrOut(int r, int c){
        return r < 0 || c < 0 || r >= M || c >= N;
    }
    static int[] dr = {-1, 0, 1, 0};  // 북, 동, 남, 서 순서
    static int[] dc = {0, 1, 0, -1};  // 북, 동, 남, 서 순서
    static int[] wall = {2, 4, 8, 1};  // 북, 동, 남, 서 순서


    private static int bfs(int R, int C, int areaNumber) {
        int areaSize = 1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{R,C});
        areaMap[R][C] = areaNumber;
        while(!q.isEmpty()){
            int[] curr = q.poll();
            for(int d = 0; d < 4; d++){
                int nr = curr[0] + dr[d];
                int nc = curr[1] + dc[d];
                // 현재 위치가 배열 범위 초과인지 확인.
                // 현재 방향이 뚫린 방향인지 확인.
                // 현재 위치가 다른 영역인지 확인
                if(isArrOut(nr,nc) || (map[curr[0]][curr[1]] & wall[d]) != 0 || areaMap[nr][nc] != 0){
                    continue;
                }
                areaMap[nr][nc] = areaNumber;
                q.add(new int[]{nr, nc});
                areaSize++;
            }
        }
        return areaSize;
    }
}

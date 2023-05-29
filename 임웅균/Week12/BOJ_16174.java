import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16174 {
    // 1차시도 메모리 초과.
    static int N;
    static int[][] map;
    static boolean[][] v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while(st.hasMoreTokens()){
                map[i][idx++] = Integer.parseInt(st.nextToken());
            }
        }
        v = new boolean[N][N];
        v[0][0] = true;
        String answer = dfs(0,0) ? "HaruHaru" : "Hing";
        System.out.println(answer);
    }

    // 이동 가능한 방향은 오른쪽과 아래 뿐
    static int[] dr = {0, 1};
    static int[] dc = {1, 0};
    private static boolean dfs(int R, int C) {
        // 도착지에 도달했는지 확인
        if (map[R][C] == -1) {
            return true;
        }

        // 이동 가능한 두 방향(오른쪽, 아래)에 대해 반복
        for (int d = 0; d < 2; d++) {
            int nr = R + dr[d] * map[R][C];
            int nc = C + dc[d] * map[R][C];

            // 범위를 초과하지 않고, 해당 위치를 방문하지 않았다면?
            if (!isOutOfRange(nr, nc) && v[nr][nc] == false) {
                v[nr][nc] = true;  // 방문 처리

                // 다음 위치에서의 DFS 결과가 true라면, 현재 위치에서도 true를 반환
                if (dfs(nr, nc)) {
                    return true;
                }
            }
        }

        // 모든 방향을 확인했는데도 도착지에 도달하지 못했다면, 현재 위치에서는 도착지에 도달할 수 없음
        return false;
    }

    // 주어진 좌표가 유효한 범위 내에 있는지 확인
    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }

}

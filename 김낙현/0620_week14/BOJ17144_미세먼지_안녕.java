import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17144_미세먼지_안녕 {
    static int[] dr = {-1,+1,+0,+0};
    static int[] dc = {+0,+0,-1,+1};
    static int R, C, T;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for(int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] airCleaner = new int[2][2];
        search:
        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] == -1) {
                    airCleaner[0][0] = r;
                    airCleaner[0][1] = c;
                    airCleaner[1][0] = r+1;
                    airCleaner[1][1] = c;
                    break search;
                }
            }
        }

        for(int t = 0; t < T; t++) {
            spread();
            clean(airCleaner);
        }

        int sum = 0;
        for(int[] row : map) {
            for(int i : row) {
                sum += i;
            }
        }

        System.out.println(sum+2);
    }
    private static void clean(int[][] airCleaner) {
        // TODO Auto-generated method stub
        // 반시계 방향
        int startR = airCleaner[0][0];
        int startC = airCleaner[0][1];

        int row = startR;
        int col = startC;

        // 상
        for(; row > 0; row--) {
            map[row][col] = map[row-1][col];
        }
        // 우
        for(; col < map[0].length-1; col++) {
            map[row][col] = map[row][col+1];
        }
        // 하
        for(; row < startR; row++) {
            map[row][col] = map[row+1][col];
        }
        // 좌
        for(; col > startC; col--) {

            map[row][col] = map[row][col-1];
        }
        map[startR][startC+1] = 0;
        map[startR][startC] = -1;

        // 시계 방향
        startR = airCleaner[1][0];
        startC = airCleaner[1][1];

        row = startR;
        col = startC;

        // 하
        for(; row < map.length-1; row++) {
            map[row][col] = map[row+1][col];
        }
        // 우
        for(; col < map[0].length-1; col++) {
            map[row][col] = map[row][col+1];
        }
        // 상
        for(; row > startR; row--) {
            map[row][col] = map[row-1][col];
        }
        // 좌
        for(; col > startC; col--) {
            map[row][col] = map[row][col-1];
        }
        map[startR][startC+1] = 0;
        map[startR][startC] = -1;
    }
    private static void spread() {
        // TODO Auto-generated method stub
        int[][] delta = new int[R][C];

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                if(map[r][c] > 0) {
                    // 확산되는 칸의 수
                    int cnt = 0;
                    for(int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(nr>=0 && nr<R && nc>=0 && nc<C && map[nr][nc] != -1) {
                            cnt++;
                            delta[nr][nc] += (map[r][c]/5);
                        }
                    }
                    delta[r][c] -= ((map[r][c]/5)*cnt);
                }
            }
        }

        for(int r = 0; r < R; r++) {
            for(int c = 0; c < C; c++) {
                map[r][c] += delta[r][c];
            }
        }
    }
}

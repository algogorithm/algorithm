import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13901_로봇 {
    static final int[] DR = {/*Dummy*/0, -1, +1, -0, +0};
    static final int[] DC = {/*Dummy*/0, -0, +0, -1, +1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[R][C];

        // 장애물
        int k = Integer.parseInt(br.readLine());
        for(int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int bR = Integer.parseInt(st.nextToken());
            int bC = Integer.parseInt(st.nextToken());
            map[bR][bC] = true;
        }

        // 시작 위치
        st = new StringTokenizer(br.readLine(), " ");
        int sR = Integer.parseInt(st.nextToken());
        int sC = Integer.parseInt(st.nextToken());
        map[sR][sC] = true;

        st = new StringTokenizer(br.readLine(), " ");

        int[] dir = new int[4];
        for(int i = 0; i < dir.length; ++i) {
            dir[i] = Integer.parseInt(st.nextToken());
        }

        int curDir = 0;
        int curR = sR;
        int curC = sC;
        int turnCnt = 0;

        while (turnCnt < 4) {
            int nextR = curR + DR[dir[curDir]];
            int nextC = curC + DC[dir[curDir]];
            if (nextR < 0 || nextR >= R || nextC < 0 || nextC >= C || map[nextR][nextC]) {
                curDir = (curDir + 1) % 4;
                ++turnCnt;
                continue;
            }
            map[nextR][nextC] = true;
            curR = nextR;
            curC = nextC;
            turnCnt = 0;
        }

        System.out.println(curR + " " + curC);
    }
}

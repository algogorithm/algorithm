import java.io.*;
import java.util.*;

public class BOJ15683_감시 {

    static final int[] DR = {-1, +0, +1, +0};
    static final int[] DC = {-0, +1, -0, -1};
    static int[][] map;
    static int invisible = 0;

    static List<Camera> cameraList = new ArrayList<>();

    static int answer = Integer.MAX_VALUE;

    static class Camera {
        private final int[] numOfDir = {0, 4, 2, 4, 4, 1};
        int type;
        int r;
        int c;       boolean[] watchableDirection;
        Queue<int[]>[] watchableBlock;

        Camera(int type, int r, int c) {
            this.type = type;
            this.r = r;
            this.c = c;
            watchableBlock = new Queue[4];
            for (int i = 0; i < 4; ++i) {
                watchableBlock[i] = new LinkedList<int[]>();
            }
            setDirections();
        }

        public int getNumOfDirections() {
            return this.numOfDir[this.type];
        }

        private void setDirections() {
            watchableDirection = new boolean[4];
            switch (this.type) {
                case 1:
                    watchableDirection[1] = true;
                    break;
                case 2:
                    watchableDirection[1] = watchableDirection[3] = true;
                    break;
                case 3:
                    watchableDirection[0] = watchableDirection[1] = true;
                    break;
                case 4:
                    watchableDirection[0] = watchableDirection[1] = watchableDirection[3] = true;
                    break;
                case 5:
                    watchableDirection[0] = watchableDirection[1] = watchableDirection[2] = watchableDirection[3] = true;
                    break;
                default:
                    System.out.println("Something Wrong!");
                    break;
            }
        }

        public void rotateClockWise() {
            this.undoWatch();
            boolean[] rotatedDirections = new boolean[4];

            for (int i = 0; i < 4; ++i) {
                rotatedDirections[(i + 1) % 4] = watchableDirection[i];
            }

            watchableDirection = rotatedDirections;
        }

        public void watch() {
            for (int dir = 0; dir < 4; ++dir) {
                if (!watchableDirection[dir]) continue;
                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{this.r, this.c});

                while (!q.isEmpty()) {
                    int[] cur = q.poll();

                    int nextR = cur[0] + DR[dir];
                    int nextC = cur[1] + DC[dir];

                    if (nextR < 0 || nextR >= map.length || nextC < 0 || nextC >= map[0].length || map[nextR][nextC] == 6)
                        continue;

                    q.add(new int[]{nextR, nextC});
                    if (map[nextR][nextC] == 0) {
                        --invisible;
                        watchableBlock[dir].add(new int[]{nextR, nextC});
                        map[nextR][nextC] = '#';
                    }
                }
            }
        }

        private void undoWatch() {
            for (int dir = 0; dir < 4; ++dir) {
                while (!watchableBlock[dir].isEmpty()) {
                    int[] recoverBlock = watchableBlock[dir].poll();
                    map[recoverBlock[0]][recoverBlock[1]] = 0;
                    ++invisible;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        for (int r = 0; r < n; ++r) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int c = 0; c < m; ++c) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 0) ++invisible;
                else if (map[r][c] >= 1 && map[r][c] <= 5) {
                    cameraList.add(new Camera(map[r][c], r, c));
                }
            }
        }

        simulation(0);

        System.out.println(answer);
    }

    static public void simulation(int cameraIdx) {
        if (cameraIdx >= cameraList.size()) {
            answer = Math.min(answer, invisible);
            return;
        }

        Camera curCamera = cameraList.get(cameraIdx);
        for (int i = 0; i < curCamera.getNumOfDirections(); ++i) {
            curCamera.watch();
            simulation(cameraIdx + 1);
            curCamera.rotateClockWise();
        }
    }
}
